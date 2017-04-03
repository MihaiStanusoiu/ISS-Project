package domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;


public class SubmissionTest {
    private Submission submission;
    private ArrayList<Integer> reviews;
    private ArrayList<Integer> tags;
    private ArrayList<Integer> topics;
    private StatusTypeSubmission status;
    private Review rr;
    private Tag tag;
    private Topic topic;
    private ReviewQualifier reviewQualifier;
    private ReviewResponse reviewResponse;
    private ReviewStatus reviewStatus;

    @Before
    public void setUp() throws Exception {
        reviews=new ArrayList<>();
        tags=new ArrayList<>();
        topics=new ArrayList<>();
        rr=new Review(9,1,1,reviewQualifier.fromString("StrongAgree"),reviewStatus.fromString("Reviewed"),reviewResponse.fromString("NotAssigned"),"buna lucrare");
        tag=new Tag(9,"particule");
        topic=new Topic(9,"fizica");
        reviews.add(1);
        reviews.add(2);
        tags.add(1);
        tags.add(2);
        topics.add(1);
        topics.add(2);
        submission=new Submission(0,"Math",status.fromString("Bid"),"a_url","f_url",true,reviews,topics,tags,0);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getName() throws Exception {
        assertTrue(submission.getId().equals(0));
        assertTrue(submission.getName().equals("Math"));
    }

    @Test
    public void getStatus() throws Exception {
        assertTrue(submission.getStatus().equals(status.BID_STATUS));
    }

    @Test
    public void getAbstractUrl() throws Exception {
        assertTrue(submission.getAbstractUrl().equals("a_url"));
    }

    @Test
    public void getFullPaperUrl() throws Exception {
        assertTrue(submission.getFullPaperUrl().equals("f_url"));
    }

    @Test
    public void getPaid() throws Exception {
        assertTrue(submission.getPaid().equals(true));
    }

    @Test
    public void getReviews() throws Exception {
        assertTrue(submission.getReviews().equals(reviews));
    }

    @Test
    public void getTopics() throws Exception {
        ArrayList<Integer> lista=new ArrayList<>();
        lista=submission.getTopics();
        assertTrue(lista.equals(topics));
    }

    @Test
    public void getTags() throws Exception {
        assertTrue(submission.getTags().equals(tags));
    }

    @Test
    public void getIdConference() throws Exception {
        assertTrue(submission.getIdConference().equals(0));
    }

    @Test
    public void addReview() throws Exception {
        ArrayList<Integer> lista=new ArrayList<>();
        submission.addReview(rr);
        lista=submission.getReviews();
        assertTrue(lista.contains(9));
        assertTrue(lista.contains(1));
        assertTrue(lista.contains(2));
    }

    @Test
    public void removeReview() throws Exception {
        ArrayList<Integer> lista=new ArrayList<>();
        submission.removeReview(rr);
        lista=submission.getReviews();
        assertTrue(!lista.contains(9));
    }

    @Test
    public void addTag() throws Exception {
        ArrayList<Integer> lista=new ArrayList<>();
        submission.addTag(tag);
        lista=submission.getTags();
        assertTrue(lista.contains(9));
    }

    @Test
    public void removeTag() throws Exception {
        ArrayList<Integer> lista=new ArrayList<>();
        submission.removeTag(tag);
        lista=submission.getTags();
        assertTrue(!lista.contains(9));
    }

    @Test
    public void addTopic() throws Exception {
        ArrayList<Integer> lista=new ArrayList<>();
        submission.addTopic(topic);
        lista=submission.getTopics();
        assertTrue(lista.contains(9));
    }

    @Test
    public void removeTopic() throws Exception {
        ArrayList<Integer> lista=new ArrayList<>();
        submission.removeTopic(topic);
        lista=submission.getTopics();
        assertTrue(!lista.contains(9));
    }

}