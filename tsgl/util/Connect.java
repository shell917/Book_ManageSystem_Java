package util;
import java.sql.*;
import java.lang.*;
import java.util.*;

public class Connect {
	static Connection conn = null;
	static Statement st = null;
	static ResultSet rs = null;
	public static Connection getConnection(){
		//�������ݿ�����
		GetProperty getProperty = new GetProperty();
		try {
			//���� forName("X") ����������Ϊ X ���౻��ʼ����
			Class.forName(getProperty.getDrivername());
//			System.out.println("�������ݿ������ɹ�");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//			System.out.println("�������ݿ�����ʧ��");
		}
		//�������ݿ�����
		String url = getProperty.getUrl();
		String username = getProperty.getUsername();
		String password = getProperty.getPassword();
		try {
			conn = DriverManager.getConnection(url, username, password);
//			System.out.println("�������ݿ����ӳɹ�");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//			System.out.println("�������ݿ�����ʧ��");
		}
		return conn;
	}
	//�ر����ݿ�����
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