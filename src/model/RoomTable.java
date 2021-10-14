package model;

public class RoomTable {

	private int roomId,roomNo;
	private String roomType;
	public RoomTable(int roomId, int roomNo, String roomType) {
		super();
		this.roomId = roomId;
		this.roomNo = roomNo;
		this.roomType = roomType;
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
	@Override
	public String toString() {
		return "RoomTable [roomId=" + roomId + ", roomNo=" + roomNo + ", roomType=" + roomType + "]";
	}
	
	
	
	
}
