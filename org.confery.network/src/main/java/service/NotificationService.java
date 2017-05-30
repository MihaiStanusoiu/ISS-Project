package service;


import transfarable.Notification;
import transfarable.User;

import java.rmi.RemoteException;
import java.util.List;

public interface NotificationService extends Service<Notification, Integer> {

    Notification sendNotificationToUser(User user, Notification notification) throws RemoteException;

    Notification sendNotificationToUsers(List<User> users, Notification notification) throws RemoteException;

}
