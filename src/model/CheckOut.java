package model;

public class CheckOut {
	private int reserveId,checkInId,roomCharges,deposit,noOfNights,roomNo;
	private int no=1;
	private String name,phone,roomType,bedType,reserveType,receptionistName;
	private String checkInDate,checkOutDate,reserveDate,checkInTime,checkOutTime;
	
	public CheckOut(int no,int reserveId, int checkInId,String name, String phone,String reserveDate,
			String checkInDate,String checkInTime, String checkOutDate,String checkOutTime, int noOfNights, int roomNo,  String roomType, 
			String bedType,int roomCharges, int deposit,String reserveType,String receptionistName
			)
	{
		
		super();
		this.no = no;
		this.reserveId = reserveId;
		this.checkInId = checkInId;
		this.name = name;
		this.phone = phone;
		this.reserveDate = reserveDate;
		this.checkInDate = checkInDate;
		this.checkInTime = checkInTime;
		this.checkOutDate = checkOutDate;
		
		this.checkOutTime = checkOutTime;
		this.noOfNights = noOfNights;
		this.roomNo = roomNo;
		this.roomType = roomType;
		this.bedType = bedType;
		this.roomCharges = roomCharges;
		this.deposit = deposit;
		this.reserveType = reserveType;
		this.receptionistName = receptionistName;
	
	}
	
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getReserveId() {
		return reserveId;
	}
	public void setReserveId(int reserveId) {
		this.reserveId = reserveId;
	}
	public int getCheckInId() {
		return checkInId;
	}
	public void setCheckInId(int checkInId) {
		this.checkInId = checkInId;
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
	public int getNoOfNights() {
		return noOfNights;
	}
	public void setNoOfNights(int noOfNights) {
		this.noOfNights = noOfNights;
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
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	public String getBedType() {
		return bedType;
	}
	public void setBedType(String bedType) {
		this.bedType = bedType;
	}
	public String getReserveType() {
		return reserveType;
	}
	public void setReserveType(String reserveType) {
		this.reserveType = reserveType;
	}
	public String getReceptionistName() {
		return receptionistName;
	}
	public void setReceptionistName(String receptionistName) {
		this.receptionistName = receptionistName;
	}
	public String getCheckInDate() {
		return checkInDate;
	}
	public void setCheckInDate(String checkInDate) {
		this.checkInDate = checkInDate;
	}
	public String getCheckOutDate() {
		return checkOutDate;
	}
	public void setCheckOutDate(String checkOutDate) {
		this.checkOutDate = checkOutDate;
	}
	public String getReserveDate() {
		return reserveDate;
	}
	public void setReserveDate(String reserveDate) {
		this.reserveDate = reserveDate;
	}
	public String getCheckInTime() {
		return checkInTime;
	}
	public void setCheckInTime(String checkInTime) {
		this.checkInTime = checkInTime;
	}
	public String getCheckOutTime() {
		return checkOutTime;
	}
	public void setCheckOutTime(String checkOutTime) {
		this.checkOutTime = checkOutTime;
	}
	@Override
	public String toString() {
		return "CheckOut [reserveId=" + reserveId + ", checkInId=" + checkInId + ", roomCharges=" + roomCharges
				+ ", deposit=" + deposit + ", noOfNights=" + noOfNights + ", roomNo=" + roomNo + ", name=" + name
				+ ", phone=" + phone + ", roomType=" + roomType + ", bedType=" + bedType + ", reserveType="
				+ reserveType + ", receptionistName=" + receptionistName + ", checkInDate=" + checkInDate
				+ ", checkOutDate=" + checkOutDate + ", reserveDate=" + reserveDate + ", checkInTime=" + checkInTime
				+ ", checkOutTime=" + checkOutTime + "]";
	}
	
	
	

}
