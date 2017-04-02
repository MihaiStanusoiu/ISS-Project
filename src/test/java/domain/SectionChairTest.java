package domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class SectionChairTest {
    private SectionChair sectionChair;

    @Before
    public void setUp() throws Exception {
        sectionChair = new SectionChair(
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
        assertTrue(sectionChair.getType().equals(UserType.SECTION_CHAIR));
    }

    @Test
    public void isGettingPermissions() throws Exception {
        assertTrue(sectionChair.getPermissions().contains(Permission.CREATE_SECTION));
        assertTrue(sectionChair.getPermissions().contains(Permission.DELETE_SECTION));
        assertTrue(sectionChair.getPermissions().contains(Permission.UPDATE_SECTION));
    }

}