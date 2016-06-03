package com.paccounting.dao;

import java.util.List;

import com.paccounting.entities.Notification;
import com.paccounting.entities.NotificationReceiverMapper;
import com.paccounting.entities.Receiver;

public interface EntityDao {

	public boolean saveEntity( Object entity);
	public boolean updateEntity(Object entity);
	public boolean saveAllEntities( List<?> entities);
	public <T>T getObject(Class<T> type,String id);
	public List<Notification>getAllNotifications(Receiver receiver);
	public List<NotificationReceiverMapper> getMapperByReceiver(Receiver receiver);
	public NotificationReceiverMapper getMapperByNotificationReceiver(Notification notification,Receiver receiver);
}
