package domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TagTest {
    private Tag t;
    @Before
    public void setUp() throws Exception {
     t = new Tag(0,"mate");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void isGettingContent() throws Exception {
        assertTrue(t.getContent().equals("mate"));
        assertTrue(t.getId().equals(0));
    }

    @Test
    public void isSettingContent() throws Exception {
        t.setContent("info");
        assertTrue(t.getContent().equals("info"));
    }

}