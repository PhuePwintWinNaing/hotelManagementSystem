package model;

public class Guest {
	private int guestId;
	private int no=1;
	private String name,nrc,phone,email,address;

	
	public Guest(int no,int guestId,String name, String phone,String nrc, String email, String address) {
		super();
		this.no = no;
		this.guestId = guestId;
		this.name = name;
		this.phone = phone;
		this.nrc = nrc;
		
		this.email = email;
		this.address = address;
	}

	
	
	public int getNo() {
		return no;
	}



	public void setNo(int no) {
		this.no = no;
	}



	public int getGuestId() {
		return guestId;
	}

	public void setGuestId(int guestId) {
		this.guestId = guestId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNrc() {
		return nrc;
	}

	public void setNrc(String nrc) {
		this.nrc = nrc;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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
	@Override
	public String toString() {
		return "GuestLists [name=" + name + ", nrc=" + nrc + ", phone=" + phone + ", email=" + email + ", address="
				+ address + "]";
	}

	

}
