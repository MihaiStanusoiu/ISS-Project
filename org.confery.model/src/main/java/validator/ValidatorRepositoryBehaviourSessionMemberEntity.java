package validator;

import domain.SessionMemberEntity;

import java.util.List;
import java.util.Objects;

import static utils.Conditional.basedOn;

/**
 * Name:         Validator behaviour for SessionMemberEntity
 * Effect:       Validates a session member
 * Date:         06/05/2017
 * Tested:       False
 *
 * @author Teodorescu Vlad
 * @version 1.0
 */

@SuppressWarnings("all")
public class ValidatorRepositoryBehaviourSessionMemberEntity extends ValidatorRepositoryBehaviour<SessionMemberEntity> {

    /**
     * @param object : the session member to validate
     * @return [List<String>] : list of error messages found
     */
    @Override
    public List<String> check(SessionMemberEntity object) {
        basedOn(Objects.isNull(object))
                .runTrue(accumulator::add, "Session member is NULL!");
        basedOn(Objects.isNull(object.getId()))
                .runTrue(accumulator::add, "Session member's id is NULL!");
        return accumulator;
    }
}
