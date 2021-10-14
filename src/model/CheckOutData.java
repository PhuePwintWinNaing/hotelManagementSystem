package model;

public class CheckOutData {
	private int cid,rNo,totalCharges,roomCharges,deposit,balance,payAmount,reserveRoomId;
	private String name,phone,checkIn,checkOut,roType,bedType,payType;
	private int noOfNight;
	
	
	public CheckOutData(int cid,String name,String phone,int rNo,String roType,String bedType,
						String checkIn,String checkOut,int roomCharges,int noOfNight,int totalCharges,int deposit,
						int payAmount,int balance,int reserveRoomId)
	{
		this.cid = cid;
		this.name = name;
		this.phone = phone;
		this.rNo = rNo;
		this.roType = roType;
		this.bedType = bedType;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.roomCharges = roomCharges;
		this.noOfNight = noOfNight;
		this.totalCharges = totalCharges;
		this.deposit = deposit;
		this.payAmount = payAmount;
		this.balance = balance;
		this.reserveRoomId = reserveRoomId;
	}

	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public int getrNo() {
		return rNo;
	}
	public void setrNo(int rNo) {
		this.rNo = rNo;
	}
	public int getNoOfNight() {
		return noOfNight;
	}
	public void setNoOfNight(int noOfNight) {
		this.noOfNight = noOfNight;
	}
	public int getTotalCharges() {
		return totalCharges;
	}
	public void setTotalCharges(int totalCharges) {
		this.totalCharges = totalCharges;
	}
	public int getRoomCharges() {
		return roomCharges;
	}
	public void setRoomCharges(int roomCharges) {
		this.roomCharges = roomCharges;
	}
	public int getDeposit() {
		return deposit;
	}
	public void setDeposit(int deposit) {
		this.deposit = deposit;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
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
	public String getRoType() {
		return roType;
	}
	public void setRoType(String roType) {
		this.roType = roType;
	}
	public String getBedType() {
		return bedType;
	}
	public void setBedType(String bedType) {
		this.bedType = bedType;
	}

	public int getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(int payAmount) {
		this.payAmount = payAmount;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public int getReserveRoomId() {
		return reserveRoomId;
	}

	public void setReserveRoomId(int reserveRoomId) {
		this.reserveRoomId = reserveRoomId;
	}

	@Override
	public String toString() {
		return "CheckOutData [cid=" + cid + ", rNo=" + rNo + ", noOfNight=" + noOfNight + ", totalCharges="
				+ totalCharges + ", roomCharges=" + roomCharges + ", deposit=" + deposit + ", balance=" + balance
				+ ", payAmount=" + payAmount + ", reserveRoomId=" + reserveRoomId + ", name=" + name + ", phone="
				+ phone + ", checkIn=" + checkIn + ", checkOut=" + checkOut + ", roType=" + roType + ", bedType="
				+ bedType + ", payType=" + payType + "]";
	}

	
	
	
	
	
	
	
	

}
