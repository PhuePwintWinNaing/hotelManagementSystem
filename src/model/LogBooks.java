package model;

public class LogBooks {
	private int id;
	private String receptionistName,loginDate,loginTime,logoutDate,logoutTime;
	public LogBooks(int id, String receptionistName,String loginDate, String loginTime, String logoutDate, String logoutTime) {
		super();
		this.id = id;
		this.receptionistName = receptionistName;
		this.loginDate = loginDate;
		this.loginTime = loginTime;
		this.logoutDate = logoutDate;
		this.logoutTime = logoutTime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getReceptionistName() {
		return receptionistName;
	}
	public void setReceptionistName(String receptionistName) {
		this.receptionistName = receptionistName;
	}
	public String getLoginDate() {
		return loginDate;
	}
	public void setLoginDate(String loginDate) {
		this.loginDate = loginDate;
	}
	public String getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}
	public String getLogoutDate() {
		return logoutDate;
	}
	public void setLogoutDate(String logoutDate) {
		this.logoutDate = logoutDate;
	}
	public String getLogoutTime() {
		return logoutTime;
	}
	public void setLogoutTime(String logoutTime) {
		this.logoutTime = logoutTime;
	}
	@Override
	public String toString() {
		return "LogBooks [id=" + id + ", loginDate=" + loginDate + ", loginTime=" + loginTime + ", logoutDate="
				+ logoutDate + ", logoutTime=" + logoutTime + "]";
	}
	
	

}
