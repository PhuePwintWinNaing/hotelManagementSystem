package model;

public class RoomNo {
	private int  roomId,roomNo;
	
	
	public RoomNo(int roomNo)
	{
		this.roomNo = roomNo;
	}
	public RoomNo(int roomId,int roomNo)
	{
		this.roomId = roomId;
		this.roomNo = roomNo;
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


	@Override
	public String toString() {
		return roomNo+"";
	}
	
	
	

}
