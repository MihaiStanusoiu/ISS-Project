package controller;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.loadui.testfx.Assertions.verifyThat;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class ReadConferencesTest extends TestFxBase {

    private final String SEARCH_TEXT_FIELD_ID = "#searchTextField";

    @Test
    public void isSearchingForConferences() throws Exception {
        clickOn(SEARCH_TEXT_FIELD_ID).write("Biology");
        Label label = find("#nameLabel");
        assertEquals(label.getText(), "CONFERENCE IN BIOLOGY");
    }

    @Test
    public void isReadingConference() throws Exception {
        clickOn(SEARCH_TEXT_FIELD_ID).write("Biology");
        BorderPane pane = find("#rootPane");
        clickOn(pane);
        verifyThat("#nameLabel", (Label name) -> name.getText().equals("CONFERENCE IN BIOLOGY"));
    }
}
