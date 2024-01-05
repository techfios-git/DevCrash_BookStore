package booksmanage.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import booksmanage.dao.BooksDAO;
import booksmanage.model.Book;

/**
 * Servlet implementation class BookServlet
 */
@WebServlet("/")
public class BookServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	BooksDAO bookDAO = null;

	public void init() {
		bookDAO = new BooksDAO();
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BookServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

//		response.getWriter().append("Served at: ").append(request.getContextPath());

		String action = request.getServletPath();
		System.out.println("Servlet aciton: " + action);

		try {

			switch (action) {

			case "/new": {

				newBook(request, response);
				break;
			}
			case "/edit": {

				showEditForm(request, response);
				break;
			}

			case "/update": {

				updateBook(request, response);
				break;
			}
			case "/add": {

				addBook(request, response);
				break;
			}
			case "/list": {

				showBooklist(request, response);
				break;
			}
			case "/delete": {

				deletBook(request, response);
				break;
			}

			default:
				listAllBooks(request, response);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {

	    int id =Integer.parseInt(request.getParameter("id"));
	    
	    System.out.println("Edit Form id="+ id);
	    
	    Book book = bookDAO.selectBook(id);
	    
	    request.setAttribute("book", book);
	    
	    request.getRequestDispatcher("books-form.jsp").forward(request, response);

	    
	}

	private void showBookForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher reqDispatcher = request.getRequestDispatcher("books-form.jsp");

		reqDispatcher.forward(request, response);
	}

	private void addBook(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {

		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String quantity = request.getParameter("quantity");
		String price = request.getParameter("price");

		Book buk = new Book(title, author, quantity, price);

		bookDAO.insertBooks(buk);
		response.sendRedirect("list-books");

	}

	private void updateBook(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));

		System.out.println("updateBook id=" + id);

		String title = request.getParameter("title");

		String author = request.getParameter("author");
		String quantity = request.getParameter("quantity");

		String price = request.getParameter("price");

		Book buk = new Book(id, title, author, quantity, price);

		bookDAO.updateBook(buk);

		response.sendRedirect("list-books");

	}

	private void newBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		showBookForm(request, response);

	}

	private void listAllBooks(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {

		List<Book> listbooks = bookDAO.getAllBooks();

		request.setAttribute("bookslist", listbooks);
		RequestDispatcher rd = request.getRequestDispatcher("list-books.jsp");

		rd.forward(request, response);

	}

	private void deletBook(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {

		System.out.println("deleteBook() called....");

		int id = Integer.parseInt(request.getParameter("id"));

		bookDAO.deleteBook(id);

		response.sendRedirect("list-books");

	}

	private void showBooklist(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
