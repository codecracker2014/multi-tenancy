package com.paccounting.entities;

import javax.persistence.Entity;

@Entity
public class Borrower extends User{
	

	private double amount;

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
}
