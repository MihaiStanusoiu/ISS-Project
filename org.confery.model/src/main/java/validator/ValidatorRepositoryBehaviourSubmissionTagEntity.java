package validator;

import domain.SubmissionTagEntity;

import java.util.List;
import java.util.Objects;

import static utils.Conditional.basedOn;

/**
 * Tested: True
 *
 * @author Teodorescu Vlad
 * @version 1.0
 */

public class ValidatorRepositoryBehaviourSubmissionTagEntity
        extends ValidatorRepositoryBehaviour<SubmissionTagEntity> {

    /**
     * @param object : the submission tag to validate
     * @return [List<String>] : list of error messages found
     */
    @Override
    public List<String> check(SubmissionTagEntity object) {
        basedOn(Objects.isNull(object))
                .runTrue(accumulator::add, "Submission tag is NULL!");
        basedOn(Objects.isNull(object.getId()))
                .runTrue(accumulator::add, "Submission tag's id is NULL!");
        return accumulator;
    }
}
