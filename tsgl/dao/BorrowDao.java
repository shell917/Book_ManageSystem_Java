package dao;

import java.util.List;

import entity.Borrow;

public interface BorrowDao {
	public void addBorrowInfo(Borrow borrow);
	public List<Borrow> queryAllBorrowInfo(String userName);
	public void delBorrowInfo(String bookID);
}
