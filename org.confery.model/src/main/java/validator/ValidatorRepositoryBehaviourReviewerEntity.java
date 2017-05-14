package validator;

import domain.ReviewerEntity;

import java.util.List;

/**
 * Name:         Validator behaviour for ReviewerEntity
 * Effect:       Validates a notification
 * Date:         06/05/2017
 * Tested:       False
 *
 * @author Teodorescu Vlad
 * @version 1.0
 */
@SuppressWarnings("all")
public class ValidatorRepositoryBehaviourReviewerEntity extends ValidatorRepositoryBehaviour<ReviewerEntity> {
    /**
     * @param object : the reviewer to validate
     * @return [List<String>] : list of error messages found
     */

    @Override
    public List<String> check(ReviewerEntity object) {
        if (object.getId() == null) {
            accumulator.add("Reviewer id is null.");
        }
        if(object.getQualifier() != null) {
            if (object.getQualifier().equals("")) {
                accumulator.add("Reviewer qualifier is empty.");
            }
        }
        else{
            accumulator.add("Reviewer qualifier is null.");
        }
        if(object.getRecommendationUrl() != null) {
            if (object.getRecommendationUrl().equals("")) {
                accumulator.add("Reviewer recommendation url is empty.");
            }
        }
        else
        {
            accumulator.add("Reviewer recommendation url is null.");
        }
        if(object.getResponse() != null) {
            if (object.getResponse().equals("")) {
                accumulator.add("Reviewer response is empty.");
            }
        }
        else{
            accumulator.add("Reviewer response is null.");
        }
        if(object.getStatus() != null) {
            if (object.getStatus().equals("")) {
                accumulator.add("Reviewer status is empty.");
            }
        }
        else
        {
            accumulator.add("Reviewer status is null.");
        }
        return accumulator;
    }
}
