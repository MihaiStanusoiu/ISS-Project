package validator;

import domain.AuthorSubmissionEntity;

import java.util.List;

/**
 * Name:         Validator behaviour for AuthorSubmissionEntity
 * Effect:       Validates an author submission
 * Date:         06/05/2017
 * Tested:       Tested
 *
 * @author Teodorescu Vlad
 * @version 1.0
 */
@SuppressWarnings("all")
public class ValidatorRepositoryBehaviourAuthorSubmissionEntity extends ValidatorRepositoryBehaviour<AuthorSubmissionEntity> {

    /**
     * @param object : the author submission to validate
     * @return [List<String>] : list of error messages found
     */
    @Override
    public List<String> check(AuthorSubmissionEntity object) {
        if (object.getId() == null) {
            accumulator.add("AuthorSubmission id is null.");
        }
        if (object.getOwner() == null) {
            accumulator.add("AuthorSubmission owner is null.");
        }
        if(object.getPresentationUrl() != null) {
            if (object.getPresentationUrl().equals("")) {
                accumulator.add("AuthorSubmission presentation url is empty.");
            }
        }
        else {
            accumulator.add("AuthorSubmission presentation url is null.");
        }
        if (object.getSubmissionAuthor() == null) {
            accumulator.add("AuthorSubmission submissionAuthor is empty.");
        }
        if (object.getUserSubmission() == null) {
            accumulator.add("AuthorSubmission userSubmission is empty.");
        }
         return accumulator;
    }
}
