package domain;

import java.util.ArrayList;

/**
 * Name:         PCMember
 * Effect:         Class for PCMember user
 * Date:           4/3/2017
 * Tested:        True
 *
 * @author {Stanusoiu Mihai-Teodor}
 * @version 1.0
 */

public class PCMember extends SectionMember {

    public PCMember(Integer id,
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

    public PCMember(Integer id, Integer idConference, Integer idSection) {
        super(id, idConference, idSection);
    }

    /**
     * Effect: Returns UserType.PC_MEMBER
     * @return UserType : returns the corresponding user type.
     */
    @Override
    public UserType getType() {
        return UserType.PC_MEMBER;
    }

    /**
     * Effect: Gets the permissions assigned to the corresponding user type (UserType.PC_MEMBER)
     * @return ArrayList<Permission>: returns the array of permissions.
     */
    @Override
    public ArrayList<Permission> getPermissions() {
        return UserType.PC_MEMBER.getPermissions();
    }
}
