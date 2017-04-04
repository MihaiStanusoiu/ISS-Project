package domain;

import java.util.ArrayList;

/**
 * Name:         UserTypeInterface
 * Effect:         Interface for the logical structure of a user type
 * Date:           4/2/2017
 * Tested:        True
 *
 * @author {Stanusoiu Mihai-Teodor}
 * @version 1.0
 */

public interface UserTypeInterface {

    /**
     * Effect: Gets the permissions assigned to the corresponding user type
     * @return ArrayList<Permission>: returns the array of permissions.
     */
    ArrayList<Permission> getPermissions();

}
