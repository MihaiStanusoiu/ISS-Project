package validator;

import domain.EditionMemberEntity;

import java.util.List;
import java.util.Objects;

import static utils.Conditional.basedOn;

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
        basedOn(Objects.isNull(object))
                .runTrue(accumulator::add, "Edition member is NULL!");
        basedOn(Objects.isNull(object.getConfigurationEditionMember()))
                .runTrue(accumulator::add, "Edition's member configuration is NULL!");
        basedOn(Objects.isNull(object.getId()))
                .runTrue(accumulator::add, "Edition's member id is NULL!");
        basedOn(Objects.isNull(object.getIdEdition()))
                .runTrue(accumulator::add, "Edition's member edition id is NULL!");
        basedOn(Objects.isNull(object.getIdUser()))
                .runTrue(accumulator::add, "Edition's member user id is NULL!");
        basedOn(Objects.isNull(object.getReviewers()))
                .runTrue(accumulator::add, "Edition's member reviewers is NULL!");
        return accumulator;
    }
}
