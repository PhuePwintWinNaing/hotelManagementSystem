package model;
import java.util.Date;

public class Reservation {
	private static int id;
	private int no=1;
	private int reserveId,roomCharges,deposit,noOfNights,reserveRoomId;
	private String customerName,customerPhone,roomNo,roomType,bedType,reserveType;
	private Date checkInDate,checkOutDate,reserveDate;
	
	
	public Reservation(int no,int reserveId,String customerName,String customerPhone, Date reserveDate,Date checkInDate, Date checkOutDate,
			int noOfNights,String roomNo, String roomType, String bedType,int roomCharges, int deposit,
			String reserveType,int reserveRoomId)
	{
		super();
		this.no = no;
		this.reserveId = reserveId;
		this.customerName = customerName;
		this.customerPhone = customerPhone;
		this.reserveDate = reserveDate;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.noOfNights = noOfNights;
		this.roomNo = roomNo;
		this.roomType = roomType;
		this.bedType = bedType;
		this.roomCharges = roomCharges;
		this.deposit = deposit;
		this.reserveType = reserveType;
		this.reserveRoomId = reserveRoomId;

	}
	public static int getId() {
		return id;
	}
	
	public static void setId(int rId)
	{
		id = rId;
	}
	

	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public  int getReserveId() {
		return reserveId;
	}
	public  void setReserveId(int rid) {
		reserveId = rid;
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
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerPhone() {
		return customerPhone;
	}
	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}
	public String getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
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
	public Date getCheckInDate() {
		return checkInDate;
	}
	public void setCheckInDate(Date checkInDate) {
		this.checkInDate = checkInDate;
	}
	public Date getCheckOutDate() {
		return checkOutDate;
	}
	public void setCheckOutDate(Date checkOutDate) {
		this.checkOutDate = checkOutDate;
	}
	public Date getReserveDate() {
		return reserveDate;
	}
	public void setReserveDate(Date reserveDate) {
		this.reserveDate = reserveDate;
	}
	

	public int getReserveRoomId() {
		return reserveRoomId;
	}
	public void setReserveRoomId(int reserveRoomId) {
		this.reserveRoomId = reserveRoomId;
	}
	@Override
	public String toString() {
		return "Reservation [reserveId=" + reserveId + ", roomCharges=" + roomCharges + ", deposit=" + deposit
				+ ", noOfNights=" + noOfNights + ", reserveRoomId=" + reserveRoomId + ", customerName=" + customerName
				+ ", customerPhone=" + customerPhone + ", roomNo=" + roomNo + ", roomType=" + roomType + ", bedType="
				+ bedType + ", reserveType=" + reserveType + ", checkInDate=" + checkInDate + ", checkOutDate="
				+ checkOutDate + ", reserveDate=" + reserveDate + "]";
	}

	
	
	
	
	
	

}
