package translator;

import domain.SubmissionEntity;
import org.jetbrains.annotations.NotNull;
import transfarable.Submission;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class SubmissionTranslator implements GenericTranslator<SubmissionEntity, Submission> {

    @Override
    public Submission translate(@NotNull SubmissionEntity submission) {
        return new Submission(submission.getId(), submission.getName(), submission.getStatus(),
                submission.getAbstractUrl(), submission.getFullPaperUrl(), submission.isPaid());
    }

    @Override
    public SubmissionEntity translate(@NotNull Submission submission) {
        return new SubmissionEntity(submission.getId(), submission.getName(), submission.getStatus(),
                submission.getAbstractUrl(), submission.getFullPaperUrl(), submission.isPaid()) ;
    }

}
