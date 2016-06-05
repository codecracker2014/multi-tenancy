package com.paccounting.entities;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Receiver{

	
	@Id
	private String mob;
	private boolean status;
	/*
	@ManyToMany(mappedBy="receivers",fetch=FetchType.EAGER)
	private Set<Notification> notifications=new HashSet<Notification>();
	public Set<Notification> getNotifications() {
		return notifications;
	}

	public void setNotifications(Set<Notification> notifications) {
		this.notifications = notifications;
	}
*/
	public Receiver()
	{
		
	}

	public String getMob() {
		return mob;
	}

	public void setMob(String mob) {
		this.mob = mob;
	}

	public Receiver(Borrower user/*,Set<Notification> notif*/)
	{
		this.setMob(user.getMob());
		this.setStatus(false);
		
		//this.setNotifications(notif);
	}
	public Receiver(Borrower user,boolean status)
	{
		this.setMob(user.getMob());
		this.setStatus(status);
	}

	public Receiver(Payer user/*,Set<Notification> notif*/)
	{
		this.setMob(user.getMob());
		this.setStatus(false);
		
		//this.setNotifications(notif);
	}
	public Receiver(Payer user,boolean status)
	{
		this.setMob(user.getMob());
		this.setStatus(status);
	}
	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
}
