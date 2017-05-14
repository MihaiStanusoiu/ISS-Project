package validator;

import domain.EditionMemberEntity;

import java.util.List;

/**
 * Name:         Validator behaviour for EditionMemberEntity
 * Effect:       Validates a edition member
 * Date:         06/05/2017
 * Tested:       False
 *
 * @author Teodorescu Vlad
 * @version 1.0
 */
@SuppressWarnings("all")
public class ValidatorRepositoryBehaviourEditionMemberEntity extends ValidatorRepositoryBehaviour<EditionMemberEntity> {

    /**
     * @param object : the edition member to validate
     * @return [List<String>] : list of error messages found
     */
    @Override
    public List<String> check(EditionMemberEntity object) {

        if (object.getConfigurationEditionMember() == null) {
            accumulator.add("SessionMember configuration edition member is null.");
        }
        if (object.getId() == null) {
            accumulator.add("SessionMember id is null.");
        }
        if (object.getIdEdition() == null) {
            accumulator.add("SessionMember edition id is null.");
        }
        if (object.getIdUser() == null) {
            accumulator.add("SessionMember user id is null.");
        }
        if (object.getReviewers() == null) {
            accumulator.add("SessionMember reviewers set is null.");
        }
        return accumulator;
    }
}
