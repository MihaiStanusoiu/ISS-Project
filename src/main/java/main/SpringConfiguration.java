package main;

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
 * Effect:      Beans configuration for spring context application.
 * Date:        31/03/2017
 * Tested:      False
 * @author      Alexandru Stoica
 * @version     1.0
 */

@Configuration
@ComponentScan("loader")
public class SpringConfiguration {

    private @Autowired SpringFXMLLoader loader;             // FXML Loader with DI.

    /** Local Resources Bundle */
    @Bean
    public ResourceBundle resourceBundle() {
        return ResourceBundle.getBundle("Bundle");
    }

    /**
     * Effect: Bean for Stage Manager Spring DI
     *
     * <p>Requires @Lazy because the stage is NOT initialize when Spring
     * is initializing the application's context.</p>
     *
     * @param stage: The stage of the FX Application.
     * @return The application's Stage Manager.
     */
    @Bean
    @Lazy
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public StageManager stageManager(Stage stage) {
        return new StageManager(loader, stage);
    }

}
