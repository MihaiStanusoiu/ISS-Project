package validator;

import domain.UserEntity;

import java.util.List;

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
public class ValidatorRepositoryBehaviourUserEntity extends ValidatorRepositoryBehaviour<UserEntity> {

    public ValidatorRepositoryBehaviourUserEntity()
    {
        super();
    }

    /**
     * @param object : the user to validate
     * @return [List<String>] : list of error messages found
     */
    @Override
    public List<String> check(UserEntity object) {
        if(object.getName() != null) {
            if (object.getName().equals("")) {
                accumulator.add("User name is empty");
            }
        }
        else{
            accumulator.add("User name is null");
        }
        if(object.getBio() != null) {
            if (object.getBio().equals("")) {
                accumulator.add("User bio is empty");
            }
        }
        else
        {
            accumulator.add("User bio is null");
        }
        if(object.getEmail() != null) {
            if (object.getEmail().equals("")) {
                accumulator.add("User email is empty");
            }
        }
        else{
            accumulator.add("User email is null");
        }
        if (object.getId() == null)
        {
            accumulator.add("User id is null");
        }
        if(object.getLocation() != null) {
            if (object.getLocation().equals("")) {
                accumulator.add("User location is empty");
            }
        }
        else{
            accumulator.add("User location is null");
        }
        if(object.getPassword() != null) {
            if (object.getPassword().equals("")) {
                accumulator.add("User password is empty");
            }
        }
        else{
            accumulator.add("User password is null");
        }
        if(object.getUsername() != null) {
            if (object.getUsername().equals("")) {
                accumulator.add("User username is empty");
            }
        }
        else
        {
            accumulator.add("User username is null");
        }
        if(object.getWebsite() != null) {
            if (object.getWebsite().equals("")) {
                accumulator.add("User website is empty");
            }
        }
        else
        {
            accumulator.add("User website is null");
        }
        return accumulator;
    }
}
