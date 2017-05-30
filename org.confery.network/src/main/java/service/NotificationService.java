package service;

import domain.NotificationEntity;
import transferable.Notification;
import transferable.User;

import java.rmi.RemoteException;
import java.util.List;

public interface NotificationService extends Service<Notification, Integer> {

    Notification sendNotificationToUser(User user, Notification notification) throws RemoteException;

    Notification sendNotificationToUsers(List<User> users, Notification notification) throws RemoteException;
}
