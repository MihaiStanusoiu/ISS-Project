package Utils;

import controller.TestFxBase;
import javafx.geometry.VerticalDirection;
import javafx.scene.input.KeyCode;

/**
 * Created by Vlad on 6/5/2017.
 */
public class UtilsForTests extends TestFxBase {

    public void addConference(String CONFERENCE_NAME,String EDITION_NAME, String EDITION_BIO, String CO_CHAIR_USERNAME, String SESSION_NAME) {
        clickOnAddConferenceButton();
        completeConferenceTextFields(CONFERENCE_NAME);
        clickOnAddEditionButton();
        completeEditionTextFields(EDITION_NAME,EDITION_BIO);
        moveToMembersTab();
        addMemberToEdition(CO_CHAIR_USERNAME);
        moveToSessionsTab();
        completeSessionFields(SESSION_NAME);
        clickOnPublishButton();
    }

    private void clickOnAddConferenceButton() {
        moveTo("#addConferenceButton").clickOn();
    }

    private void completeConferenceTextFields(String CONFERENCE_NAME) {
        completeConferenceTextFieldWith(CONFERENCE_NAME, "Test");
    }

    public void completeConferenceTextFieldWith(String name, String acronym) {
        moveTo("#nameTextField").clickOn().write(name);
        moveTo("#acronymTextField").clickOn().write(acronym);
    }

    public void updateConferenceTextFieldWith(String name, String acronym) {
        moveTo("#nameTextField").clickOn().type(KeyCode.BACK_SPACE, 15);
        moveTo("#nameTextField").clickOn().write(name);
        moveTo("#acronymTextField").clickOn().type(KeyCode.BACK_SPACE, 4);
        moveTo("#acronymTextField").clickOn().write(acronym);
    }

    private void clickOnAddEditionButton() {
        moveTo("Add Edition").clickOn();
    }

    private void completeEditionTextFields(String EDITION_NAME, String EDITION_BIO) {
        moveTo("#locationTextField").clickOn().write(EDITION_NAME);
        moveTo("#bioTextField").clickOn().write(EDITION_BIO);
    }

    private void moveToMembersTab() {
        moveTo("Members").clickOn();
    }

    private void addMemberToEdition(String CO_CHAIR_USERNAME) {
        moveTo("#coChairTextField").clickOn().write(CO_CHAIR_USERNAME);
        clickOn("#plusButton");
    }

    private void moveToSessionsTab() {
        moveTo("Sessions").clickOn();
    }

    private void completeSessionFields(String SESSION_NAME) {
        moveTo("#sessionTextField").clickOn().write(SESSION_NAME);
        moveTo("#plusButton").clickOn();
    }

    public void clickOnPublishButton() {
        moveTo("Publish").clickOn();
    }

    public void clickOnSaveButton() {
        moveTo("Save").clickOn();
    }

    public void forceLoginInSystem(String USERNAME,String PASSWORD) {
        try {
            loginInSystem(USERNAME, PASSWORD);
        } catch (Exception exception ) {
            logoutFromSystem();
            loginInSystem(USERNAME, PASSWORD);
        }
    }

    public void loginInSystem(final String username, final String password) {
        moveTo("#loginButton").clickOn();
        clickOn("#usernameTextField").write(username);
        clickOn("#passwordTextField").write(password);
        moveTo("LOGIN").clickOn();
    }

    public void logoutFromSystem() {
        moveTo("#logoutButton").clickOn();
    }

    public void updateEditionTextFieldWith(String name, String acronym) {
        moveTo("#locationTextField").clickOn().type(KeyCode.BACK_SPACE, 12);
        moveTo("#locationTextField").clickOn().write(name);
        moveTo("#bioTextField").clickOn().type(KeyCode.BACK_SPACE, 11);
        moveTo("#bioTextField").clickOn().write(acronym);
    }

    public void addPCMemberToEdition(String name)
    {
        clickOn("#pcMemberTextField").write(name);
        moveTo("#addPCMemberButton").clickOn();
    }

    public void addSubmission(String name, String abs, String full_paper, String author, String tag, String topic)
    {
        clickOn("#nameTextField").write(name);
        clickOn("#abstractTextField").write(abs);
        clickOn("#fullPaperTextField").write(full_paper);
        scroll(25, VerticalDirection.DOWN);
        clickOn("#authorTextField").write(author);
        clickOn("#addAuthorButton");
        clickOn("#tagTextField").write(tag);
        clickOn("#addTagButton");
        clickOn("#topicTextField").write(topic);
        clickOn("#addTopicButton");
        scroll(25, VerticalDirection.UP);
        clickOn("Submit");
    }

    public void addUser(String email, String display_name, String username, String password)
    {
        clickOn("#emailTextField").write(email);
        clickOn("#displayNameTextField").write(display_name);
        clickOn("#usernameTextField").write(username);
        clickOn("#passwordTextField").write(password);
        clickOn("#confirmTextField").write(password);
        clickOn("#signUpButton");
    }

    public void modifyUser(String new_name)
    {
        moveTo("#nameTextField").clickOn().type(KeyCode.BACK_SPACE, 4);
        clickOn("#nameTextField").write(new_name);
        clickOn("Save");
    }
}
