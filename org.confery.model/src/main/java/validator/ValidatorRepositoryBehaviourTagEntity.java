package validator;

import domain.TagEntity;

import java.util.List;
import java.util.Objects;

import static utils.Conditional.basedOn;

/**
 * Name:         Validator behaviour for TagEntity
 * Effect:       Validates a tag
 * Date:         06/05/2017
 * Tested:       False
 *
 * @author Teodorescu Vlad
 * @version 1.0
 */

public class ValidatorRepositoryBehaviourTagEntity extends ValidatorRepositoryBehaviour<TagEntity> {
    /**
     * @param object : the tag to validate
     * @return [List<String>] : list of error messages found
     */

    @Override
    public List<String> check(TagEntity object) {
        basedOn(Objects.isNull(object))
                .runTrue(accumulator::add, "Tag is NULL!");
        basedOn(Objects.isNull(object.getId()))
                .runTrue(accumulator::add, "Tag's id is NULL!");
        basedOn(Objects.isNull(object.getSubmissionTags()))
                .runTrue(accumulator::add, "Tag's submissions is NULL!");
        basedOn(Objects.isNull(object.getWord()) || object.getWord().equals(""))
                .runTrue(accumulator::add, "Tag's word is INVALID!");
        return accumulator;
    }
}
