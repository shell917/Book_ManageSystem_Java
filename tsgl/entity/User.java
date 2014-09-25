package entity;

public class User {
	private int id;
	private String name;
	private String password;
	private int is_admin;
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(int id, String name, String password, int isAdmin) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		is_admin = isAdmin;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getIs_admin() {
		return is_admin;
	}
	public void setIs_admin(int isAdmin) {
		is_admin = isAdmin;
	}
	
}
