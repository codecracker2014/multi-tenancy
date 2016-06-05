package com.paccounting.entities;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.hibernate.engine.internal.Cascade;

@Entity
public class Notification {

	@Id
	private String id;
	@OneToOne(cascade={CascadeType.ALL},fetch=FetchType.EAGER)
	@JoinColumn(name="event_id")
	private Event event;
	
	private int status;
	

	public enum Status{
		READY(0),SENT(1),ACCEPTED(2),REJECTED(3),NEW(4),MODIFIED(5),DELETED(6);
		private int value; 
		private Status(int value) { this.value = value; }
	}
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="sender_not_id")
	private User sender;

	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		if(status ==Notification.Status.NEW.ordinal()|| status ==Notification.Status.MODIFIED.ordinal()||status==Notification.Status.DELETED.ordinal())
		{
			this.status = status;
		}
		else
		{
			throw new IllegalArgumentException();
		}
		
	}
	/*@ManyToMany(cascade={CascadeType.ALL},fetch=FetchType.EAGER)
	@JoinTable(name="notf_rec",joinColumns={@JoinColumn(name="notification_id")},inverseJoinColumns={@JoinColumn(name="rec_mob")})
	private Set<Receiver> receivers=new HashSet<Receiver>();
	
	public Set<Receiver> getReceivers() {
		return receivers;
	}
	public void setReceivers(Set<Receiver> receivers) {
		this.receivers = receivers;
	}*/
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Event getEvent() {
		return event;
	}
	public void setEvent(Event event) {
		this.event = event;
	}
	public User getSender() {
		return sender;
	}
	public void setSender(User sender) {
		this.sender = sender;
	}
	
}
