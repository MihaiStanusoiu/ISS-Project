package main;

import javafx.application.Application;
import javafx.stage.Stage;
import manager.StageManager;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import view.ViewType;

@SpringBootApplication
@SuppressWarnings("all")
public class  Main extends Application {

    private ConfigurableApplicationContext context;
    private StageManager stageManager;

    public static void main(String[] args) {
        Application.launch();
    }

    /**
     * Effect: Spring gets initialised here.
     * <p> We need Spring in order to create the StageManager. </p>
     * @throws Exception : If Spring is unable to get an Application Context.
     */
    @Override
    public void init() throws Exception {
        context = getContext();
    }

    /**
     * Effect: Starts the FX Application Thread.
     * <p> Needs a stage in order to create the StageManager, so the bean initialisation must be lazy. </p>
     * @param stage: The init stage that FX Application provides. [Stage]
     * @throws Exception : If Spring is unable to create the bean for StageManager.
     */
    @Override
    public void start(Stage stage) throws Exception {
        stageManager = context.getBean(StageManager.class, stage);
        displayScene();
    }

    /**
     * Effect: Displays the frame scene of our application.
     */
    private void displayScene() {
        stageManager.switchScene(ViewType.CONFERENCES);
    }

    /**
     * Effect: Closes the spring's application context.
     *
     * This method is called when the application should stop, and provides a
     * convenient place to prepare for application exit and destroy resources.
     *
     * @throws Exception : If the application is not closing properly.
     */
    @Override
    public void stop() throws Exception {
        context.close();
        System.exit(0);
    }

    /**
     * Effect: Create an application context with the application's command line args.
     * @return ConfigurableApplicationContext The application's context.
     */
    private ConfigurableApplicationContext getContext() {
        // creates an application & context builder based on the main class
        SpringApplicationBuilder builder = new SpringApplicationBuilder(Main.class);
        // retrieves a read-only list of raw arg and converts the list to String[] -- application's args
        String[] args = getParameters().getRaw().stream().toArray(String[]::new);
        builder.headless(false); // needed for TestFX integration
        return builder.run(args);
    }
}
