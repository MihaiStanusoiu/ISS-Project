package domain;

import java.util.ArrayList;

/**
 * Name:         Author
 * Effect:       Class for author type
 * Date:         4/2/2017
 * Tested:       True
 *
 * @author      {Stanusoiu Mihai-Teodor}
 * @version     1.0
 */
public class Author extends SectionMember {

    private Boolean isOwner;
    private Integer idSubmission;

    public Author(Integer id,
                  String username,
                  String password,
                  String email,
                  String name,
                  String website,
                  String bio,
                  String location,
                  Integer idConference,
                  Integer idSection,
                  Integer idSubmission) {
        super(id, username, password, email, name, website, bio, location, idConference, idSection);
        this.idSubmission = idSubmission;
    }

    public Author(Integer id,
                  String username,
                  String password,
                  String email,
                  String name,
                  String website,
                  String bio,
                  String location,
                  Integer idConference,
                  Integer idSection,
                  Boolean isOwner,
                  Integer idSubmission) {
        super(id, username, password, email, name, website, bio, location, idConference, idSection);
        this.isOwner = isOwner;
        this.idSubmission = idSubmission;
    }

    public Author(Integer id, Integer idConference, Integer idSection, Integer idSubmission) {
        super(id, idConference, idSection);
        this.idSubmission = idSubmission;
    }

    public Author(Integer id, Integer idConference, Integer idSection, Integer idSubmission, Boolean isOwner) {
        super(id, idConference, idSection);
        this.isOwner = isOwner;
        this.idSubmission = idSubmission;
    }

    /**
     * Effect: Check if current author is owner of the paper
     * @return Boolean : returns isOwner.
     */
    public Boolean isOwner() {
        return isOwner;
    }

    /**
     * Effect: Sets the owner boolean value to the given value
     * @param isOwner: new value for isOwner
     */
    public void setOwner(Boolean isOwner) {

        this.isOwner = isOwner;
    }

    /**
     * Effect: Getter for the id of the submission.
     * @return Integer : returns idSubmission.
     */
    public Integer getIdSubmission() {
        return idSubmission;
    }

    /**
     * Effect: Sets the idSubmission to the given value
     * @param idSubmission: new value for idSubmission
     */
    public void setIdSubmission(Integer idSubmission) {
        this.idSubmission = idSubmission;
    }

    /**
     * Effect: Returns UserType.AUTHOR
     * @return UserType : returns the corresponding user type.
     */
    @Override
    public UserType getType() {
        return UserType.AUTHOR;
    }

    /**
     * Effect: Gets the permissions assigned to the corresponding user type (UserType.AUTHOR)
     * @return ArrayList<Permission>: returns the array of permissions.
     */
    @Override
    public ArrayList<Permission> getPermissions() {
        return UserType.AUTHOR.getPermissions();
    }
}
