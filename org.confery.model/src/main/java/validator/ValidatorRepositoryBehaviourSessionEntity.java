package validator;

import domain.SessionEntity;

import java.util.List;

/**
 * Name:         Validator behaviour for SessionEntity
 * Effect:       Validates a session
 * Date:         06/05/2017
 * Tested:       False
 *
 * @author Teodorescu Vlad
 * @version 1.0
 */
@SuppressWarnings("all")
public class ValidatorRepositoryBehaviourSessionEntity extends ValidatorRepositoryBehaviour<SessionEntity> {
    /**
     * @param object : the session to validate
     * @return [List<String>] : list of error messages found
     */

    @Override
    public List<String> check(SessionEntity object) {
        if (object.getId() == null) {
            accumulator.add("Session id is null.");
        }
        if(object.getBio() != null) {
            if (object.getBio().equals("")) {
                accumulator.add("Session bio is empty.");
            }
        }
        else
        {
            accumulator.add("Session bio is null.");
        }
        if (object.getEdition() == null) {
            accumulator.add("Session edition is null.");
        }
        if (object.getEndDate() == null) {
            accumulator.add("Session end date is null.");
        }
        if(object.getLocation() != null) {
            if (object.getLocation().equals("")) {
                accumulator.add("Session location is empty.");
            }
        }
        else
        {
            accumulator.add("Session location is null.");
        }
        if(object.getName() != null) {
            if (object.getName().equals("")) {
                accumulator.add("Session name is empty.");
            }
        }
        else
        {
            accumulator.add("Session name is null.");
        }
        if (object.getSeats() == null) {
            accumulator.add("Session seats is null.");
        }
        if (object.getSessionMembers() == null) {
            accumulator.add("Session sessionMembers is null.");
        }
        if (object.getStartDate() == null) {
            accumulator.add("Session start date is null.");
        }
        return accumulator;
    }
}
