package model;


public class Payment {
	private String payDate;
	private int pId,payAmount,payTypeId,reserveRoomId;
	
	public Payment(int pId,String payDate, int payAmount, int payTypeId,int reserveRoomId) {
		super();
		this.pId = pId;
		this.payDate = payDate;
		this.payAmount = payAmount;
		this.payTypeId = payTypeId;
		this.reserveRoomId = reserveRoomId;
	}
	

	public int getpId() {
		return pId;
	}
	public void setpId(int pId) {
		this.pId = pId;
	}
	public int getPayAmount() {
		return payAmount;
	}
	public void setPayAmount(int payAmount) {
		this.payAmount = payAmount;
	}
	public int getPayTypeId() {
		return payTypeId;
	}
	public void setPayTypeId(int payTypeId) {
		this.payTypeId = payTypeId;
	}


	public int getReserveRoomId() {
		return reserveRoomId;
	}


	public void setReserveRoomId(int reserveRoomId) {
		this.reserveRoomId = reserveRoomId;
	}


	public String getPayDate() {
		return payDate;
	}


	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}


	@Override
	public String toString() {
		return "Payment [payDate=" + payDate + ", pId=" + pId + ", payAmount=" + payAmount + ", payTypeId=" + payTypeId
				+ ", reserveRoomId=" + reserveRoomId + "]";
	}


	
	
	
	
	
}
