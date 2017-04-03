package domain;

import java.util.ArrayList;

/**
 * Name:         CoChair
 * Effect:         Class for CoChair user
 * Date:           4/3/2017
 * Tested:        True
 *
 * @author {Stanusoiu Mihai-Teodor}
 * @version 1.0
 */
public class CoChair extends PCMember {

    //  Constructors

    public CoChair(
            Integer id, String username, String password, String email, String name, String website, String bio,
            String location, Integer idConference, Integer idSection
    ) {
        super(id, username, password, email, name, website, bio, location, idConference, idSection);
    }

    public CoChair(Integer id, Integer idConference, Integer idSection) {
        super(id, idConference, idSection);
    }


    //  Getters and Setters

    /**
     * Effect: Returns UserType.CO_CHAIR
     * @return UserType : returns the corresponding user type.
     */
    @Override
    public UserType getType() {
        return UserType.CO_CHAIR;
    }

    /**
     * Effect: Gets the permissions assigned to the corresponding user type (UserType.CO_CHAIR)
     * @return ArrayList<Permission>: returns the array of permissions.
     */
    @Override
    public ArrayList<Permission> getPermissions() {
        return UserType.CO_CHAIR.getPermissions();
    }
}
