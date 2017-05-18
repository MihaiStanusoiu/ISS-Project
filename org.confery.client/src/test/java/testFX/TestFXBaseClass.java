package testFX;

import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import main.Main;
import org.junit.After;
import org.junit.Before;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

import java.sql.Time;
import java.util.concurrent.TimeoutException;

/**
 * Created by Vlad on 5/18/2017.
 */
public class TestFXBaseClass extends ApplicationTest{

    @Before
    public void setUpClass() throws Exception{
        ApplicationTest.launch(main.Main.class);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.show();
    }

    @After
    public void afterEachTest() throws TimeoutException{
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }

    //method used to determine a fxml element by fxid
    public <T extends Node> T find (final String query){
        return (T) lookup(query).queryAll().iterator().next();
    }
}
