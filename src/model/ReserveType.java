package model;

public class ReserveType {
	private int reserveTypeId;
	private String reserveType;
	
//	public ReserveType(String reserveType)
//	{
//		this.reserveType=reserveType;
//	}

	public ReserveType(int reserveTypeId,String reserveType) {
		super();
		this.reserveTypeId= reserveTypeId;
		this.reserveType = reserveType;
	}

	public String getReserveType() {
		return reserveType;
	}

	public void setReserveType(String reserveType) {
		this.reserveType = reserveType;
	}

	public int getReserveTypeId() {
		return reserveTypeId;
	}

	public void setReserveTypeId(int reserveTypeId) {
		this.reserveTypeId = reserveTypeId;
	}

	@Override
	public String toString() {
		return reserveType;
	}

	
	
	

}
