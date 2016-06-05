package com.paccounting.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paccounting.dao.EntityDao;
import com.paccounting.entities.Borrower;
import com.paccounting.entities.Event;
import com.paccounting.entities.Notification;
import com.paccounting.entities.NotificationReceiverMapper;
import com.paccounting.entities.Payer;
import com.paccounting.entities.Receiver;
import com.paccounting.entities.Transaction;
import com.paccounting.model.InputNotification;

@Service
public class NotificationServiceImpl implements NotificationService{

	@Autowired
	EntityDao entityDao;
	@Transactional
	public boolean addNotification(InputNotification inputNotification) {
		
		try{
		Notification notification=new Notification();
		notification.setEvent(inputNotification.getEvent());
		notification.setId(inputNotification.getId());
		notification.setSender(inputNotification.getSender());
		Event e=inputNotification.getEvent();
		notification.setStatus(inputNotification.getStatus());
		boolean flag=false;
		if(inputNotification.getStatus()==Notification.Status.MODIFIED.ordinal()||inputNotification.getStatus()==Notification.Status.DELETED.ordinal())
		{
			Notification n=entityDao.getObject(Notification.class, inputNotification.getId());
			flag=true;		
			if(n.getSender().getMob().equals(inputNotification.getSender().getMob()))
			entityDao.updateEntity(notification);
			else
				return false;
		}
		else
		entityDao.saveEntity(notification);
		System.out.println("notificcation saved");
		for(Transaction tr:e.getTransactions())
		{
			for(Borrower borrower:tr.getBorrowers())
			{
				/*receivers.add(new Receiver(borrower,ntfc));*/
				if(!borrower.getMob().equals(notification.getSender().getMob()))
				{
					NotificationReceiverMapper mapper=new NotificationReceiverMapper();
					mapper.setNotification(notification);
					Receiver receiver=new Receiver(borrower);
					mapper.setReceiver(receiver);
					mapper.setStatus(notification.getStatus());
					System.out.println("ID:"+borrower.getMob());
					if(notification.getStatus()==Notification.Status.MODIFIED.ordinal()||notification.getStatus()==Notification.Status.DELETED.ordinal())
					{
						NotificationReceiverMapper m=entityDao.getMapperByNotificationReceiver(notification, receiver);
						mapper.setId(m.getId());
						System.out.println("updating mapper "+mapper.getId());
						entityDao.updateEntity(mapper);
					}
					else
						entityDao.saveEntity(mapper);
				}
				
			}
			for(Payer payer:tr.getPayers())
			{/*
				receivers.add(new Receiver(payer,ntfc));
				System.out.println("ID:"+payer.getMob());*/
				if(!payer.getMob().equals(notification.getSender().getMob()))
				{
					System.out.println("Saving payer");
					NotificationReceiverMapper mapper=new NotificationReceiverMapper();
					mapper.setNotification(notification);
					Receiver receiver=new Receiver(payer);
					mapper.setReceiver(receiver);
					mapper.setStatus(notification.getStatus());
					if(notification.getStatus()==Notification.Status.MODIFIED.ordinal()||notification.getStatus()==Notification.Status.DELETED.ordinal())
					{
						NotificationReceiverMapper m=entityDao.getMapperByNotificationReceiver(notification, receiver);
						mapper.setId(m.getId());
						System.out.println("updating mapper "+mapper.getId());
						entityDao.updateEntity(mapper);

					}
					else
					entityDao.saveEntity(mapper);
				}
			}
		}
		//notification.setReceivers(receivers);
		}catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
		 
	}
	@Transactional
	public List<InputNotification> getNotifications(String id) {
		
		Receiver receiver=entityDao.getObject(Receiver.class, id);
		//List<Notification>list=receiver.getNotificationIds();
		List<NotificationReceiverMapper> mappers=entityDao.getMapperByReceiver(receiver);
		List<Notification>notifications=new ArrayList<Notification>();
		List<NotificationReceiverMapper>mappers2=new ArrayList<NotificationReceiverMapper>();
		for(NotificationReceiverMapper mapper:mappers)
		{
			if(mapper.getStatus()==Notification.Status.READY.ordinal()||mapper.getStatus()==Notification.Status.MODIFIED.ordinal()||mapper.getStatus()==Notification.Status.NEW.ordinal())
			{
				notifications.add(mapper.getNotification());
				mapper.setStatus(Notification.Status.SENT.ordinal());
				mappers2.add(mapper);
			}
			else if(mapper.getStatus()==Notification.Status.DELETED.ordinal())
			{
				notifications.add(mapper.getNotification());
				System.out.println("deleting mapper "+mapper.getId());
				entityDao.delete(mapper);
			}

				
		}
		System.out.println("saved");
		entityDao.saveAllEntities(mappers2);
		return formatNotification(notifications);
	}
	private List<InputNotification> formatNotification(List<Notification> list) {
		System.out.println("saved1");
		List<InputNotification>nlist=new ArrayList<InputNotification>();
		for(Notification notification:list)
		{
			InputNotification inNotification=new InputNotification(notification);
			nlist.add(inNotification);
			
		}
		
		
		return nlist;
	}

}
