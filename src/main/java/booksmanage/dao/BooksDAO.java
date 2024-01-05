package booksmanage.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import booksmanage.model.Book;

public class BooksDAO {

	private static final String GET_ALL_BOOKS_SQL = "select * from books";
	private static final String INSERT_A_BOOK_SQL = "INSERT INTO BOOKS(title, author, price,quantity) values(?,?,?,?)";
	private static final String DELETE_BOOKS_SQL = "delete from books where id =?";
	private static final String SELECT_BOOK_SQL = "select id, title, author, quantity, price from books where id=?";
	private static final String UPDATE_A_BOOK_SQL = "UPDATE BOOKS SET title=?, author=?, quantity=?, price=? where id=?";

	private String jdbcURL = "jdbc:mysql://localhost:3306/demo?useSSL=false";

	private String jdbcUsername = "root";

	private String jdbcPassword = "root";

	private Connection connection = null;;

	public Connection getConnection() {

		try {

			if (connection == null) {

				connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return connection;
	}

	public List<Book> getAllBooks() throws SQLException {

		List<Book> books = new ArrayList<Book>();
		Connection conn = getConnection();

		PreparedStatement ps = conn.prepareStatement(GET_ALL_BOOKS_SQL);

		ResultSet rs = ps.executeQuery();

		while (rs.next()) {

			int id = rs.getInt("id");
			String title = rs.getString("title");
			String author = rs.getString("author");
			String qty = rs.getString("quantity");
			String price = rs.getString("price");

			books.add(new Book(id, title, author, qty, price));

		}

		return books;

	}

	public boolean insertBooks(Book book) throws SQLException {

		Connection conn = getConnection();

		PreparedStatement ps = conn.prepareStatement(INSERT_A_BOOK_SQL);

		ps.setString(1, book.getTitle());
		ps.setString(2, book.getAuthor());
		ps.setString(3, book.getPrice());
		ps.setString(4, book.getQuantity());
		boolean rowAffected = ps.executeUpdate() > 0;

		return rowAffected;
	}

	public boolean deleteBook(int id) throws SQLException {

		boolean rowDelete = false;

		Connection conn = getConnection();
		System.out.println(DELETE_BOOKS_SQL);

		PreparedStatement ps = conn.prepareStatement(DELETE_BOOKS_SQL);
		ps.setInt(1, id);

		rowDelete = ps.executeUpdate() > 0;

		return rowDelete;

	}

	public Book selectBook(int id) throws SQLException {

		Connection conn = getConnection();

		PreparedStatement ps = conn.prepareStatement(SELECT_BOOK_SQL);

		ps.setInt(1, id);

		ResultSet rs = ps.executeQuery();

		Book book = null;

		while (rs.next()) {

			String name = rs.getString("title");

			String author = rs.getString("author");

			String quantity = rs.getString("quantity");

			String price = rs.getString("price");

			book = new Book(id, name, author, quantity, price);
		}

		return book;
	}

	public boolean updateBook(Book book) throws SQLException {
		Connection conn = getConnection();

		PreparedStatement ps = conn.prepareStatement(UPDATE_A_BOOK_SQL);

		ps.setString(1, book.getTitle());
		ps.setString(2, book.getAuthor());
		ps.setString(3, book.getPrice());
		ps.setString(4, book.getQuantity());

		ps.setInt(5, book.getId());

		System.out.println("Updating book id=" + book.getId());
		boolean rowAffected = ps.executeUpdate() > 0;

		return rowAffected;

	}

}
