package model;

public class RoomOccupiedReport {
	private int noOfRoomOccupied,year;
	private String month;
	
	public RoomOccupiedReport(int noOfRoomOccupied, String month, int year) {
		super();
		this.noOfRoomOccupied = noOfRoomOccupied;
		this.month = month;
		this.year = year;
	}

	public int getNoOfRoomOccupied() {
		return noOfRoomOccupied;
	}

	public void setNoOfRoomOccupied(int noOfRoomOccupied) {
		this.noOfRoomOccupied = noOfRoomOccupied;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	

	@Override
	public String toString() {
		return "RoomOccupiedReport [noOfRoomOccupied=" + noOfRoomOccupied + ", month=" + month + ", year=" + year + "]";
	}
	


}
