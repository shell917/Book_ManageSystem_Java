package impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.Connect;

import dao.BooksDao;
import entity.Book;



public class IBook implements BooksDao{
	private static Connection conn = null;
	private static PreparedStatement pst = null;
	private static ResultSet rs = null;
	@Override
	public List<Book> queryAllBooks() {
		// TODO Auto-generated method stub
		conn = Connect.getConnection();
		List<Book> bookList = new ArrayList<Book>();
		try {
			String sql = "select * from book;";
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery(sql);
			while(rs.next()){
				Book book = new Book();
				book.setId(rs.getInt("id"));
				book.setName(rs.getString("name"));
				book.setType(rs.getString("type"));
				book.setAuthor(rs.getString("author"));
				book.setTranslator(rs.getString("translator"));
				book.setPublisher(rs.getString("publisher"));
				book.setPublisher_time(rs.getDate("publisher_time"));
				book.setStock(rs.getInt("stock"));
				book.setPrice(rs.getDouble("price"));
				bookList.add(book);	
			}
		} catch (SQLException e) {
			System.out.println("查询数据库信息失败！");
			e.printStackTrace();
		}finally{
			Connect.free(rs, pst, conn);
		}
		return bookList;
		
	}
	@Override
	public List<Book> queryBooksByName(String name) {
		// TODO Auto-generated method stub
		conn = Connect.getConnection();
		PreparedStatement pst = null;
		List<Book> bookList = new ArrayList<Book>();
		try {
			String sql =String.format("select * from book where name like '%s';", "%"+name+"%");
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while(rs.next()){
				Book book = new Book();
				book.setId(rs.getInt("id"));
				book.setName(rs.getString("name"));
				book.setType(rs.getString("type"));
				book.setAuthor(rs.getString("author"));
				book.setTranslator(rs.getString("translator"));
				book.setPublisher(rs.getString("publisher"));
				book.setPublisher_time(rs.getDate("publisher_time"));
				book.setStock(rs.getInt("stock"));
				book.setPrice(rs.getDouble("price"));
				bookList.add(book);	
			}
		} catch (SQLException e) {
			System.out.println("查询数据库信息失败！");
			e.printStackTrace();
		}finally{
			Connect.free(rs, pst, conn);
		}
		return bookList;
	}
	@Override
	public void addBook(Book book) {
		// TODO Auto-generated method stub
		conn = Connect.getConnection();
		PreparedStatement pst = null;
		try {
//			String sql = "insert into book (name,type,author,translator,publisher,publisher_time,stock,price) values(?,?,?,?,?,?,?);";
			String sql = "insert into book values(?,?,?,?,?,?,?,?,?);";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, book.getId());
			pst.setString(2, book.getName());
			pst.setString(3, book.getType());
			pst.setString(4, book.getAuthor());
			pst.setString(5, book.getTranslator());
			pst.setString(6, book.getPublisher());
			pst.setDate(7, book.getPublisher_time());
			pst.setInt(8, book.getStock());
			pst.setDouble(9, book.getPrice());
			pst.executeUpdate();
		} catch (SQLException e) {
			System.out.println("添加图书信息失败！");
			e.printStackTrace();
		}finally{
			Connect.free(rs, pst, conn);
		}
	}
	@Override
	public void delBook(int bookID) {
		// TODO Auto-generated method stub
		conn = Connect.getConnection();
		PreparedStatement pst = null;
		try {
			String sql = "delete from book where id=?;";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, bookID);
			pst.executeUpdate();
		} catch (SQLException e) {
			System.out.println("删除图书信息失败！");
			e.printStackTrace();
		}finally{
			Connect.free(rs, pst, conn);
		}
	}
	@Override
	public void updateBook(Book book, int bookID) {
		// TODO Auto-generated method stub
		conn = Connect.getConnection();
		PreparedStatement pst = null;
		try {
			String sql = "update book set name=?,type=?,author=?,translator=?,publisher=?,publisher_time=?,stock=?,price=? where id=?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, book.getName());
			pst.setString(2, book.getType());
			pst.setString(3, book.getAuthor());
			pst.setString(4, book.getTranslator());
			pst.setString(5, book.getPublisher());
			pst.setDate(6, book.getPublisher_time());
			pst.setInt(7, book.getStock());
			pst.setDouble(8, book.getPrice());
			pst.setInt(9, book.getId());
			pst.executeUpdate();
		} catch (SQLException e) {
			System.out.println("修改图书信息失败！");
			e.printStackTrace();
		}finally{
			Connect.free(rs, pst, conn);
		}
	}
	@Override
	public List<Book> queryBooksByBookID(int BookID) {
		// TODO Auto-generated method stub
		conn = Connect.getConnection();
		PreparedStatement pst = null;
		List<Book> bookList = new ArrayList<Book>();
		try {
			String sql = "select * from book where id=?;";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, BookID);
			rs = pst.executeQuery();
			while(rs.next()){
				Book book = new Book();
				book.setId(rs.getInt("id"));
				book.setName(rs.getString("name"));
				book.setType(rs.getString("type"));
				book.setAuthor(rs.getString("author"));
				book.setTranslator(rs.getString("translator"));
				book.setPublisher(rs.getString("publisher"));
				book.setPublisher_time(rs.getDate("publisher_time"));
				book.setStock(rs.getInt("stock"));
				book.setPrice(rs.getDouble("price"));
				bookList.add(book);	
			}
		} catch (SQLException e) {
			System.out.println("查询数据库信息失败！");
			e.printStackTrace();
		}finally{
			Connect.free(rs, pst, conn);
		}
		return bookList;
	}
	
}
