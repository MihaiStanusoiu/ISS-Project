package service;


import domain.UserEntity;
import transfarable.Edition;
import transfarable.Session;
import transfarable.Submission;
import transfarable.User;

import java.rmi.RemoteException;
import java.util.List;

public interface UserService extends Service<User, Integer, UserEntity> {

    List<Session> getSessionsForUser(User user) throws RemoteException;
    List<Edition> getEditionsForUser(User user) throws RemoteException;
    List<Submission> getSubmissionsForUser(User user) throws RemoteException;
    List<Submission> getSubmissionsByOwnership(User user) throws RemoteException;
    List<Submission> getSubmissionsByAuthorship(User user) throws RemoteException;
    List<Edition> getEditionsAsChair(User user) throws RemoteException;

}
