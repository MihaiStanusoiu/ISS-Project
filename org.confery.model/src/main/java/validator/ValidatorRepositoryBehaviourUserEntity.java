package validator;

import domain.UserEntity;

import java.util.List;
import java.util.Objects;

import static utils.Conditional.basedOn;

/**
 * Name:         Validator behaviour for UserEntity
 * Effect:       Validates a user
 * Date:         06/05/2017
 * Tested:       False
 *
 * @author Teodorescu Vlad
 * @version 1.0
 */

@SuppressWarnings("all")
public class ValidatorRepositoryBehaviourUserEntity
        extends ValidatorRepositoryBehaviour<UserEntity> {

    /**
     * @param object : the user to validate
     * @return [List<String>] : list of error messages found
     */
    @Override
    public List<String> check(UserEntity object) {
        basedOn(Objects.isNull(object))
                .runTrue(accumulator::add, "User is NULL!");
        basedOn(Objects.isNull(object.getPassword()) || object.getPassword().equals(""))
                .runTrue(accumulator::add, "User's password is invalid!");
        basedOn(Objects.isNull(object.getUsername()) || object.getUsername().equals(""))
                .runTrue(accumulator::add, "User's username is invalid!");
        basedOn(Objects.isNull(object.getName()))
                .runTrue(accumulator::add, "User's name is NULL!");
        basedOn(Objects.isNull(object.getBio()))
                .runTrue(accumulator::add, "User's bio is NULL!");
        basedOn(Objects.isNull(object.getEmail()))
                .runTrue(accumulator::add, "User's email is NULL!");
        basedOn(Objects.isNull(object.getLocation()))
                .runTrue(accumulator::add, "User's location is NULL!");
        basedOn(Objects.isNull(object.getWebsite()))
                .runTrue(accumulator::add, "User's website is NULL!");
        return accumulator;
    }
}
