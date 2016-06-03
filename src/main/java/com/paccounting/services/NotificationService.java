package com.paccounting.services;

import java.util.List;

import com.paccounting.model.InputNotification;

public interface NotificationService {

	public boolean addNotification(InputNotification inputNotification);

	public List<InputNotification> getNotifications(String id);
}
