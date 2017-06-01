package service;

import transfarable.User;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface CollectionService extends Remote, ServiceInterface {

    ConferenceService conferenceService() throws RemoteException;

    LoginService loginService() throws RemoteException;

    SignUpService signUpService() throws RemoteException;

    UserService userService() throws RemoteException;

    NotificationService notificationService() throws RemoteException;

    SessionService sessionService() throws RemoteException;

    SubmissionService submissionService() throws RemoteException;

    TagService tagService() throws RemoteException;

    TopicService topicService() throws RemoteException;

    EditionService editionService() throws RemoteException;

    User getActiveUser() throws RemoteException;

    void activeUser(User user) throws RemoteException;

}
