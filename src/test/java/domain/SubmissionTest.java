package domain;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;


public class SubmissionTest {

    private Submission submission;
    private ArrayList<Integer> reviews;
    private ArrayList<Integer> tags;
    private ArrayList<Integer> topics;
    private Review review;
    private Tag tag;
    private Topic topic;
    private ArrayList<Integer> list = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        reviews = new ArrayList<>();
        tags = new ArrayList<>();
        topics = new ArrayList<>();
        review = new Review(9,1,1,
                ReviewQualifier.fromString("StrongAgree"), ReviewStatus.fromString("Reviewed"),
                ReviewResponse.fromString("NotAssigned"),"Good Job!");
        tag = new Tag(9,"Math");
        topic = new Topic(9,"CS");
        reviews.add(1);
        reviews.add(2);
        tags.add(1);
        tags.add(2);
        topics.add(1);
        topics.add(2);
        submission=new Submission(0,"Math", StatusTypeSubmission.fromString("Bid"),
                "a_url","f_url",true, reviews, topics, tags,0);
    }

    @Test
    public void getName() throws Exception {
        assertTrue(submission.getId().equals(0));
        assertTrue(submission.getName().equals("Math"));
    }

    @Test
    public void getStatus() throws Exception {
        assertTrue(submission.getStatus().equals(StatusTypeSubmission.BID_STATUS));
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
        list = submission.getTopics();
        assertTrue(list.equals(topics));
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
        submission.addReview(review);
        list = submission.getReviews();
        assertTrue(list.contains(9));
        assertTrue(list.contains(1));
        assertTrue(list.contains(2));
    }

    @Test
    public void removeReview() throws Exception {
        submission.removeReview(review);
        list = submission.getReviews();
        assertTrue(!list.contains(9));
    }

    @Test
    public void addTag() throws Exception {
        submission.addTag(tag);
        list = submission.getTags();
        assertTrue(list.contains(9));
    }

    @Test
    public void removeTag() throws Exception {

        submission.removeTag(tag);
        list = submission.getTags();
        assertTrue(!list.contains(9));
    }

    @Test
    public void addTopic() throws Exception {
        submission.addTopic(topic);
        list = submission.getTopics();
        assertTrue(list.contains(9));
    }

    @Test
    public void removeTopic() throws Exception {
        submission.removeTopic(topic);
        list = submission.getTopics();
        assertTrue(!list.contains(9));
    }

}