package domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TopicTest {

    private Topic topic;

    @Before
    public void setUp() throws Exception {
        topic = new Topic(0,"Topic");
    }

    @Test
    public void getContent() throws Exception {
        assertTrue(topic.getContent().equals("Topic"));
        assertTrue(topic.getId().equals(0));
    }

    @Test
    public void setContent() throws Exception {
        topic.setContent("TopicUpdate");
        assertTrue(topic.getContent().equals("TopicUpdate"));
    }

}