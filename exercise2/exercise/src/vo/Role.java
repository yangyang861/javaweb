package vo;

public class Role {
	private int roleID;
	private String roleName;
	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Role(int roleID, String roleName) {
		super();
		this.roleID = roleID;
		this.roleName = roleName;
	}
	public int getRoleID() {
		return roleID;
	}
	public void setRoleID(int roleID) {
		this.roleID = roleID;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	@Override
	public String toString() {
		return "Role [roleID=" + roleID + ", roleName=" + roleName + "]";
	}
	

}
