package validator;

import domain.ConfigurationEditionMemberEntity;

import java.util.List;

/**
 * Name:         Validator behaviour for ConferenceEditionMemberEntity
 * Effect:       Validates a conference edition member
 * Date:         06/05/2017
 * Tested:       False
 *
 * @author Teodorescu Vlad
 * @version 1.0
 */
@SuppressWarnings("all")
public class ValidatorRepositoryBehaviourConfigurationEditionMemberEntity extends ValidatorRepositoryBehaviour<ConfigurationEditionMemberEntity> {
    /**
     * @param object : the configurationEditionMember to validate
     * @return [List<String>] : list of error messages found
     */

    @Override
    public List<String> check(ConfigurationEditionMemberEntity object) {
        if (object.getChair() == null) {
            accumulator.add("ConferenceEditionMemeber chair is not set.");
        }
        if (object.getCoChair() == null) {
            accumulator.add("ConferenceEditionMemeber coChair is not set.");
        }
        if (object.getEditionMembers() == null) {
            accumulator.add("ConferenceEditionMemeber edition memebers is null.");
        }
        if (object.getId() == null) {
            accumulator.add("ConferenceEditionMemeber id is null.");
        }
        if (object.getPCMember() == null) {
            accumulator.add("ConferenceEditionMemeber pcMember is not set.");
        }
        return accumulator;
    }
}
