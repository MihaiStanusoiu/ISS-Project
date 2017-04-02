package domain;

import java.util.ArrayList;

/**
 * Name:         Listener
 * Effect:         Class for Listener type
 * Date:           4/2/2017
 * Tested:        True
 *
 * @author {Stanusoiu Mihai-Teodor}
 * @version 1.0
 */
public class Listener extends SectionMember {

    //  Constructors

    public Listener(
            Integer id, String username, String password, String email, String name, String website, String bio,
            String location, Integer idConference, Integer idSection
    ) {
        super(id, username, password, email, name, website, bio, location, idConference, idSection);
    }

    public Listener(Integer id, Integer idConference, Integer idSection) {
        super(id, idConference, idSection);
    }


    //  Getters and Setters

    /**
     * Effect: Returns UserType.LISTENER
     * @return UserType : returns the corresponding user type.
     */
    @Override
    public UserType getType() {
        return UserType.LISTENER;
    }

    /**
     * Effect: Gets the permissions assigned to the corresponding user type (UserType.LISTENER)
     * @return ArrayList<Permission>: returns the array of permissions.
     */
    @Override
    public ArrayList<Permission> getPermissions() {
        return UserType.LISTENER.getPermissions();
    }
}
