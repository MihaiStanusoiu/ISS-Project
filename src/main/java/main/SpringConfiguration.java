package main;

import controller.*;
import javafx.stage.Stage;
import loader.SpringFXMLLoader;
import manager.StageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
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

    private @Autowired SpringFXMLLoader loader;             // FXML Loader with DI.
    private StageManager stageManager;

    @Bean
    @Lazy
    public ControllerConferencesView controllerHome() {
        return new ControllerConferencesView(stageManager);
    }

    @Bean
    @Lazy
    public ControllerUsersView controllerUsers() {
        return new ControllerUsersView(stageManager);
    }

    @Bean
    @Lazy
    public ControllerConferenceItem controllerConferenceItem() {
        return new ControllerConferenceItem(stageManager);
    }

    @Bean
    @Lazy
    public ControllerTopBar controllerTopBar() {
        return new ControllerTopBar(stageManager);
    }

    @Bean
    @Lazy
    public ControllerLogin controllerLogin() {
        return new ControllerLogin(stageManager);
    }

    @Bean
    @Lazy
    public ControllerSignUp controllerSignUp() {
        return new ControllerSignUp(stageManager);
    }

    @Bean
    @Lazy
    public ControllerMenu controllerMenu() {
        return new ControllerMenu(stageManager);
    }

    /** Local Resources Bundle */
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

}
