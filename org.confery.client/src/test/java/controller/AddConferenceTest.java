package controller;

import Utils.UtilsForTests;
import javafx.geometry.VerticalDirection;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
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
    private final String USERNAME = "oana.daniela";
    private final String PASSWORD = "password";
    private final String SESSION_NAME = "Test Session";
    private final String CO_CHAIR_USERNAME = "oana.daniela";
    private final String PCMEMBER_USERNAME = "iulia.dorian";
    UtilsForTests utils = new UtilsForTests();
    private final Integer delay = 6000;

    @Override @Before
    public void setUp() throws Exception {
        super.setUp();
        utils.forceLoginInSystem(USERNAME,PASSWORD);
    }

    @Override @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void mainTest() throws Exception
    {
        isAbleToAddConference();
        isAbleToUpdateConference();
        isAbleToUpdateEditionInfo();
        isAbleToUpdateEditionMembers();
        isOneConferenceAvailable();
        isOneSessionAvailable();
        submitAPaper();
        isAbleToDeleteConference();
        isAbleToSignUp();
        isAbleToModifyUser();
        isAbleToDeleteUser();
    }

    @Test
    public void isAbleToAddConference() throws Exception {
        // when:
        utils.addConference(CONFERENCE_NAME,EDITION_NAME, EDITION_BIO, CO_CHAIR_USERNAME,SESSION_NAME);
        // go-to:
        moveTo(CONFERENCE_NAME).clickOn();
        moveTo(EDITION_NAME).clickOn();
        // then:
        assertNodeExists(hasText("Oana Daniela"));
        assertNodeExists(hasText("Popescu Dan"));
        assertNodeExists(hasText(SESSION_NAME));
        assertNodeExists(hasText(EDITION_NAME));
        assertNodeExists(hasText(EDITION_BIO));
        sleep(delay);
        moveTo("CONFERY").clickOn();
    }

    @Test
    public void isAbleToUpdateConference() throws Exception {
        // to-go:
        moveTo(CONFERENCE_NAME).clickOn();
        utils.updateConferenceTextFieldWith("Test Update", "Test Update");
        utils.clickOnSaveButton();
        assertNodeExists(hasText("Test Update"));
        sleep(delay);
    }

    @Test
    public void isAbleToUpdateEditionInfo() throws Exception {
        // to-go:
        moveTo("Test Update").clickOn();
        moveTo("Test Edition").clickOn();
        utils.updateEditionTextFieldWith("Test Edition Update", "Test Bio Update");
        scroll(25, VerticalDirection.DOWN);
        utils.clickOnSaveButton();
        moveTo("CONFERY").clickOn();
        moveTo("Test Update").clickOn();
        assertNodeExists(hasText("Test Edition Update"));
        sleep(delay);
    }

    @Test
    public void isAbleToUpdateEditionMembers() throws Exception {
        // to-go:
        moveTo("CONFERY").clickOn();
        moveTo("Test Update").clickOn();
        moveTo("Test Edition Update").clickOn();
        moveTo("Members").clickOn();
        scroll(25, VerticalDirection.DOWN);
        utils.addPCMemberToEdition(PCMEMBER_USERNAME);
        sleep(500);
        scroll(25, VerticalDirection.DOWN);
        assertNodeExists(hasText("Iulia Dorian"));
        assertNodeExists(hasText("Test Edition Update"));
        sleep(delay);
    }

    @Test
    public void isOneConferenceAvailable() throws Exception
    {
        moveTo("CONFERY").clickOn();
        moveTo("#myConferences").clickOn();
        assertNodeExists(hasText("Test Update"));
        sleep(delay);
    }

    @Test
    public void isOneSessionAvailable() throws Exception
    {
        moveTo("CONFERY").clickOn();
        moveTo("Test Update").clickOn();
        moveTo("Test Edition Update").clickOn();
        moveTo("Sessions").clickOn();
        assertNodeExists(hasText("Test Session"));
        sleep(delay);
    }

    @Test
    public void submitAPaper() throws Exception
    {
        utils.logoutFromSystem();
        utils.loginInSystem("iulia.dorian", "password");
        utils.addConference("Test2","Test2 Edition", "Test 2 Bio", "iulia.dorian","Test 2 session");
        utils.logoutFromSystem();
        utils.loginInSystem(USERNAME,PASSWORD);
        moveTo("CONFERY").clickOn();
        moveTo("Test2").clickOn();
        moveTo("Test2 Edition").clickOn();
        moveTo("Submit Papers").clickOn();
        utils.addSubmission("Test Submission", "Test Abstract", "Test full paper", "oana.daniela", "Test", "Test");
        assertNodeExists(hasText("Test Submission"));
        sleep(delay);
    }


    @Test
    public void isAbleToDeleteConference() throws Exception
    {
        moveTo("CONFERY").clickOn();
        moveTo("Test Update").clickOn();
        moveTo("Delete").clickOn();
        utils.logoutFromSystem();
        utils.loginInSystem("iulia.dorian", "password");
        moveTo("CONFERY").clickOn();
        moveTo("Test2").clickOn();
        moveTo("Delete").clickOn();
        utils.logoutFromSystem();
        assertEquals(find("Test Update"), null);
        sleep(delay);
    }

    @Test
    public void isAbleToSignUp()
    {
        moveTo("CONFERY").clickOn();
        moveTo("#signUpButton").clickOn();
        utils.addUser("example@example", "Test", "test.test", "password123");
        assertNodeExists(hasText("Test"));
        sleep(delay);
    }

    @Test
    public void isAbleToModifyUser()
    {
        moveTo("#profileButton").clickOn();
        utils.modifyUser("TestTest");
        moveTo("CONFERY").clickOn();
        assertNodeExists(hasText("TestTest"));
        sleep(delay);
    }

    @Test
    public void isAbleToDeleteUser()
    {
        moveTo("#profileButton").clickOn();
        clickOn("Delete Your Account");
        assertEquals(find("TestTest"), null);
        sleep(delay);
    }
}
