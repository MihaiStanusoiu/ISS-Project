package domain;

import java.util.ArrayList;

/**
 * Name:        ConferenceMember
 * Effect:      Class for ConferenceMember type
 * Date:        4/2/2017
 * Tested:      True
 *
 * @author      {Stanusoiu Mihai-Teodor}
 * @version     1.0
 */
public class ConferenceMember extends User {

    protected Integer idConference;

    public ConferenceMember(Integer id,
                            String username,
                            String password,
                            String email,
                            String name,
                            String website,
                            String bio,
                            String location,
                            Integer idConference) {
        super(id, username, password, email, name, website, bio, location);
        this.idConference = idConference;
    }

    public ConferenceMember(Integer id,
                            String username,
                            String password,
                            String email,
                            String name,
                            String website,
                            String bio,
                            String location) {
        super(id, username, password, email, name, website, bio, location);
    }

    public ConferenceMember(Integer id, Integer idConference) {
        this.id = id;
        this.idConference = idConference;
    }

    /**
     * Effect: Getter for the id of the conference.
     * @return Integer : returns idConference.
     */
    public Integer getIdConference() {
        return idConference;
    }

    /**
     * Effect: Sets the idConference to the given value
     * @param idConference: new value for idSubmission
     */
    public void setIdConference(Integer idConference) {
        this.idConference = idConference;
    }

    /**
     * Effect: Gets the permissions assigned to the corresponding user type (UserType.CONFERENCE_MEMBER)
     * @return ArrayList<Permission>: returns the array of permissions.
     */
    @Override
    public ArrayList<Permission> getPermissions() {
        return UserType.CONFERENCE_MEMBER.getPermissions();
    }

    /**
     * Effect: Returns UserType.CONFERENCE_MEMBER
     * @return UserType : returns the corresponding user type.
     */
    @Override
    public UserType getType() {
        return UserType.CONFERENCE_MEMBER;
    }
}
