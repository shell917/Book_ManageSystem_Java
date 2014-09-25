package util;
import java.sql.*;
import java.lang.*;
import java.util.*;

public class Connect {
	static Connection conn = null;
	static Statement st = null;
	static ResultSet rs = null;
	public static Connection getConnection(){
		//加载数据库驱动
		GetProperty getProperty = new GetProperty();
		try {
			//调用 forName("X") 将导致命名为 X 的类被初始化。
			Class.forName(getProperty.getDrivername());
//			System.out.println("加载数据库驱动成功");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//			System.out.println("加载数据库驱动失败");
		}
		//建立数据库连接
		String url = getProperty.getUrl();
		String username = getProperty.getUsername();
		String password = getProperty.getPassword();
		try {
			conn = DriverManager.getConnection(url, username, password);
//			System.out.println("建立数据库连接成功");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//			System.out.println("建立数据库连接失败");
		}
		return conn;
	}
	//关闭数据库连接
	public static void free(ResultSet rs, Statement st, Connection conn){
		if(rs!=null)
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				if(st!=null)
					try {
						st.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}finally{
						if(conn!=null)
							try {
								conn.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}
					}
			}
	}
	/*public static void main(String[] args) {
		getConnection();
	}*/
}