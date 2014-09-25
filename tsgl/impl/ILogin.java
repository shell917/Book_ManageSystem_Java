package impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.Connect;
import dao.LoginDao;

public class ILogin implements LoginDao{
	public static String currentUser = null;
	private static Connection conn = null;
	private static PreparedStatement pst = null;
	private static ResultSet rs = null;
	public boolean isLogin(String userName, String password) {
		// TODO Auto-generated method stub
		conn = Connect.getConnection();
		String sql = "select password from reader where username = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, userName);
			rs = pst.executeQuery();
			if(rs.next()){
				if(rs.getString("password").equals(password)){
					currentUser = userName;
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return false;
	}
}
