package validator;

import domain.SubmissionTopicEntity;

import java.util.List;
import java.util.Objects;

import static utils.Conditional.basedOn;

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
        basedOn(Objects.isNull(object))
                .runTrue(accumulator::add, "Submission topic is NULL!");
        basedOn(Objects.isNull(object.getId()))
                .runTrue(accumulator::add, "Submission topic's id is NULL!");
        basedOn(Objects.isNull(object.getSubmissionTopic()))
                .runTrue(accumulator::add, "Submission topic's submission is NULL!");
        basedOn(Objects.isNull(object.getTopic()))
                .runTrue(accumulator::add, "Submission topic's topic is NULL!");
        return accumulator;
    }
}
