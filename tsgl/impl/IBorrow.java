package impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.Connect;

import dao.BorrowDao;

import entity.Borrow;

public class IBorrow implements BorrowDao{
	private static Connection conn = null;
	private static PreparedStatement pst = null;
	private static ResultSet rs = null;
	@Override
	public List<Borrow> queryAllBorrowInfo(String userName) {
		// TODO Auto-generated method stub
		conn = Connect.getConnection();
		List<Borrow> borrowList = new ArrayList<Borrow>();
		try {
			String sql = "select * from borrow where username=?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, userName);
			rs = pst.executeQuery();
			while(rs.next()){
				Borrow borrow = new Borrow();
				borrow.setBookID(rs.getString("book_id"));
				borrow.setUserName(rs.getString("username"));
				borrow.setBorrowDate(rs.getDate("borrow_date"));
				borrow.setBackDate(rs.getDate("back_date"));
				
				borrowList.add(borrow);	
			}
		} catch (SQLException e) {
			System.out.println("查询数据库信息失败！");
			e.printStackTrace();
		}finally{
			Connect.free(rs, pst, conn);
		}
		return borrowList;
	}
	@Override
	public void addBorrowInfo(Borrow borrow) {
		// TODO Auto-generated method stub
		conn = Connect.getConnection();
		PreparedStatement pst = null;
		try {
			String sql = "insert into borrow (book_id,username,borrow_date,back_date) values(?,?,?,?);";
			pst = conn.prepareStatement(sql);
			pst.setString(1, borrow.getBookID());
			pst.setString(2, borrow.getUserName());
			pst.setDate(3, borrow.getBorrowDate());
			pst.setDate(4, borrow.getBackDate());
			pst.executeUpdate();
		} catch (SQLException e) {
			System.out.println("添加借书信息失败！");
			e.printStackTrace();
		}finally{
			Connect.free(rs, pst, conn);
		}
	}
	@Override
	public void delBorrowInfo(String bookID) {
		// TODO Auto-generated method stub
		conn = Connect.getConnection();
		PreparedStatement pst = null;
		try {
			String sql = "delete from borrow where book_id=?;";
			pst = conn.prepareStatement(sql);
			pst.setString(1, bookID);
			pst.executeUpdate();
		} catch (SQLException e) {
			System.out.println("删除借书信息失败！");
			e.printStackTrace();
		}finally{
			Connect.free(rs, pst, conn);
		}
	}
	
}
