package validator;

import domain.EditionEntity;

import java.util.List;

/**
 * Name:         Validator behaviour for EditionEntity
 * Effect:       Validates an edition
 * Date:         06/05/2017
 * Tested:       False
 *
 * @author Tanasie Luiza Maria/Teodorescu Vlad
 * @version 1.0
 */
@SuppressWarnings("all")
public class ValidatorRepositoryBehaviourEditionEntity extends ValidatorRepositoryBehaviour<EditionEntity> {

    /**
     * @param object : the edition to validate
     * @return [List<String>] : list of error messages found
     */
    @Override
    public List<String> check(EditionEntity object) {
        if(object.getBio() != null) {
            if (object.getBio().equals("")) {
                accumulator.add("Edition bio is empty");
            }
        }
        else{
            accumulator.add("Edition bio is null");
        }
        if (object.getAbstractDeadline() == null) {
            accumulator.add("Edition abstract deadline is null");
        }
        if (object.getBiddingDeadline() == null) {
            accumulator.add("Edition bidding deadline is null");
        }
        if (object.getConference() == null) {
            accumulator.add("Edition conference is null");
        }
        if (object.getEndDate() == null) {
            accumulator.add("Edition end date is null");
        }
        if (object.getEvaluationDeadline() == null) {
            accumulator.add("Edition evaluation deadline is null");
        }
        if (object.getId() == null) {
            accumulator.add("Edition id is null");
        }
        if(object.getLocation() != null) {
            if (object.getLocation().equals("")) {
                accumulator.add("Edition location is empty");
            }
        }
        else{
            accumulator.add("Edition location is null");
        }
        if (object.getPaperDeadline() == null) {
            accumulator.add("Edition location is null");
        }
        if (object.getSessions() == null) {
            accumulator.add("Edition sessions is null");
        }
        if (object.getStartDate() == null) {
            accumulator.add("Edition start date is null");
        }
        return accumulator;
    }
}
