package validator;

import domain.SubmissionEntity;

import java.util.List;
import java.util.Objects;

import static utils.Conditional.basedOn;

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
        basedOn(Objects.isNull(object))
                .runTrue(accumulator::add, "Submission is NULL!");
        basedOn(Objects.isNull(object.getId()))
                .runTrue(accumulator::add, "Submission's id is NULL!");
        basedOn(Objects.isNull(object.getAbstractUrl()))
                .runTrue(accumulator::add, "Submission's abstract url is INVALID!");
        basedOn(Objects.isNull(object.getFullPaperUrl()))
                .runTrue(accumulator::add, "Submission's full paper url is INVALID!");
        basedOn(Objects.isNull(object.isPaid()))
                .runTrue(accumulator::add, "Submission's isPaid is NULL!");
        return accumulator;
    }
}
