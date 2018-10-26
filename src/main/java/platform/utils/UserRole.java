package platform.utils;

public enum UserRole {
	admin("admin"),
	teacher("teacher"),
	student("student");
	
	private String userRole;
	
	private UserRole() {}

	private UserRole(final String userRole) {
		this.userRole = userRole;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
}
