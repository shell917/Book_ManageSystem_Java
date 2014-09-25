package entity;

public class Reader {
	private int id;
	private String userName;
	private String password;
	private String name;
	private String sex;
	private String type;
	private int max_num;
	private int days_num;
	public Reader() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Reader(int id, String userName, String password, String name,
			String sex, String type, int maxNum, int daysNum) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.name = name;
		this.sex = sex;
		this.type = type;
		max_num = maxNum;
		days_num = daysNum;
	}
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getMax_num() {
		return max_num;
	}
	public void setMax_num(int maxNum) {
		max_num = maxNum;
	}
	public int getDays_num() {
		return days_num;
	}
	public void setDays_num(int daysNum) {
		days_num = daysNum;
	}
	
}
