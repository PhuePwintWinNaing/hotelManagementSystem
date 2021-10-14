package model;

import java.time.LocalDate;

public class Income {

	private int income;

	public Income(int income) {
		super();
		this.income = income;
	}

	public int getIncome() {
		return income;
	}

	public void setIncome(int income) {
		this.income = income;
	}

	@Override
	public String toString() {
		return income+"";
	}

	public LocalDate get(int i) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
