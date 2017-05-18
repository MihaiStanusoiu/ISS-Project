package main;

import controller.*;
import itemcontroller.ControllerConferenceView;
import itemcontroller.ControllerProfileView;
import itemcontroller.ControllerUserView;
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
 * Name:        SpringConfiguration
 * Effect:      Beans configuration for Spring context application.
 * Date:        31/03/2017
 * Tested:      False
 * @author      Alexandru Stoica
 * @version     1.0
 */

@Configuration
@ComponentScan("loader")
@SuppressWarnings("all")
public class SpringConfiguration {

    private @Autowired SpringFXMLLoader loader;
    private StageManager stageManager;

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

    @Bean
    @Lazy
    public ControllerConferencesView controllerConferencesView()
            throws RemoteException, NotBoundException, MalformedURLException {
        return new ControllerConferencesView(stageManager, listener());
    }

    @Bean
    @Lazy
    public ControllerMyConferencesView controllerMyConferencesView() {
        return new ControllerMyConferencesView(stageManager);
    }

    @Bean
    @Lazy
    public ControllerNotificationsView controllerNotificationsView() {
        return new ControllerNotificationsView(stageManager);
    }

    @Bean
    @Lazy
    public ControllerUsersView controllerUsers()
            throws RemoteException, NotBoundException, MalformedURLException {
        return new ControllerUsersView(stageManager, listener());
    }

    @Bean
    @Lazy
    public ControllerLogin controllerLogin()
            throws RemoteException, MalformedURLException, NotBoundException {
        return new ControllerLogin(stageManager, loginService(), listener());
    }

    @Bean
    @Lazy
    public ControllerTopBar controllerTopBar()
            throws RemoteException, NotBoundException, MalformedURLException {
        return new ControllerTopBar(stageManager, listener());
    }

    @Bean
    @Lazy
    public ControllerSignUp controllerSignUp()
            throws RemoteException, NotBoundException, MalformedURLException {
        return new ControllerSignUp(stageManager, signUpService(), listener());
    }

    @Bean
    @Lazy
    public ControllerMenu controllerMenu()
            throws RemoteException, NotBoundException, MalformedURLException {
        return new ControllerMenu(stageManager, listener());
    }

    @Bean
    @Lazy
    public ControllerAddConferenceView controllerAddConferenceVoew()
            throws RemoteException, NotBoundException, MalformedURLException {
        return new ControllerAddConferenceView(stageManager, listener());
    }

    @Bean
    @Lazy
    public ControllerConferenceView controllerConferenceView() {
        return new ControllerConferenceView(stageManager);
    }


    @Bean
    @Lazy
    public ControllerProfileView controllerProfileView()
            throws RemoteException, NotBoundException, MalformedURLException {
        return new ControllerProfileView(stageManager, listener(), userService());
    }


    @Bean
    @Lazy
    public ControllerUserView controllerUserView() {
        return new ControllerUserView(stageManager);
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
