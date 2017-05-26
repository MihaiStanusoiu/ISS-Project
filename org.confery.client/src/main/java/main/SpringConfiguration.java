package main;

import javafx.stage.Stage;
import listener.ListenerHelper;
import loader.SpringFXMLLoader;
import manager.StageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import service.LoginService;
import service.SignUpService;
import service.SubscriptionService;
import service.UserService;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

/**
 * @author      Alexandru Stoica
 * @version     1.0
 */

@Configuration
@ComponentScan("loader")
@ComponentScan("itemcontroller")
@ComponentScan("controller")
@SuppressWarnings("all")
public class SpringConfiguration {

    private @Autowired SpringFXMLLoader loader;
    private StageManager stageManager;

//    public @Autowired ControllerMenu controllerMenu;
//    public @Autowired ControllerConferencesView controllerConferencesView;
//    public @Autowired ControllerLogin controllerLogin;
//    public @Autowired ControllerMyConferencesView controllerMyConferencesView;
//    public @Autowired ControllerNotificationsView controllerNotificationsView;
//    public @Autowired ControllerSignUp controllerSignUp;
//    public @Autowired ControllerTopBar controllerTopBar;
//    public @Autowired ControllerUsersView controllerUsersView;
//    public @Autowired ControllerAddConferenceView controllerAddConferenceView;
//    public @Autowired ControllerMembersConferenceView controllerMembersConferenceView;
//    public @Autowired ControllerConferenceView controllerConferenceView;
//    public @Autowired ControllerProfileView controllerProfileView;
//    public @Autowired ControllerUserView controllerUserView;


    @Bean
    @Lazy
    @Scope("singleton")
    public ListenerHelper listener()
            throws RemoteException, MalformedURLException, NotBoundException {
        ListenerHelper listener = new ListenerHelper();
        SubscriptionService service = subscriptionService();
        service.addSubscriber(listener);
        return listener;
    }

    /** Local Resource Bundle */
    @Bean
    public ResourceBundle resourceBundle() {
        return ResourceBundle.getBundle("application");
    }

    /**
     * Effect: Bean for Stage Manager Spring DI
     *
     * <p>Requires @Lazy because the stage is NOT initialized when Spring
     * is initializing the application's context.</p>
     *
     * @param stage: The stage of the FX Application.
     * @return The application's Stage Manager.
     */
    @Bean
    @Lazy
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public StageManager stageManager(Stage stage) {
        stageManager = new StageManager(loader, stage);
        return stageManager;
    }

    private static final Integer port = 1192;

    private RmiProxyFactoryBean getService(Class<?> serviceClass, String serviceName, Integer port) {
        RmiProxyFactoryBean rmiProxyFactoryBean = new RmiProxyFactoryBean();
        rmiProxyFactoryBean.setServiceUrl("rmi://localhost:" + port + "/" + serviceName);
        rmiProxyFactoryBean.setServiceInterface(serviceClass);
        rmiProxyFactoryBean.afterPropertiesSet();
        return rmiProxyFactoryBean;
    }

    @Bean
    public LoginService loginService()
            throws RemoteException, NotBoundException, MalformedURLException {
        return (LoginService) getService(LoginService.class, "LoginService", port).getObject();
    }

    @Bean
    public SubscriptionService subscriptionService()
            throws RemoteException, NotBoundException, MalformedURLException {
        return (SubscriptionService) getService(SubscriptionService.class, "SubscriptionService", port).getObject();
    }

    @Bean
    public SignUpService signUpService()
            throws RemoteException, NotBoundException, MalformedURLException {
        return (SignUpService) getService(SignUpService.class, "SignUpService", port).getObject();
    }

    @Bean
    public UserService userService()
            throws RemoteException, NotBoundException, MalformedURLException {
        return (UserService) getService(UserService.class, "UserService", port).getObject();
    }
}
