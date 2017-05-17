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
        basedOn(Objects.isNull(object.getAbstractUrl()) || object.getAbstractUrl().equals(""))
                .runTrue(accumulator::add, "Submission's abstract url is INVALID!");
        basedOn(Objects.isNull(object.getEdition()))
                .runTrue(accumulator::add, "Submission's edition is NULL!");
        basedOn(Objects.isNull(object.getFullPaperUrl()) || object.getFullPaperUrl().equals(""))
                .runTrue(accumulator::add, "Submission's full paper url is INVALID!");
        basedOn(Objects.isNull(object.getName()) || object.getName().equals(""))
                .runTrue(accumulator::add, "Submission's name is INVALID!");
        basedOn(Objects.isNull(object.getReviewers()))
                .runTrue(accumulator::add, "Submission's reviewers is NULL!");
        basedOn(Objects.isNull(object.getStatus()) || object.getStatus().equals(""))
                .runTrue(accumulator::add, "Submission's status is INVALID!");
        basedOn(Objects.isNull(object.getSubmissionAuthors()))
                .runTrue(accumulator::add, "Submission's authors is NULL!");
        basedOn(Objects.isNull(object.getSubmissionTags()))
                .runTrue(accumulator::add, "Submission's tags is NULL!");
        basedOn(Objects.isNull(object.getSubmissionTopic()))
                .runTrue(accumulator::add, "Submission's topic is NULL!");
        basedOn(Objects.isNull(object.isPaid()))
                .runTrue(accumulator::add, "Submission's isPaid is NULL!");
        return accumulator;
    }
}
