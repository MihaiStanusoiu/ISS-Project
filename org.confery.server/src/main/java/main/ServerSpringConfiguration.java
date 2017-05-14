package main;

import database.DatabaseLoader;
import manager.LoginManager;
import manager.SubscriptionManager;
import model.UserModel;
import model.UserProtocol;
import notification.NotificationCenter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.remoting.rmi.RmiServiceExporter;
import service.LoginService;
import service.SubscriptionService;

import java.io.IOException;
import java.rmi.RemoteException;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

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
    @Scope("singleton")
    public NotificationCenter notificationCenter() throws RemoteException {
        return new NotificationCenter();
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
    public RmiServiceExporter loginService() throws IOException {
        RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
        LoginService loginService = new LoginManager(notificationCenter(), userModel());
        rmiServiceExporter.setServiceName("LoginService");
        rmiServiceExporter.setService(loginService);
        rmiServiceExporter.setServiceInterface(LoginService.class);
        rmiServiceExporter.setRegistryPort(port);
        return rmiServiceExporter;
    }

}