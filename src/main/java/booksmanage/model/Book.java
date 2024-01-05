package booksmanage.model;

import javax.management.loading.PrivateClassLoader;

public class Book {

	private String title;
	
	private String author;
	
	private String quantity;
	
	private String price;
	
	private int id;

	
	public Book(int id, String title, String author, String quantity, String price) {
		super();
		
		this.id = id;
		this.title = title;
		this.author = author;
		this.quantity = quantity;
		this.price = price;
	}
	
	public Book(String title, String author, String quantity, String price) {
		super();
		
		this.title = title;
		this.author = author;
		this.quantity = quantity;
		this.price = price;
	}

	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	
}
