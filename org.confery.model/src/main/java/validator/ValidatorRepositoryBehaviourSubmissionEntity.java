package validator;

import domain.SubmissionEntity;

import java.util.List;

/**
 * Name:         Validator behaviour for SubmissionEntity
 * Effect:       Validates a submission
 * Date:         06/05/2017
 * Tested:       False
 *
 * @author Teodorescu Vlad
 * @version 1.0
 */
@SuppressWarnings("all")
public class ValidatorRepositoryBehaviourSubmissionEntity extends ValidatorRepositoryBehaviour<SubmissionEntity> {
    /**
     * @param object : the submission to validate
     * @return [List<String>] : list of error messages found
     */

    @Override
    public List<String> check(SubmissionEntity object) {
        if (object.getId() == null) {
            accumulator.add("Submission id is null.");
        }
        if(object.getAbstractUrl() != null) {
            if (object.getAbstractUrl().equals("")) {
                accumulator.add("Submission abstract url is empty.");
            }
        }
        else
        {
            accumulator.add("Submission abstract url is null.");
        }
        if (object.getEdition() == null) {
            accumulator.add("Submission edition is null.");
        }
        if(object.getFullPaperUrl() != null) {
            if (object.getFullPaperUrl().equals("")) {
                accumulator.add("Submission full paper url is empty.");
            }
        }
        else{
            accumulator.add("Submission full paper url is null.");
        }
        if(object.getName() != null) {
            if (object.getName().equals("")) {
                accumulator.add("Submission name is empty.");
            }
        }
        else
        {
            accumulator.add("Submission name is null.");
        }
        if (object.getReviewers() == null) {
            accumulator.add("Submission reviewers is null.");
        }
        if(object.getStatus() != null) {
            if (object.getStatus().equals("")) {
                accumulator.add("Submission status is empty.");
            }
        }
        else
        {
            accumulator.add("Submission status is null.");
        }
        if (object.getSubmissionAuthors() == null) {
            accumulator.add("Submission submissionAuthors is null.");
        }
        if (object.getSubmissionTags() == null) {
            accumulator.add("Submission submissionTags is null.");
        }
        if (object.getSubmissionTopic() == null) {
            accumulator.add("Submission submissionTopic is null.");
        }
        if (object.isPaid() == null) {
            accumulator.add("Submission isPaid is null.");
        }
        return accumulator;
    }
}
