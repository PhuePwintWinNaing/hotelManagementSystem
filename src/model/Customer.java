package model;

public class Customer {
	private int cId;
	private String name,phone;
	
	public Customer(int cId)
	{
		this.cId = cId;
	}
	public Customer(String name, String phone) {
		super();
		
		this.name = name;
		this.phone = phone;
	}
	public  int getcId() {
		return cId;
	}
	public void setcId(int id) {
		cId = id;
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
	@Override
	public String toString() {
		return cId+"";
	}
		
	

}
