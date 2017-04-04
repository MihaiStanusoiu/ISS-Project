package domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class ReviewTest {

    private Review review;

    @Before
    public void setUp() throws Exception {
        review = new Review(0,1,1, ReviewQualifier.fromString("StrongAgree"),
                ReviewStatus.fromString("Reviewed"), ReviewResponse.fromString("NotAssigned"),"Good Job!");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getIdSubmission() throws Exception {
        assertTrue(review.getId().equals(0));
        assertTrue(review.getIdSubmission().equals(1));
    }

    @Test
    public void isGetttingIdReviewer() throws Exception {
        assertTrue(review.getIdReviewer().equals(1));
    }

    @Test
    public void isGettingReviewQualifier() throws Exception {
        assertTrue(review.getReviewQualifier().equals(ReviewQualifier.STRONG_AGREE));
    }

    @Test
    public void isGettingReviewStatus() throws Exception {
        assertTrue(review.getReviewStatus().equals(ReviewStatus.REVIEWED));
    }

    @Test
    public void isGettingReviewResponse() throws Exception {
        assertTrue(review.getReviewResponse().equals(ReviewResponse.NOT_ASSIGNED));
    }

    @Test
    public void isGettingRecommendationUrl() throws Exception {
        assertTrue(review.getRecommendationUrl().equals("Good Job!"));
    }

}