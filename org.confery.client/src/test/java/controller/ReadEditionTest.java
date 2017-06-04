package controller;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import org.junit.Test;

import static org.loadui.testfx.Assertions.verifyThat;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class ReadEditionTest extends TestFxBase {

    private final String SEARCH_TEXT_FIELD_ID = "#searchTextField";

    @Test
    public void isReadingEditions() throws Exception {
        clickOn(SEARCH_TEXT_FIELD_ID).write("Biology");
        BorderPane pane = find("#rootPane");
        clickOn(pane);
        verifyThat("#locationLabel", (Label location) -> location.getText().contains("New"));
    }

    @Test
    public void isReadingEdition() throws Exception {
        clickOn(SEARCH_TEXT_FIELD_ID).write("Biology");
        BorderPane pane = find("#rootPane");
        clickOn(pane);
        verifyThat("#locationLabel", (Label location) -> location.getText().contains("New"));
        clickOn((BorderPane)find("#rootPane"));
        verifyThat("#bioText", (Text bio) -> bio.getText().contains("Lorem"));
    }
}
