package dao;

import java.util.List;

import entity.Book;


public interface BooksDao {
	public List<Book> queryAllBooks();
	public List<Book> queryBooksByName(String name);
	public List<Book> queryBooksByBookID(int BookID);
	
	public void addBook(Book book);
	public void delBook(int bookID);
	public void updateBook(Book book, int bookID);
}
