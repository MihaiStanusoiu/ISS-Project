package validator;

import domain.ConferenceEntity;

import java.util.List;

/**
 * Name:         Validator behaviour for ConferenceEntity
 * Effect:       Validates a conference
 * Date:         06/05/2017
 * Tested:       False
 *
 * @author Teodorescu Vlad
 * @version 1.0
 */
@SuppressWarnings("all")
public class ValidatorRepositoryBehaviourConferenceEntity extends ValidatorRepositoryBehaviour<ConferenceEntity> {

    /**
     * @param object : the conference to validate
     * @return [List<String>] : list of error messages found
     */
    @Override
    public List<String> check(ConferenceEntity object) {

        if(object.getAcronym() != null) {
            if (object.getAcronym().equals("")) {
                accumulator.add("Conference acronym is empty.");
            }
        }
        else{
            accumulator.add("Conference acronym is null.");
        }
        if (object.getEditions() == null) {
            accumulator.add("Conference editions is null.");
        }
        if (object.getId() == null) {
            accumulator.add("Conference id is null.");
        }
        if(object.getName() != null) {
            if (object.getName().equals("")) {
                accumulator.add("Conference name is empty.");
            }
        }
        else{
            accumulator.add("Conference name is null.");
        }
        return accumulator;
    }
}
