package manager;

import domain.UserEntity;
import service.*;
import transferable.User;
import translator.UserTranslator;

import java.rmi.RemoteException;
import java.util.List;

import static java.util.Arrays.asList;
import static utils.Try.runFunction;

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
    public void setLoginService(LoginService loginService) throws RemoteException {
        this.loginService = loginService;
    }

    @Override
    public void setSignUpService(SignUpService signUpService) throws RemoteException {
        this.signUpService = signUpService;
    }

    @Override
    public void setUserService(UserService userService) throws RemoteException {
        this.userService = userService;
    }

    @Override
    public void setNotificationService(NotificationService notificationService) throws RemoteException {
        this.notificationService = notificationService;
    }

    @Override
    public void setSessionService(SessionService sessionService) throws RemoteException {
        this.sessionService = sessionService;
    }

    @Override
    public void setSubmissionService(SubmissionService submissionService) throws RemoteException {
        this.submissionService = submissionService;
    }

    @Override
    public void setTagService(TagService tagService) throws RemoteException {
        this.tagService = tagService;
    }

    @Override
    public void setTopicService(TopicService topicService) throws RemoteException {
        this.topicService = topicService;
    }

    @Override
    public void setEditionService(EditionService editionService) throws RemoteException {
        this.editionService = editionService;
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
        UserEntity userEntity = UserTranslator.translate(user);
        this.activeUser = userEntity;
        List<ServiceInterface> list = asList(loginService, signUpService, notificationService, userService,
                sessionService, submissionService, editionService, tagService, topicService);
//        list.forEach(service -> runFunction(service::activeUser, user));
        for (ServiceInterface service : list) {
            service.activeUser(user);
        }
    }
}
