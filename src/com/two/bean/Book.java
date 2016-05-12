package com.two.bean;

public class Book {

	private String id ;
	
	private String bookName ;
	
	private String author ;
	
	private float price ;
	
	private String description ;
	
	public Book(String id, String bookName, String author, float price,
			String description) {
		this.id = id;
		this.bookName = bookName;
		this.author = author;
		this.price = price;
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", bookName=" + bookName + ", author="
				+ author + ", price=" + price + ", description=" + description
				+ "]";
	}
}
