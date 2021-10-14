package model;

public class PaymentType {
	private int paymentTypeId,paymentAmount;
	private String paymentType;
	public PaymentType(String paymentType)
	{
		this.paymentType = paymentType;
	}
	
	public PaymentType(int paymentAmount, String paymentType) {
		super();
		this.paymentAmount = paymentAmount;
		this.paymentType = paymentType;
		
	}
	public int getPaymentTypeId() {
		return paymentTypeId;
	}
	public void setPaymentTypeId(int paymentTypeId) {
		this.paymentTypeId = paymentTypeId;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	

	@Override
	public String toString() {
		return paymentType;
	}
	


}
