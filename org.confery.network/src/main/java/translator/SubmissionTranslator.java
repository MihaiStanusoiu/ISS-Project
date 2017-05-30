package translator;

import domain.SubmissionEntity;
import transfarable.Submission;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class SubmissionTranslator {

    public static Submission translate(SubmissionEntity submission) {
        return new Submission(submission.getId(), submission.getName(), submission.getStatus(),
                submission.getAbstractUrl(), submission.getFullPaperUrl(), submission.isPaid());
    }


    public static SubmissionEntity translate(Submission submission) {
        return new SubmissionEntity(submission.getId(), submission.getName(), submission.getStatus(),
                submission.getAbstractUrl(), submission.getFullPaperUrl(), submission.isPaid()) ;
    }

}
