package manager;

import domain.UserEntity;
import service.*;
import transfarable.User;

import java.rmi.RemoteException;
import java.util.List;

import static java.util.Arrays.asList;
import static utils.Try.runMethod;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class CollectionManager implements CollectionService {

    private LoginService loginService;
    private SignUpService signUpService;
    private UserService userService;
    private UserEntity activeUser;

    private SubmissionService submissionService;
    private TopicService topicService;
    private TagService tagService;
    private EditionService editionService;
    private SessionService sessionService;
    private NotificationService notificationService;


    public CollectionManager(LoginService loginService,
                             SignUpService signUpService,
                             UserService userService,
                             SubmissionService submissionService,
                             TopicService topicService,
                             TagService tagService,
                             EditionService editionService,
                             SessionService sessionService,
                             NotificationService notificationService) {
        this.loginService = loginService;
        this.signUpService = signUpService;
        this.userService = userService;
        this.submissionService = submissionService;
        this.topicService = topicService;
        this.tagService = tagService;
        this.editionService = editionService;
        this.sessionService = sessionService;
        this.notificationService = notificationService;
    }

    @Override
    public LoginService loginService() {
        return loginService;
    }

    @Override
    public SignUpService signUpService() {
        return signUpService;
    }

    @Override
    public UserService userService() throws RemoteException {
        return userService;
    }

    @Override
    public NotificationService notificationService() throws RemoteException {
        return notificationService;
    }

    @Override
    public SessionService sessionService() throws RemoteException {
        return sessionService;
    }

    @Override
    public SubmissionService submissionService() throws RemoteException {
        return submissionService;
    }

    @Override
    public TagService tagService() throws RemoteException {
        return tagService;
    }

    @Override
    public TopicService topicService() throws RemoteException {
        return topicService;
    }

    @Override
    public EditionService editionService() throws RemoteException {
        return editionService;
    }

    @Override
    public UserEntity getActiveUser() {
        return activeUser;
    }

    @Override
    public void activeUser(User user) throws RemoteException {
        List<ServiceInterface> list = asList(loginService, signUpService, notificationService, userService,
                sessionService, submissionService, editionService, tagService, topicService);
        list.forEach(service -> runMethod(service::activeUser, user));
    }
}
