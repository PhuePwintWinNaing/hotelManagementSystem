package model;

public class IncomeReport{
	private int total,year;
	private String month;

	public IncomeReport(int total, String month, int year) {
		super();
		this.total = total;
		this.month = month;
		this.year = year;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
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
		return "IncomeReport [total=" + total + ", month=" + month + ", year=" + year + "]";
	}
	
	

}
