package domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StatusTypeSubmissionTest {
    StatusTypeSubmission status;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void gettingfromString() throws Exception {
        assertTrue(status.fromString("Rejected").equals(status.REJECTED_STATUS));
        assertTrue(status.fromString("Accepted").equals(status.ACCEPTED_STATUS));
        assertTrue(status.fromString("Bid").equals(status.BID_STATUS));
        assertTrue(status.fromString("Review").equals(status.REVIEW_STATUS));
    }

}