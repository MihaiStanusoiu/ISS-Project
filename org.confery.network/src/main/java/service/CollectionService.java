package service;

import domain.UserEntity;
import transferable.User;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface CollectionService extends Remote {

    void setLoginService(LoginService loginService) throws RemoteException;

    void setSignUpService(SignUpService signUpService) throws RemoteException;

    void setUserService(UserService userService) throws RemoteException;

    void setNotificationService(NotificationService notificationService) throws RemoteException;

    void setSessionService(SessionService sessionService) throws RemoteException;

    void setSubmissionService(SubmissionService submissionService) throws RemoteException;

    void setTagService(TagService tagService) throws RemoteException;

    void setTopicService(TopicService topicService) throws RemoteException;

    void setEditionService(EditionService editionService) throws RemoteException;

    LoginService loginService() throws RemoteException;

    SignUpService signUpService() throws RemoteException;

    UserService userService() throws RemoteException;

    NotificationService notificationService() throws RemoteException;

    SessionService sessionService() throws RemoteException;

    SubmissionService submissionService() throws RemoteException;

    TagService tagService() throws RemoteException;

    TopicService topicService() throws RemoteException;

    EditionService editionService() throws RemoteException;

    UserEntity getActiveUser() throws RemoteException;

    void activeUser(User user) throws RemoteException;

}
