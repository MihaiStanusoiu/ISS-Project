package domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TagTest {

    private Tag tag;

    @Before
    public void setUp() throws Exception {
        tag = new Tag(0,"Math");
    }

    @Test
    public void isGettingContent() throws Exception {
        assertTrue(tag.getContent().equals("Math"));
        assertTrue(tag.getId().equals(0));
    }

    @Test
    public void isSettingContent() throws Exception {
        tag.setContent("CS");
        assertTrue(tag.getContent().equals("CS"));
    }

}