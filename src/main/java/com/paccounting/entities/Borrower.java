package com.paccounting.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Borrower {
	

	@Id
	private String mob;
	private String name;
	
	private double amount;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMob() {
		return mob;
	}

	public void setMob(String mob) {
		this.mob = mob;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
}
