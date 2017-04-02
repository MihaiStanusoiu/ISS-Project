package domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class ReviewerTest {
    private Reviewer reviewer;

    @Before
    public void setUp() throws Exception {
        reviewer = new Reviewer(1, 1, 1);
    }

    @Test
    public void getType() throws Exception {
        assertTrue(reviewer.getType().equals(UserType.REVIEWER));
    }

    @Test
    public void getPermissions() throws Exception {
        assertTrue(reviewer.getPermissions().contains(Permission.REVIEW));
    }

}