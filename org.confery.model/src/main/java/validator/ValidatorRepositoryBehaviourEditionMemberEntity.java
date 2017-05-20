
package validator;

import domain.EditionMemberEntity;

import java.util.List;
import java.util.Objects;

import static utils.Conditional.basedOn;

/**
 * Tested: True
 *
 * @author Teodorescu Vlad
 * @version 1.0
 */

public class ValidatorRepositoryBehaviourEditionMemberEntity
        extends ValidatorRepositoryBehaviour<EditionMemberEntity> {

    /**
     * @param object : the edition member to validate
     * @return [List<String>] : list of error messages found
     */
    @Override
    public List<String> check(EditionMemberEntity object) {
        basedOn(Objects.isNull(object))
                .runTrue(accumulator::add, "Edition member is NULL!");
        basedOn(Objects.isNull(object.getId()))
                .runTrue(accumulator::add, "Edition's member id is NULL!");
        return accumulator;
    }

}
