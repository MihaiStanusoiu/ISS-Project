package domain;

import java.util.ArrayList;

/**
 * Name:         SessionChair
 * Effect:         Class for SessionChair user
 * Date:           4/2/2017
 * Tested:        True
 *
 * @author {Stanusoiu Mihai-Teodor}
 * @version 1.0
 */

public class SessionChair extends SessionMember {

    public SessionChair(Integer id,
                        String username,
                        String password,
                        String email,
                        String name,
                        String website,
                        String bio,
                        String location,
                        Integer idConference,
                        Integer idSection) {
        super(id, username, password, email, name, website, bio, location, idConference, idSection);
    }

    public SessionChair(Integer id,
                        Integer idConference,
                        Integer idSection) {
        super(id, idConference, idSection);
    }

    /**
     * Effect: Returns UserType.SECTION_CHAIR
     * @return UserType : returns the corresponding user type.
     */
    @Override
    public UserType getType() {
        return UserType.SECTION_CHAIR;
    }

    /**
     * Effect: Gets the permissions assigned to the corresponding user type (UserType.SECTION_CHAIR)
     * @return ArrayList<Permission>: returns the array of permissions.
     */
    @Override
    public ArrayList<Permission> getPermissions() {
        return UserType.SECTION_CHAIR.getPermissions();
    }
}
