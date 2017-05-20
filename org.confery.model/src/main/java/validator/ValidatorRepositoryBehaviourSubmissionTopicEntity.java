package validator;

import domain.SubmissionTopicEntity;

import java.util.List;
import java.util.Objects;

import static utils.Conditional.basedOn;

/**
 * @author Teodorescu Vlad
 * @version 1.0
 */

@SuppressWarnings("all")
public class ValidatorRepositoryBehaviourSubmissionTopicEntity
        extends ValidatorRepositoryBehaviour<SubmissionTopicEntity> {

    /**
     * @param object : The submission topic to validate
     * @return [List<String>] : List of error messages found
     */
    @Override
    public List<String> check(SubmissionTopicEntity object) {
        basedOn(Objects.isNull(object))
                .runTrue(accumulator::add, "Submission topic is NULL!");
        basedOn(Objects.isNull(object.getId()))
                .runTrue(accumulator::add, "Submission topic's id is NULL!");
        return accumulator;
    }
}
