package service;


import domain.UserEntity;
import transfarable.*;

import java.rmi.RemoteException;
import java.util.List;

public interface UserService extends Service<User, Integer, UserEntity> {

    List<Session> getSessionsForUser(User user) throws RemoteException;

    List<Edition> getEditionsForUser(User user) throws RemoteException;

    List<Submission> getSubmissionsForUser(User user) throws RemoteException;

    List<Submission> getSubmissionsByOwnership(User user) throws RemoteException;

    List<Submission> getSubmissionsByAuthorship(User user) throws RemoteException;

    List<Edition> getEditionsAsChair(User user) throws RemoteException;

    User addEditionTo(User user, Edition edition, MemberRoleTransferable role) throws RemoteException;

    User addSessionTo(User user, Session session, MemberRoleTransferable role) throws RemoteException;

    User addSubmissionTo(User user, Submission submission, Boolean isOwner) throws RemoteException;

    User addNotificationTo(User user, Notification notification) throws RemoteException;

    User removeEditionFrom(User user, Edition edition) throws RemoteException;

    User removeSessionFrom(User user, Session session) throws RemoteException;

    User removeNotificationFrom(User user, Notification notification) throws RemoteException;

    User removeSubmissionFrom(User user, Submission submission) throws RemoteException;
}

