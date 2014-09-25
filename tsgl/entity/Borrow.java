package entity;

import java.sql.Date;

public class Borrow {
	private int ID;
	private String bookID;
	private String userName;
	private Date borrowDate;
	private Date backDate;
	public Borrow() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Borrow(int iD, String bookID, String userName, Date borrowDate,
			Date backDate) {
		super();
		ID = iD;
		this.bookID = bookID;
		this.userName = userName;
		this.borrowDate = borrowDate;
		this.backDate = backDate;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getBookID() {
		return bookID;
	}
	public void setBookID(String bookID) {
		this.bookID = bookID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getBorrowDate() {
		return borrowDate;
	}
	public void setBorrowDate(Date borrowDate) {
		this.borrowDate = borrowDate;
	}
	public Date getBackDate() {
		return backDate;
	}
	public void setBackDate(Date backDate) {
		this.backDate = backDate;
	}
	
}
