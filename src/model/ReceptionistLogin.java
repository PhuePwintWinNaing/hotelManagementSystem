package model;

public class ReceptionistLogin {
	private String staffId,password;

	public ReceptionistLogin(String staffId, String password) {
		super();
		this.staffId = staffId;
		this.password = password;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "ReceptionistStaffIdAndPassword [staffId=" + staffId + ", password=" + password + "]";
	}
	
	
	
}
