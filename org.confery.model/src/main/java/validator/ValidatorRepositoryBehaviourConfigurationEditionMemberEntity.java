package validator;

import domain.ConfigurationEditionMemberEntity;

import java.util.List;
import java.util.Objects;

import static utils.Conditional.basedOn;

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
public class ValidatorRepositoryBehaviourConfigurationEditionMemberEntity
        extends ValidatorRepositoryBehaviour<ConfigurationEditionMemberEntity> {

    /**
     * @param object : the configurationEditionMember to validate
     * @return [List<String>] : list of error messages found
     */
    @Override
    public List<String> check(ConfigurationEditionMemberEntity object) {
        basedOn(Objects.isNull(object))
                .runTrue(accumulator::add, "Edition's configuration is NULL!");
        basedOn(Objects.isNull(object.getChair()))
                .runTrue(accumulator::add, "Edition configuration's chair is NULL!");
        basedOn(Objects.isNull(object.getCoChair()))
                .runTrue(accumulator::add, "Edition configuration's co-chair is NULL!");
        basedOn(Objects.isNull(object.getPCMember()))
                .runTrue(accumulator::add, "Edition configuration's pc-member is NULL!");
        basedOn(Objects.isNull(object.getId()))
                .runTrue(accumulator::add, "Edition configuration's id is NULL!");
        return accumulator;
    }
}
