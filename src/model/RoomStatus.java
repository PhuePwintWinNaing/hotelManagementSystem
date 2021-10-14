package model;

public class RoomStatus {
	private int roomId,roomNo;
	private int no=1;
	String roomType,roomStatus,checkInDate,checkOutDate;
	
	public RoomStatus(int no,int roomId,int roomNo,String roomType)
	{
		this.no = no;
		this.roomId = roomId;
		this.roomNo = roomNo;
		this.roomType = roomType;
	}

	
	public int getNo() {
		return no;
	}


	public void setNo(int no) {
		this.no = no;
	}


	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public int getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getRoomStatus() {
		return roomStatus;
	}

	public void setRoomStatus(String roomStatus) {
		this.roomStatus = roomStatus;
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

	@Override
	public String toString() {
		return "RoomStatus [roomId=" + roomId + ", roomNo=" + roomNo + ", roomType=" + roomType + ", roomStatus="
				+ roomStatus + ", checkInDate=" + checkInDate + ", checkOutDate=" + checkOutDate + "]";
	}
	

}
