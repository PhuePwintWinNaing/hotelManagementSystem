package model;


public class Receptionist {
	private int id,no=1;
	private String name,staffId,phone,nrc,address,password,deleteDate;
	
	public Receptionist()
	{
		
	}
	
	public Receptionist(int no, int id,String name, String staffId, String phone, String nrc, String address) {
		super();
		this.no = no;
		this.id = id;
		this.name = name;
		this.staffId = staffId;
		this.phone = phone;
		this.nrc = nrc;
		this.address = address;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStaffId() {
		return staffId;
	}
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getNrc() {
		return nrc;
	}
	public void setNrc(String nrc) {
		this.nrc = nrc;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getDeleteDate() {
		return deleteDate;
	}
	public void setDeleteDate(String deleteDate) {
		this.deleteDate = deleteDate;
	}
	@Override
	public String toString() {
		return staffId+"";
	}
	
	
	


	
	
	
	
}
