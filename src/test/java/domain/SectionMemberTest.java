package domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class SectionMemberTest {

    private SectionMember sectionMember;

    @Before
    public void setUp() throws Exception {
        sectionMember = new SectionMember(
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
        sectionMember.setIdSection(1);
    }

    @Test
    public void isGettingIdSection() throws Exception {
        assertTrue(sectionMember.getIdSection().equals(1));
    }

    @Test
    public void isSettingSection() throws Exception {
        sectionMember.setIdSection(2);
        assertTrue(sectionMember.getIdSection().equals(2));
    }

    @Test
    public void isGettingType() throws Exception {
        assertTrue(sectionMember.getType().equals(UserType.SECTION_MEMBER));
    }

    @Test
    public void isGettingPermissions() throws Exception {
        assertTrue(sectionMember.getPermissions().contains(Permission.PAY));
    }

}