package domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class EditionMemberTest {

    private EditionMember user;

    @Before
    public void setUp() throws Exception {
        user = new EditionMember(
                1,
                "mike",
                "1234",
                "mihai@gmail.com",
                "Mihai",
                "mihai.com",
                "Student",
                "str..."
        );
        user.setIdConference(1);
    }

    @Test
    public void isGettingIdConference() throws Exception {
        assertTrue(user.getIdConference().equals(1));
    }

    @Test
    public void getPermissions() throws Exception {
        assertTrue(user.getPermissions().contains(Permission.VIEW_CONFERENCE));
    }

}