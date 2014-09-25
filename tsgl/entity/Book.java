package entity;

import java.sql.Date;

public class Book {
	private int id;
	private String name;
	private String type;
	private String author;
	private String translator;
	private String publisher;
	private Date publisher_time;
	private int stock;				//ø‚¥Ê¡ø
	private double price;
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Book(int id, String name, String type, String author,
			String translator, String publisher, Date publisherTime, int stock, double price) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.author = author;
		this.translator = translator;
		this.publisher = publisher;
		publisher_time = publisherTime;
		this.stock = stock;
		this.price = price;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTranslator() {
		return translator;
	}
	public void setTranslator(String translator) {
		this.translator = translator;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public Date getPublisher_time() {
		return publisher_time;
	}
	public void setPublisher_time(Date publisherTime) {
		publisher_time = publisherTime;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
}
