package domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class SessionMemberTest {

    private SessionMember sessionMember;

    @Before
    public void setUp() throws Exception {
        sessionMember = new SessionMember(
                1,
                "mike",
                "1234",
                "mihai@gmail.com",
                "Mihai",
                "mihai.com",
                "Student",
                "str...",
                1
        );
        sessionMember.setIdSection(1);
    }

    @Test
    public void isGettingIdSection() throws Exception {
        assertTrue(sessionMember.getIdSection().equals(1));
    }

    @Test
    public void isSettingSection() throws Exception {
        sessionMember.setIdSection(2);
        assertTrue(sessionMember.getIdSection().equals(2));
    }

    @Test
    public void isGettingType() throws Exception {
        assertTrue(sessionMember.getType().equals(UserType.SECTION_MEMBER));
    }

    @Test
    public void isGettingPermissions() throws Exception {
        assertTrue(sessionMember.getPermissions().contains(Permission.PAY));
    }

}