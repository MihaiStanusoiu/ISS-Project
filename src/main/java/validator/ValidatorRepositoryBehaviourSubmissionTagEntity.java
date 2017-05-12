package validator;

import domain.SubmissionTagEntity;

import java.util.List;

/**
 * Name:         Validator behaviour for SubmissionTagEntity
 * Effect:       Validates a submission tag
 * Date:         06/05/2017
 * Tested:       False
 *
 * @author Teodorescu Vlad
 * @version 1.0
 */
@SuppressWarnings("all")
public class ValidatorRepositoryBehaviourSubmissionTagEntity extends ValidatorRepositoryBehaviour<SubmissionTagEntity> {
    /**
     * @param object : the submission tag to validate
     * @return [List<String>] : list of error messages found
     */
    @Override
    public List<String> check(SubmissionTagEntity object) {
        if (object.getId() == null) {
            accumulator.add("SubmissionTag id is null.");
        }
        if (object.getTag() == null) {
            accumulator.add("SubmissionTag tag is null.");
        }
        if (object.getSubmissionTag() == null) {
            accumulator.add("SubmissionTag submission is null.");
        }
        return accumulator;
    }
}
