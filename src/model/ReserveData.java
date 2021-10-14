package model;
import view.ReservationForm;

public class ReserveData {

	private String name,phone,roomNo,reserveDate,checkInDate,checkOutDate;
	private BedType bedType;
	private RoomType roomType;
	private int roomCharges,deposit;
	
	public ReserveData()
	{
		
	}
	
	public ReserveData(String name, String phone, String roomNo, String reserveDate, String checkInDate,
			String checkOutDate, BedType bedType, RoomType roomType, int roomCharges,
			int deposit) {
		super();
		this.name = name;
		this.phone = phone;
		this.roomNo = roomNo;
		this.reserveDate = reserveDate;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.bedType = bedType;
		this.roomType = roomType;
		this.roomCharges = roomCharges;
		this.deposit = deposit;
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

	public String getReserveDate() {
		return reserveDate;
	}

	public void setReserveDate(String reserveDate) {
		this.reserveDate = reserveDate;
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

	public RoomType getRoomType() {
		return roomType;
	}

	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}

	public BedType getBedType() {
		return bedType;
	}

	public void setBedType(BedType bedType) {
		this.bedType = bedType;
	}

	public String getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
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
	

	
	
	
	
	
	

	
	
	
	
	
}
