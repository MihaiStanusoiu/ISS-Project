
package service;


import domain.SessionEntity;
import domain.UserEntity;
import transfarable.Session;
import transfarable.User;

import java.rmi.RemoteException;
import java.util.List;

public interface SessionService extends Service<Session, Integer, SessionEntity> {

    List<User> getSpeakers(Session session) throws RemoteException;

    List<User> getListeners(Session session) throws RemoteException;

    User getChair(Session session) throws RemoteException;
}
