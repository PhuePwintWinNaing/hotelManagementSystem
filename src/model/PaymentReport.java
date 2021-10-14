package model;

public class PaymentReport {
	private String paymentType;
	private int paymentAmount;
	public PaymentReport(String paymentType, int paymentAmount) {
		super();
		this.paymentType = paymentType;
		this.paymentAmount = paymentAmount;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public int getPaymentAmount() {
		return paymentAmount;
	}
	public void setPaymentAmount(int paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
	@Override
	public String toString() {
		return "PaymentReport [paymentType=" + paymentType + ", paymentAmount=" + paymentAmount + "]";
	}
	
	
	
	

}
