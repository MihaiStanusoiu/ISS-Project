package main;

import context.CoreContext;
import javafx.stage.Stage;
import listener.ListenerHelper;
import loader.SpringFXMLLoader;
import manager.StageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import service.*;

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
@ComponentScan("context")
@SuppressWarnings("all")
public class SpringConfiguration {

    @Autowired
    private CoreContext coreContext;

    @Autowired
    private SpringFXMLLoader loader;

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
    public CollectionService collectionService()
            throws RemoteException, NotBoundException, MalformedURLException {
        return (CollectionService) getService(CollectionService.class, "CollectionService", port).getObject();
    }

    @Bean
    public SubscriptionService subscriptionService()
            throws RemoteException, NotBoundException, MalformedURLException {
        return (SubscriptionService) getService(SubscriptionService.class, "SubscriptionService", port).getObject();
    }

}
