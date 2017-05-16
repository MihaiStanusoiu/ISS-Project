package validator;

import domain.ConferenceEntity;

import java.util.List;
import java.util.Objects;

import static utils.Conditional.basedOn;

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
public class ValidatorRepositoryBehaviourConferenceEntity
        extends ValidatorRepositoryBehaviour<ConferenceEntity> {

    /**
     * @param object : the conference to validate
     * @return [List<String>] : list of error messages found
     */
    @Override
    public List<String> check(ConferenceEntity object) {
        basedOn(Objects.isNull(object))
                .runTrue(accumulator::add, "Conference is NULL!");
        basedOn(Objects.isNull(object.getAcronym()))
                .runTrue(accumulator::add, "Conference's acronym is NULL!");
        basedOn(Objects.isNull(object.getId()))
                .runTrue(accumulator::add, "Conference's id is NULL!");
        basedOn(Objects.isNull(object.getName()))
                .runTrue(accumulator::add, "Conference's name is NULL!");
        basedOn(object.getName().equals(""))
                .runTrue(accumulator::add, "Conference's name is empty!");
        return accumulator;
    }
}
