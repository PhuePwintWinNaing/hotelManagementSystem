package model;

public class GuestProfile {
	private int No=1,roomNo;
	private String name,phone,nrc,email,address,checkIn,checkOut;
	public GuestProfile(int no,int roomNo, String name, String phone, String nrc, String email, String address, String checkIn,
			String checkOut) {
		super();
		No = no;
		this.roomNo = roomNo;
		this.name = name;
		this.phone = phone;
		this.nrc = nrc;
		this.email = email;
		this.address = address;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}

	public int getNo() {
		return No;
	}
	public void setNo(int no) {
		No = no;
	}
	
	public int getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCheckIn() {
		return checkIn;
	}
	public void setCheckIn(String checkIn) {
		this.checkIn = checkIn;
	}
	public String getCheckOut() {
		return checkOut;
	}
	public void setCheckOut(String checkOut) {
		this.checkOut = checkOut;
	}
	@Override
	public String toString() {
		return "GuestProfile [no=" + No + ", roomNo=" + roomNo + ", name=" + name + ", phone=" + phone + ", nrc=" + nrc
				+ ", email=" + email + ", address=" + address + ", checkIn=" + checkIn + ", checkOut=" + checkOut + "]";
	}
	
	
	

}
