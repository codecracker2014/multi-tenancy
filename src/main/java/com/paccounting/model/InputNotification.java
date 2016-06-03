package com.paccounting.model;

import com.paccounting.entities.Event;
import com.paccounting.entities.Notification;
import com.paccounting.entities.User;


public class InputNotification {

	
	private String id;
	private Event event;
	private User sender;
	
	private int status;
	
	
	public InputNotification()
	{
		
	}
	public InputNotification(Notification notification)
	{
		this.setId(notification.getId());
		this.setEvent(notification.getEvent());
		this.setSender(notification.getSender());
		this.setStatus(notification.getStatus());
		
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
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
