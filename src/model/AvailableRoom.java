package model;

public class AvailableRoom {
	private int roomNo;
	
	public AvailableRoom(int roomNo) {
		super();
		this.roomNo = roomNo;
	}


	public int getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}

	
	@Override
	public String toString() {
		return  roomNo+"";
	}
	
	
}
