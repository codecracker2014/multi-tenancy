package com.paccounting.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;



@Entity
public class Transaction {

	@Id
	private String id;
	private String type;
	
	@OneToMany(cascade={CascadeType.ALL},fetch=FetchType.EAGER)
	@JoinColumn(name="payer_id")
	private List<Payer> payers;
	@OneToMany(cascade={CascadeType.ALL},fetch=FetchType.EAGER)
	@JoinColumn(name="borrower_id")
	private List<Borrower>borrowers;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<Payer> getPayers() {
		return payers;
	}
	public void setPayers(List<Payer> payers) {
		this.payers = payers;
	}
	public List<Borrower> getBorrowers() {
		return borrowers;
	}
	public void setBorrowers(List<Borrower> borrowers) {
		this.borrowers = borrowers;
	}
	
}
