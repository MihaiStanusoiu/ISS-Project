package validator;

import domain.SessionEntity;

import java.util.List;
import java.util.Objects;

import static utils.Conditional.basedOn;

/**
 * Tested: True
 *
 * @author Teodorescu Vlad
 * @version 1.0
 */

public class ValidatorRepositoryBehaviourSessionEntity
        extends ValidatorRepositoryBehaviour<SessionEntity> {
    /**
     * @param object : the session to validate
     * @return [List<String>] : list of error messages found
     */
    @Override
    public List<String> check(SessionEntity object) {
        basedOn(Objects.isNull(object))
                .runTrue(accumulator::add, "Session is NULL!");
        basedOn(Objects.isNull(object.getId()))
                .runTrue(accumulator::add, "Session's id is NULL!");
        basedOn(Objects.isNull(object.getBio()))
                .runTrue(accumulator::add, "Session's bio is NULL!");
        basedOn(Objects.isNull(object.getEndDate()))
                .runTrue(accumulator::add, "Session's end date is NULL!");
        basedOn(Objects.isNull(object.getLocation()))
                .runTrue(accumulator::add, "Session's location is NULL!");
        basedOn(Objects.isNull(object.getName()) || object.getName().equals(""))
                .runTrue(accumulator::add, "Session's name is INVALID!");
        basedOn(Objects.isNull(object.getSeats()))
                .runTrue(accumulator::add, "Session's seats is NULL!");
        basedOn(Objects.isNull(object.getStartDate()))
                .runTrue(accumulator::add, "Session's start date is NULL!");
        return accumulator;
    }
}
