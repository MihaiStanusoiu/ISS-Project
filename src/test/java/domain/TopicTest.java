package domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TopicTest {
    private Topic t;
    @Before
    public void setUp() throws Exception {
        t = new Topic(0,"quantic");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getContent() throws Exception {
        assertTrue(t.getContent().equals("quantic"));
        assertTrue(t.getId().equals(0));
    }

    @Test
    public void setContent() throws Exception {
        t.setContent("whatever");
        assertTrue(t.getContent().equals("whatever"));
    }

}