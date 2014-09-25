package impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.Connect;
import dao.ReaderDao;
import entity.Reader;



public class IReader implements ReaderDao{
	private static Connection conn = null;
	private static PreparedStatement pst = null;
	private static ResultSet rs = null;
	@Override
	public List<Reader> queryAllReader() {
		// TODO Auto-generated method stub
		
		conn = Connect.getConnection();
		List<Reader> readerList = new ArrayList<Reader>();
		try {
			String sql = "select * from reader;";
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery(sql);
			while(rs.next()){
				Reader reader = new Reader();
				reader.setUserName(rs.getString("username"));
				reader.setName(rs.getString("name"));
				reader.setSex(rs.getString("sex"));
				reader.setType(rs.getString("type"));
				reader.setMax_num(rs.getInt("max_num"));
				reader.setDays_num(rs.getInt("days_num"));
				
				readerList.add(reader);	
			}
		} catch (SQLException e) {
			System.out.println("查询数据库信息失败！");
			e.printStackTrace();
		}finally{
			Connect.free(rs, pst, conn);
		}
		return readerList;
	}

	@Override
	public Reader queryCurrentUserInfo() {
		// TODO Auto-generated method stub
		conn = Connect.getConnection();
		String sql = "select * from reader where username = ?";
		Reader reader = new Reader();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, ILogin.currentUser);
			rs = pst.executeQuery();
			if(rs.next()){
				reader.setUserName(rs.getString("username"));
				reader.setPassword(rs.getString("password"));
				reader.setName(rs.getString("name"));
				reader.setSex(rs.getString("sex"));
				reader.setType(rs.getString("type"));
				reader.setMax_num(rs.getInt("max_num"));
				reader.setDays_num(rs.getInt("days_num"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return reader;
	}

	@Override
	public List<Reader> queryReaderByName(String name) {
		// TODO Auto-generated method stub
		conn = Connect.getConnection();
		PreparedStatement pst = null;
		List<Reader> readerList = new ArrayList<Reader>();
		try {
			String sql =String.format("select * from reader where name like '%s';", "%"+name+"%");
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while(rs.next()){
				Reader reader = new Reader();
				reader.setUserName(rs.getString("username"));
				reader.setName(rs.getString("name"));
				reader.setSex(rs.getString("sex"));
				reader.setType(rs.getString("type"));
				reader.setMax_num(rs.getInt("max_num"));
				reader.setDays_num(rs.getInt("days_num"));
				readerList.add(reader);	
			}
		} catch (SQLException e) {
			System.out.println("查询数据库信息失败！");
			e.printStackTrace();
		}finally{
			Connect.free(rs, pst, conn);
		}
		return readerList;
	}
	@Override
	public List<Reader> queryReaderByUserName(String userName) {
		// TODO Auto-generated method stub
		conn = Connect.getConnection();
		PreparedStatement pst = null;
		List<Reader> readerList = new ArrayList<Reader>();
		try {
			String sql = "select * from reader where username=?;";
			pst = conn.prepareStatement(sql);
			pst.setString(1, userName);
			rs = pst.executeQuery();
			while(rs.next()){
				Reader reader = new Reader();
				reader.setUserName(rs.getString("username"));
				reader.setName(rs.getString("name"));
				reader.setSex(rs.getString("sex"));
				reader.setType(rs.getString("type"));
				reader.setMax_num(rs.getInt("max_num"));
				reader.setDays_num(rs.getInt("days_num"));
				readerList.add(reader);	
			}
		} catch (SQLException e) {
			System.out.println("查询数据库信息失败！");
			e.printStackTrace();
		}finally{
			Connect.free(rs, pst, conn);
		}
		return readerList;
	}
	@Override
	public void rePassword(String password) {
		// TODO Auto-generated method stub
		conn = Connect.getConnection();
		PreparedStatement pst = null;
		try {
			String sql = "update reader set password=? where username=?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, password);
			pst.setString(2, ILogin.currentUser);
			pst.executeUpdate();
		} catch (SQLException e) {
			System.out.println("修改数据库信息失败！");
			e.printStackTrace();
		}finally{
			Connect.free(rs, pst, conn);
		}
	}

	@Override
	public void addReader(Reader reader) {
		// TODO Auto-generated method stub
		conn = Connect.getConnection();
		PreparedStatement pst = null;
		try {
			String sql = "insert into reader (username,name,sex,type,max_num,days_num) values(?,?,?,?,?,?);";
			pst = conn.prepareStatement(sql);
			pst.setString(1, reader.getUserName());
			pst.setString(2, reader.getName());
			pst.setString(3, reader.getSex()); 
			pst.setString(4, reader.getType()); 
			pst.setInt(5, reader.getMax_num());
			pst.setInt(6, reader.getDays_num());
			pst.executeUpdate();
		} catch (SQLException e) {
			System.out.println("添加读者信息失败！");
			e.printStackTrace();
		}finally{
			Connect.free(rs, pst, conn);
		}
	}

	@Override
	public void delReader(String userName) {
		// TODO Auto-generated method stub
		conn = Connect.getConnection();
		PreparedStatement pst = null;
		try {
			String sql = "delete from reader where username=?;";
			pst = conn.prepareStatement(sql);
			pst.setString(1, userName);
			pst.executeUpdate();
		} catch (SQLException e) {
			System.out.println("删除读者信息失败！");
			e.printStackTrace();
		}finally{
			Connect.free(rs, pst, conn);
		}
	}

	@Override
	public void updateReader(Reader reader, String userName) {
		// TODO Auto-generated method stub
		conn = Connect.getConnection();
		PreparedStatement pst = null;
		try {
			String sql = "update reader set name=?,sex=?,type=?,max_num=?,days_num=? where userName=?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, reader.getName());	
			pst.setString(2, reader.getSex()); 
			pst.setString(3, reader.getType()); 
			pst.setInt(4, reader.getMax_num());
			pst.setInt(5, reader.getDays_num());
			pst.setString(6, userName);	
			pst.executeUpdate();
		} catch (SQLException e) {
			System.out.println("修改读者信息失败！");
			e.printStackTrace();
		}finally{
			Connect.free(rs, pst, conn);
		}
	}

	
}
