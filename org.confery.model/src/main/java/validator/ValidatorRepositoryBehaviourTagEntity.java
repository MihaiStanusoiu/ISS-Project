package validator;

import domain.TagEntity;

import java.util.List;

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
        if (object.getId() == null) {
            accumulator.add("Tag id is null.");
        }
        if (object.getSubmissionTags() == null) {
            accumulator.add("Tag submissionTags is null.");
        }
        if(object.getWord() != null) {
            if (object.getWord().equals("")) {
                accumulator.add("Tag word is empty.");
            }
        }
        else{
            accumulator.add("Tag word is null.");
        }
        return accumulator;
    }
}
