package domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class SessionChairTest {

    private SessionChair sessionChair;

    @Before
    public void setUp() throws Exception {
        sessionChair = new SessionChair(
                1,
                "mike",
                "1234",
                "mihai@gmail.com",
                "Mihai",
                "mihai.com",
                "Student",
                "str...",
                1,
                1
        );
    }

    @Test
    public void isGettingType() throws Exception {
        assertTrue(sessionChair.getType().equals(UserType.SECTION_CHAIR));
    }

    @Test
    public void isGettingPermissions() throws Exception {
        assertTrue(sessionChair.getPermissions().contains(Permission.CREATE_SECTION));
        assertTrue(sessionChair.getPermissions().contains(Permission.DELETE_SECTION));
        assertTrue(sessionChair.getPermissions().contains(Permission.UPDATE_SECTION));
    }

}