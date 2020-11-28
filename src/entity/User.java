package entity;

/**
 * 操作用户
 *
 */
public class User {
	/**
	 * 普通用户权限
	 */
	public final static String GUEST = "guest";// 用在用户信息页面做选择
	/**
	 * 管理员权限
	 */
	public final static String ADMIN = "admin";// 用在用户信息页面做选择
	/**
	 * 员工权限
	 */
	public final static String CLERK = "clerk";// 用在用户信息页面做选择

	private String name;// 账号
	private String password;// 密码
	private String status;// 身份

	public User() {
	}

	public User(String name, String password, String status) {
		super();
		this.name = name;
		this.password = password;
		this.status = status;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "User  [account=" + name + ", password="
				+ password + ", status=" + status  + "]";
	}

}
