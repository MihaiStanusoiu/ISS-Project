package validator;

import domain.AuthorSubmissionEntity;

import java.util.List;
import java.util.Objects;

import static utils.Conditional.basedOn;

/**
 * Tested: True
 *
 * @author Teodorescu Vlad
 * @version 1.0
 */

public class ValidatorRepositoryBehaviourAuthorSubmissionEntity
        extends ValidatorRepositoryBehaviour<AuthorSubmissionEntity> {

    /**
     * @param object : the author submission to validate
     * @return [List<String>] : list of error messages found
     */
    @Override
    public List<String> check(AuthorSubmissionEntity object) {
        basedOn(Objects.isNull(object))
                .runTrue(accumulator::add, "Author is NULL!");
        basedOn(Objects.isNull(object.getId()))
                .runTrue(accumulator::add, "Author's ID is NULL!");
        basedOn(Objects.isNull(object.getOwner()))
                .runTrue(accumulator::add, "Author's owner flag is NULL!");
        basedOn(Objects.isNull(object.getPresentationUrl()))
                .runTrue(accumulator::add, "Author's presentation is NULL!");
        return accumulator;
    }
}
