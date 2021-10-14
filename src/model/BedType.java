package model;

public class BedType {
	private int bedTypeId;
	private String bedTypes;

	public BedType(String bedTypes)
	{
		this.bedTypes = bedTypes;
	}
	public BedType(int bedTypeId,String bedTypes) {
		super();
		this.bedTypeId = bedTypeId;
		this.bedTypes = bedTypes;
	}

	public int getBedTypeId() {
		return bedTypeId;
	}

	public void setBedTypeId(int bedTypeId) {
		this.bedTypeId = bedTypeId;
	}

	public String getBedTypes() {
		return bedTypes;
	}

	public void setBedTypes(String bedTypes) {
		this.bedTypes = bedTypes;
	}

	@Override
	public String toString() {
		return bedTypes;
	};
	
	
}
