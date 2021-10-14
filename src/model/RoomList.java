package model;

public class RoomList {
	private int roomId,roomNo,roomCharges;
	private int No=1;
	private String roomType,bedType;
	public RoomList(int no, int roomId,int roomNo,String roomType, String bedType, int roomCharges) {
		super();
		No = no;
		this.roomId = roomId;
		this.roomNo = roomNo;
		this.roomType = roomType;
		this.bedType = bedType;
		this.roomCharges = roomCharges;
	}
	
	
public int getNo() {
		return No;
	}


	public void setNo(int no) {
		No = no;
	}


	public int getRoomId() 
	{
		return roomId;
	}
	public void setRoomId(int roomId) 
	{
	this.roomId = roomId;
	}
	public int getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
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
		return "RoomList [no=" + No + ", roomNo=" + roomNo + ", roomCharges=" + roomCharges + ", roomType=" + roomType
				+ ", bedType=" + bedType + "]";
	}

	
	
	
	
	
	
	

}
