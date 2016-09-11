package com.paccounting.controllers;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.paccounting.entities.Borrower;
import com.paccounting.entities.Event;
import com.paccounting.entities.Payer;
import com.paccounting.entities.Transaction;
import com.paccounting.entities.User;
import com.paccounting.model.InputNotification;
import com.paccounting.model.NewUser;
import com.paccounting.security.PhoneVerificationService;
import com.paccounting.services.NotificationService;
import com.paccounting.services.UserService;

@RestController
public class RstController {

	
	@Autowired
	private NotificationService notificationService;
	@Autowired
	private UserService userService;
	@Autowired
	private PhoneVerificationService phoneVerificationService;
	@RequestMapping(value="addEvent",method=RequestMethod.POST,consumes="application/json")
	public HttpStatus addEvent(@RequestBody InputNotification inputNotification)
	{
		System.out.println("Got Input ");
		System.out.println(inputNotification.getEvent().getName());
		if(notificationService.addNotification(inputNotification))
		return HttpStatus.ACCEPTED;
		else
			return HttpStatus.BAD_REQUEST;
	}
	
	@RequestMapping(value="getNotification/{id}",method=RequestMethod.POST)
	public List<InputNotification> getNotification(@PathVariable String id) {
		System.out.println(id);
		List<InputNotification>list=notificationService.getNotifications(id)	;
		return list;
	}
	
	@RequestMapping(value="getnot",method=RequestMethod.POST)
	public InputNotification getSample() {
		Transaction t=new Transaction();
		t.setId("424");
		t.setType("simpel");
		Payer p=new Payer();
		p.setMob("4242424");
		p.setName("gaj");
		p.setAmount(113);
		
		Borrower b=new Borrower();
		b.setMob("2424535");
		b.setName("narendra");
		b.setAmount(13333);
		
		List<Payer>payers=new ArrayList<Payer>();
		payers.add(p);
		List<Borrower>borowers=new ArrayList<Borrower>();
		borowers.add(b);
		t.setBorrowers(borowers);
		t.setPayers(payers);
		
		Event e=new  Event();
		e.setDate(new Date(2016, 5, 15));
		e.setId("evn224");
		e.setName("shopping");
		e.setPlace("Noida");
		
		List<Transaction>transactions=new ArrayList<Transaction>();
		
		transactions.add(t);
		e.setTransactions(transactions);
		
		InputNotification in=new InputNotification();
		
		in.setEvent(e);
		in.setId("in24254");
		
		User sender=new User();
		sender.setMob("434646646");
		sender.setName("sfsff");
		
		in.setSender(sender);
		in.setStatus(5);
		return in;
	}
  
	
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public HttpStatus register(@RequestBody NewUser newUser) {
		System.out.println("Registering user");
		for(int i=0;i<2;i++)
		{
			int r=phoneVerificationService.sendOtp("91", newUser);
			if(r==0)
			{
				return HttpStatus.ACCEPTED;
			}
			else if(r==1)
			{
				return HttpStatus.ALREADY_REPORTED;
			}
			
				
		}
		return HttpStatus.INTERNAL_SERVER_ERROR;
		
	}
	
	@RequestMapping(value="/verify",method=RequestMethod.POST)
	public HttpStatus verifyAndRegister(@RequestBody NewUser newUser) {
		if(phoneVerificationService.verify(newUser))
		{
			if(userService.addUser(newUser))
			{
				return HttpStatus.ACCEPTED;
			}
			else
			{
				return HttpStatus.BAD_REQUEST;
			}
		}
		else
		{
			return HttpStatus.FORBIDDEN;
		}
		
	}
	
  
  
  
  
}
