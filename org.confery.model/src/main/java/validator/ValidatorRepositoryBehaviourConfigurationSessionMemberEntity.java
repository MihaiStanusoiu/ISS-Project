package validator;

import domain.ConfigurationSessionMemberEntity;

import java.util.List;
import java.util.Objects;

import static utils.Conditional.basedOn;

/**
 * Name:         Validator behaviour for SessionMember
 * Effect:       Validates a session member
 * Date:         06/05/2017
 * Tested:       False
 *
 * @author Teodorescu Vlad
 * @version 1.0
 */
@SuppressWarnings("all")
public class ValidatorRepositoryBehaviourConfigurationSessionMemberEntity
        extends ValidatorRepositoryBehaviour<ConfigurationSessionMemberEntity> {

    /**
     * @param object : the session member to validate
     * @return [List<String>] : list of error messages found
     */
    @Override
    public List<String> check(ConfigurationSessionMemberEntity object) {
        basedOn(Objects.isNull(object))
                .runTrue(accumulator::add, "Conference's configuration is NULL!");
        basedOn(Objects.isNull(object.getChair()))
                .runTrue(accumulator::add, "Conference configuration's  chair is NULL!");
        basedOn(Objects.isNull(object.getId()))
                .runTrue(accumulator::add, "Conference configuration's id is NULL!");
        basedOn(Objects.isNull(object.getListener()))
                .runTrue(accumulator::add, "Conference configuration's listener is NULL!");
        basedOn(Objects.isNull(object.getSpeaker()))
                .runTrue(accumulator::add, "Conference configuration's speaker is NULL!");
        return accumulator;
    }
}
