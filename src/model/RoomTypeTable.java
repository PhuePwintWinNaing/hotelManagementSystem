package model;

public class RoomTypeTable {
	private int roomTypeId,roomCharges;
	private int no=1;
	private String roomType,bedType;
	public RoomTypeTable(int no, String roomType, String bedType, int roomCharges) {
		super();
		this.no = no;
		this.roomType = roomType;
		this.bedType = bedType;
		this.roomCharges = roomCharges;
	}
	
	
	
	
	public int getNo() {
		return no;
	}




	public void setNo(int no) {
		this.no = no;
	}




	public int getRoomTypeId() {
		return roomTypeId;
	}
	public void setRoomTypeId(int roomTypeId) {
		this.roomTypeId = roomTypeId;
	}
	public int getRoomCharges() {
		return roomCharges;
	}
	public void setRoomCharges(int roomCharges) {
		this.roomCharges = roomCharges;
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
	@Override
	public String toString() {
		return "RoomTypeTable [roomTypeId=" + roomTypeId + ", roomCharges=" + roomCharges + ", roomType=" + roomType
				+ ", bedType=" + bedType + "]";
	}
	
	
	
	
	
	
	

}
