package domain;

import org.junit.Test;
import static org.junit.Assert.*;

public class StatusTypeSubmissionTest {

    @Test
    public void isGettingFromString() throws Exception {
        assertTrue(StatusTypeSubmission.fromString("Rejected").equals(StatusTypeSubmission.REJECTED_STATUS));
        assertTrue(StatusTypeSubmission.fromString("Accepted").equals(StatusTypeSubmission.ACCEPTED_STATUS));
        assertTrue(StatusTypeSubmission.fromString("Bid").equals(StatusTypeSubmission.BID_STATUS));
        assertTrue(StatusTypeSubmission.fromString("Review").equals(StatusTypeSubmission.REVIEW_STATUS));
    }

}