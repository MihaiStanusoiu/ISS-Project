package validator;

import domain.SessionMemberEntity;

import java.util.List;

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

        if (object.getId() == null) {
            accumulator.add("Session member id is null.");
        }
        if (object.getIdConfigurationSession() == null) {
            accumulator.add("Session member configuration is null.");
        }
        if (object.getSession() == null) {
            accumulator.add("Session member session is null.");
        }
        if (object.getUser() == null) {
            accumulator.add("Session member user is null.");
        }
        return accumulator;
    }
}
