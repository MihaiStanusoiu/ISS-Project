package validator;

import domain.SubmissionTopicEntity;

import java.util.List;

/**
 * Name:         Validator behaviour for SubmissionTopicEntity
 * Effect:       Validates a submission topic
 * Date:         06/05/2017
 * Tested:       False
 *
 * @author Teodorescu Vlad
 * @version 1.0
 */
@SuppressWarnings("all")
public class ValidatorRepositoryBehaviourSubmissionTopicEntity extends ValidatorRepositoryBehaviour<SubmissionTopicEntity> {

    /**
     * @param object : the submission topic to validate
     * @return [List<String>] : list of error messages found
     */
    @Override
    public List<String> check(SubmissionTopicEntity object) {
        if (object.getId() == null) {
            accumulator.add("Submission topic id is null.");
        }
        if (object.getSubmissionTopic() == null) {
            accumulator.add("Submission topic submission is null.");
        }
        if (object.getTopic() == null) {
            accumulator.add("Submission topic topic is null.");
        }
        return accumulator;
    }
}
