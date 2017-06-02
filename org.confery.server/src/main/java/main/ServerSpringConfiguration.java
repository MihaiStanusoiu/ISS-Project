package main;

import database.DatabaseLoader;
import manager.*;
import model.*;
import notification.NotificationCenter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.remoting.rmi.RmiServiceExporter;
import protocol.*;
import service.*;

import java.io.IOException;
import java.rmi.RemoteException;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@ComponentScan("org.confery.network")
@Configuration
@SuppressWarnings("all")
public class ServerSpringConfiguration {

            private final Integer port = 1192;

            @Bean
            @Scope("singleton")
            public DatabaseLoader databaseLoader() throws IOException {
                return new DatabaseLoader();
            }

            @Bean
            public UserProtocol userModel() throws IOException {
                return new UserModel(databaseLoader());
            }

            @Bean
            public SubmissionProtocol submissionModel() throws IOException {
                return new SubmissionModel(databaseLoader());
            }

            @Bean
            public LoginProtocol loginModel() throws IOException {
                return new LoginModel(databaseLoader());
            }

            @Bean
            public EditionProtocol editionModel() throws IOException {
                return new EditionModel(databaseLoader());
            }

            @Bean
            public NotificationProtocol notificationModel() throws IOException {
                return new NotificationModel(databaseLoader());
            }

            @Bean
            public TagProtocol tagModel() throws IOException {
                return new TagModel(databaseLoader());
            }

            @Bean
            public TopicProtocol topicModel() throws IOException {
                return new TopicModel(databaseLoader());
            }

            @Bean
            public SessionProtocol sessionModel() throws IOException {
                return new SessionModel(databaseLoader());
            }

            @Bean
            @Scope("singleton")
            public NotificationCenter notificationCenter() throws RemoteException {
                return new NotificationCenter();
            }

            @Bean
            public RmiServiceExporter collectionService() throws IOException {
                RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
                CollectionService collectionService = new CollectionManager(
                        conferenceService(), loginService(), signUpService(),
                        userService(), submissionSerive(), topicService(), tagService(),
                        editionService(), sessionService(), notificationService(), authenticationService());


                rmiServiceExporter.setServiceName("CollectionService");
                rmiServiceExporter.setService(collectionService);
                rmiServiceExporter.setServiceInterface(CollectionService.class);
                rmiServiceExporter.setRegistryPort(port);
                return rmiServiceExporter;
            }

            public Object getService(Class<?> serviceClass, String serviceName, ServiceInterface service) {
                RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
                rmiServiceExporter.setServiceName(serviceName);
                rmiServiceExporter.setService(service);
                rmiServiceExporter.setServiceInterface(serviceClass);
                rmiServiceExporter.setRegistryPort(port);
                return rmiServiceExporter.getService();
            }

            @Bean
            public AuthenticationService authenticationService() throws IOException {
                AuthenticationService authenticationService = new AuthenticationManager(loginModel());
                return (AuthenticationService)getService(AuthenticationService.class, "authenticationService", authenticationService);
            }

            @Bean
            public ConferenceService conferenceService() throws IOException {
                ConferenceService conferenceService = new ConferenceManager(conferenceModel(),loginModel());
        return (ConferenceService)getService(ConferenceService.class, "conferenceService", conferenceService);
    }

    @Bean
    public ConferenceProtocol conferenceModel() throws IOException {
        return new ConferenceModel(databaseLoader());
    }

    @Bean
    public UserService userService() throws IOException {
        UserService userService = new UserManager(userModel(), loginModel());
        return (UserService) getService(UserService.class, "userService", userService);
    }

    @Bean
    public SubmissionService submissionSerive() throws IOException {
        SubmissionService submissionService = new SubmissionManager(submissionModel(),loginModel());
        return (SubmissionService) getService(SubmissionService.class, "submissionService", submissionService);
    }

    @Bean
    public TopicService topicService() throws IOException {
        TopicService topicService = new TopicManager(topicModel(), loginModel());
        return (TopicService) getService(TopicService.class, "topicService", topicService);
    }

    @Bean
    public TagService tagService() throws IOException {
        TagService tagService = new TagManager(tagModel(), loginModel());
        return (TagService) getService(TagService.class, "tagService", tagService);
    }

    @Bean
    public SessionService sessionService() throws IOException {
        SessionService sessionService = new SessionManager(sessionModel(), loginModel());
        return (SessionService) getService(SessionService.class, "sessionService", sessionService);
    }
    @Bean
    public NotificationService notificationService() throws IOException {
        NotificationService notificationService = new NotificationManager(notificationModel(), loginModel());
        return (NotificationService) getService(NotificationService.class, "notificationService", notificationService);
    }
    @Bean
    public EditionService editionService() throws IOException {
        EditionService editionService = new EditionManager(editionModel(), loginModel());
        return (EditionService) getService(EditionService.class, "editionMaanger", editionService);
    }

    @Bean
    public LoginService loginService() throws IOException {
        LoginService loginService = new LoginManager(notificationCenter(), userModel(), loginModel());
        return (LoginService) getService(LoginService.class, "LoginService", loginService);
    }

    @Bean
    public RmiServiceExporter subscriptionService() throws RemoteException {
        RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
        SubscriptionService subscriptionService = new SubscriptionManager(notificationCenter());
        rmiServiceExporter.setServiceName("SubscriptionService");
        rmiServiceExporter.setService(subscriptionService);
        rmiServiceExporter.setServiceInterface(SubscriptionService.class);
        rmiServiceExporter.setRegistryPort(port);
        return rmiServiceExporter;
    }

    @Bean
    public SignUpService signUpService() throws IOException {
        SignUpService signUpService = new SignUpManager(notificationCenter(), userModel(), loginModel());
        return (SignUpService) getService(SignUpService.class, "SignUpService", signUpService);
    }


}