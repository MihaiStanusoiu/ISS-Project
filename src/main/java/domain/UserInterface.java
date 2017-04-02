package domain;

/**
 * Name:         UserInterface
 * Effect:         Inferface for user type
 * Date:           4/2/2017
 * Tested:        True
 *
 * @author {Stanusoiu Mihai-Teodor}
 * @version 1.0
 */
public interface UserInterface {

    /**
     * Effect: Returns the corresponding user type
     * @return UserType : returns the corresponding user type.
     */
    UserType getType();
}
