package validator;

import domain.ConfigurationSessionMemberEntity;

import java.util.List;

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
public class ValidatorRepositoryBehaviourConfigurationSessionMemberEntity extends ValidatorRepositoryBehaviour<ConfigurationSessionMemberEntity> {
    /**
     * @param object : the session member to validate
     * @return [List<String>] : list of error messages found
     */


    @Override
    public List<String> check(ConfigurationSessionMemberEntity object) {
        if (object.getChair() == null) {
            accumulator.add("SessionMember chair is not set.");
        }
        if (object.getId() == null) {
            accumulator.add("SessionMember id is null.");
        }
        if (object.getListener() == null) {
            accumulator.add("SessionMember listener is not set.");
        }
        if (object.getSectionMembers() == null) {
            accumulator.add("SessionMember section member is null.");
        }
        if (object.getSpeaker() == null) {
            accumulator.add("SessionMember speaker is not set.");
        }
        return accumulator;
    }
}
