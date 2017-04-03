package domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class ReviewTest {
    Review review;
    ReviewQualifier reviewQualifier;
    ReviewResponse reviewResponse;
    ReviewStatus reviewStatus;

    @Before
    public void setUp() throws Exception {
        review =new Review(0,1,1,reviewQualifier.fromString("StrongAgree"),reviewStatus.fromString("Reviewed"),reviewResponse.fromString("NotAssigned"),"buna lucrare");
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
        assertTrue(review.getReviewQualifier().equals(reviewQualifier.STRONG_AGREE));
    }

    @Test
    public void isGettingReviewStatus() throws Exception {
        assertTrue(review.getReviewStatus().equals(reviewStatus.REVIEWED));
    }

    @Test
    public void isGettingReviewResponse() throws Exception {
        assertTrue(review.getReviewResponse().equals(reviewResponse.NOT_ASSIGNED));
    }

    @Test
    public void isGettingRecommandationUrl() throws Exception {
        assertTrue(review.getRecommandationUrl().equals("buna lucrare"));
    }

}