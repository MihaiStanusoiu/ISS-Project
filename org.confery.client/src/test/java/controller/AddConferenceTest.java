package controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.loadui.testfx.Assertions.assertNodeExists;
import static org.testfx.matcher.control.TextFlowMatchers.hasText;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class AddConferenceTest extends TestFxBase {

    private final String EDITION_NAME = "Test Edition";
    private final String EDITION_BIO = "Edition Bio";
    private final String CONFERENCE_NAME = "Test Conference";
    private final String USERNAME = "test";
    private final String PASSWORD = "password";
    private final String SESSION_NAME = "Test Session";
    private final String CO_CHAIR_USERNAME = "oana.daniela";

    @Override @Before
    public void setUp() throws Exception {
        super.setUp();
        forceLoginInSystem();
    }

    @Override @After
    public void tearDown() throws Exception {
        super.tearDown();
        logoutFromSystem();
    }

    @Test
    public void isAbleToAddConference() throws Exception {
        // when:
        addConference();
        // go-to:
        moveTo(CONFERENCE_NAME).clickOn();
        moveTo(EDITION_NAME).clickOn();
        // then:
        assertNodeExists(hasText("Oana Daniela"));
        assertNodeExists(hasText("Popescu Dan"));
        assertNodeExists(hasText(SESSION_NAME));
        assertNodeExists(hasText(EDITION_NAME));
        assertNodeExists(hasText(EDITION_BIO));
    }

    private void addConference() {
        clickOnAddConferenceButton();
        completeConferenceTextFields();
        clickOnAddEditionButton();
        completeEditionTextFields();
        moveToMembersTab();
        addMemberToEdition();
        moveToSessionsTab();
        completeSessionFields();
        clickOnPublishButton();
    }

    private void forceLoginInSystem() {
        try {
            loginInSystem(USERNAME, PASSWORD);
        } catch (Exception exception ) {
            logoutFromSystem();
            loginInSystem(USERNAME, PASSWORD);
        }
    }

    @Test
    public void isAbleToUpdateConference() throws Exception {
        addConference();
        loginInSystem(USERNAME, PASSWORD);
        // to-go:
        moveTo(CONFERENCE_NAME).clickOn();
        completeConferenceTextFieldWith("Test Update", "Test Update");
        clickOnPublishButton();
        assertNodeExists(hasText("Test Update"));
        sleep(4000);
    }

    private void completeSessionFields() {
        moveTo("#sessionTextField").clickOn().write(SESSION_NAME);
        moveTo("#plusButton").clickOn();
    }

    private void moveToSessionsTab() {
        moveTo("Sessions").clickOn();
    }

    private void addMemberToEdition() {
        moveTo("#coChairTextField").clickOn().write(CO_CHAIR_USERNAME);
        clickOn("#plusButton");
    }

    private void moveToMembersTab() {
        moveTo("Members").clickOn();
    }

    private void completeEditionTextFields() {
        moveTo("#locationTextField").clickOn().write(EDITION_NAME);
        moveTo("#bioTextField").clickOn().write(EDITION_BIO);
    }

    private void clickOnAddEditionButton() {
        moveTo("Add Edition").clickOn();
    }

    private void clickOnPublishButton() {
        moveTo("Publish").clickOn();
    }

    private void completeConferenceTextFields() {
        completeConferenceTextFieldWith(CONFERENCE_NAME, "Test");
    }

    private void completeConferenceTextFieldWith(String name, String acronym) {
        moveTo("#nameTextField").clickOn().write(name);
        moveTo("#acronymTextField").clickOn().write(acronym);
    }

    private void clickOnAddConferenceButton() {
        moveTo("#addConferenceButton").clickOn();
    }

    @SuppressWarnings("all")
    private void loginInSystem(final String username, final String password) {
        moveTo("#loginButton").clickOn();
        clickOn("#usernameTextField").write(username);
        clickOn("#passwordTextField").write(password);
        moveTo("LOGIN").clickOn();
    }

    private void logoutFromSystem() {
        moveTo("#logoutButton").clickOn();
    }

}
