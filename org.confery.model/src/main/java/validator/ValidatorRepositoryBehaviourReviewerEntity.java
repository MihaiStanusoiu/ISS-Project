package validator;

import domain.ReviewerEntity;

import java.util.List;
import java.util.Objects;

import static utils.Conditional.basedOn;

/**
 * Tested: True
 *
 * @author Teodorescu Vlad
 * @version 1.0
 */

public class ValidatorRepositoryBehaviourReviewerEntity
        extends ValidatorRepositoryBehaviour<ReviewerEntity> {
    /**
     * @param object : the reviewer to validate
     * @return [List<String>] : list of error messages found
     */

    @Override
    public List<String> check(ReviewerEntity object) {
        basedOn(Objects.isNull(object))
                .runTrue(accumulator::add, "Reviewer is NULL!");
        basedOn(Objects.isNull(object.getId()))
                .runTrue(accumulator::add, "Reviewer's id is NULL!");
        basedOn(Objects.isNull(object.getQualifier()))
                .runTrue(accumulator::add, "Reviewer's qualifier is NULL!");
        basedOn(Objects.isNull(object.getRecommendationUrl()))
                .runTrue(accumulator::add, "Reviewer's recommendation url is NULL!");
        basedOn(Objects.isNull(object.getResponse()))
                .runTrue(accumulator::add, "Reviewer's response is NULL!");
        basedOn(Objects.isNull(object.getStatus()) || object.getStatus().equals(""))
                .runTrue(accumulator::add, "Reviewer's status is INVALID!");
        return accumulator;
    }
}
