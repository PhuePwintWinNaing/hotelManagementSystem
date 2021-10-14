package model;

public class RoomType {
	private int roomTypeId;
	private String roomType;

	public RoomType(int roomTypeId,String roomType) {
		super();
		this.roomTypeId = roomTypeId;
		this.roomType = roomType;
	}

	public int getRoomTypeId() {
		return roomTypeId;
	}

	public void setRoomTypeId(int roomTypeId) {
		this.roomTypeId = roomTypeId;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	@Override
	public String toString() {
		return roomType;
	}
	
	
	

}
