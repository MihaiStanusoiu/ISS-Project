
package service;

import domain.SessionEntity;
import transfarable.MemberRoleTransferable;
import transfarable.Session;
import transfarable.User;

import java.rmi.RemoteException;
import java.util.List;

public interface SessionService extends Service<Session, Integer, SessionEntity> {

    List<User> getSpeakers(Session session) throws RemoteException;

    List<User> getListeners(Session session) throws RemoteException;

    User getChair(Session session) throws RemoteException;

    public Session addMemberTo(Session session, User user, MemberRoleTransferable role) throws RemoteException;

    public Session removeMemberFrom(Session session, User user) throws RemoteException;

}
