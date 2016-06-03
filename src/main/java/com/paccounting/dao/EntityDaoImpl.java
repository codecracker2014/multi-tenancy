package com.paccounting.dao;

import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.paccounting.entities.Notification;
import com.paccounting.entities.NotificationReceiverMapper;
import com.paccounting.entities.Receiver;
@Repository
public class EntityDaoImpl implements EntityDao {
	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public boolean saveEntity(Object entity) {
		try {
			
			entityManager.persist(entity);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}


	@Transactional
	public boolean updateEntity(Object entity) {
		try {
			
			entityManager.merge(entity);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public <T> T getObject(Class<T> type, String id) {
		
		
		return type.cast(entityManager.find(type, id));
	}

	public List<Notification> getAllNotifications(Receiver receiver) {
		
		Query query=entityManager.createQuery("select from Notification");
		
		return query.getResultList();
	}

	public List<NotificationReceiverMapper> getMapperByReceiver(Receiver receiver) {
		
		Query query=entityManager.createQuery("from NotificationReceiverMapper NRM where NRM.receiver= :receiver");
		query.setParameter("receiver", receiver);
		
		return query.getResultList();
	}

	@Transactional
	public boolean saveAllEntities(List<?> entities) {
		// TODO Auto-generated method stub
		try{
			for(Object entity:entities)
			{
				entityManager.persist(entity);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}


	public NotificationReceiverMapper getMapperByNotificationReceiver(Notification notification, Receiver receiver) {
		Query query=entityManager.createQuery("from NotificationReceiverMapper NRM where NRM.receiver= :receiver and NRM.notification=:notification");
		query.setParameter("receiver", receiver);
		query.setParameter("notification", notification);
		return (NotificationReceiverMapper)query.getSingleResult();
		
	}

	
}
