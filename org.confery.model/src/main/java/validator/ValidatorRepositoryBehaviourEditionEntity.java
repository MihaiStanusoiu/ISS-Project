package validator;

import domain.EditionEntity;

import java.util.List;
import java.util.Objects;

import static utils.Conditional.basedOn;

/**
 * Tested: True
 *
 * @author Tanasie Luiza Maria & Teodorescu Vlad
 * @version 1.0
 */

public class ValidatorRepositoryBehaviourEditionEntity
        extends ValidatorRepositoryBehaviour<EditionEntity> {

    /**
     * @param object : the edition to validate
     * @return [List<String>] : list of error messages found
     */
    @Override
    public List<String> check(EditionEntity object) {
        basedOn(Objects.isNull(object))
                .runTrue(accumulator::add, "Edition is NULL!");
        basedOn(Objects.isNull(object.getBio()))
                .runTrue(accumulator::add, "Edition's bio is NULL!");
        basedOn(Objects.isNull(object.getAbstractDeadline()))
                .runTrue(accumulator::add, "Edition's abstract deadline is NULL!");
        basedOn(Objects.isNull(object.getBiddingDeadline()))
                .runTrue(accumulator::add, "Edition's bidding deadline is NULL!");
        basedOn(Objects.isNull(object.getEndDate()))
                .runTrue(accumulator::add, "Edition's end date is NULL!");
        basedOn(Objects.isNull(object.getEvaluationDeadline()))
                .runTrue(accumulator::add, "Edition's evaluation date is NULL!");
        basedOn(Objects.isNull(object.getId()))
                .runTrue(accumulator::add, "Edition's id is NULL!");
        basedOn(Objects.isNull(object.getLocation()))
                .runTrue(accumulator::add, "Edition's location is INVALID!");
        basedOn(Objects.isNull(object.getPaperDeadline()))
                .runTrue(accumulator::add, "Edition's paper deadline is NULL!");
        basedOn(Objects.isNull(object.getStartDate()))
                .runTrue(accumulator::add, "Edition's start date is NULL!");
        return accumulator;
    }
}
