package domain;

import java.util.ArrayList;

/**
 * Name:         Speaker
 * Effect:         Class for Speaker user
 * Date:           4/2/2017
 * Tested:        True
 *
 * @author {Stanusoiu Mihai-Teodor}
 * @version 1.0
 */
public class Speaker extends Author {

    //  Fields

    private String presentationURL;


    //  Constructors

    public Speaker(
            Integer id, String username, String password, String email, String name, String website, String bio,
            String location, Integer idConference, Integer idSection, Boolean isOwner, Integer idSubmission,
            String presentationURL
    ) {
        super(id, username, password, email, name, website, bio, location, idConference, idSection, isOwner, idSubmission);
        this.presentationURL = presentationURL;
    }

    public Speaker(
            Integer id, Integer idConference, Integer idSection, Integer idSubmission, Boolean isOwner,
            String presentationURL
    ) {
        super(id, idConference, idSection, idSubmission, isOwner);
        this.presentationURL = presentationURL;
    }


    //  Getters and Setters

    /**
     * Effect: Getter for the url of the presentation.
     * @return String : returns presentationURL.
     */
    public String getPresentationURL() {
        return presentationURL;
    }

    /**
     * Effect: Sets the url of the presentation to the given value
     * @param presentationURL: [String] new value for idSubmission
     */
    public void setPresentationURL(String presentationURL) {
        this.presentationURL = presentationURL;
    }

    /**
     * Effect: Returns UserType.SPEAKER
     * @return UserType : returns the corresponding user type.
     */
    @Override
    public UserType getType() {
        return UserType.SPEAKER;
    }

    /**
     * Effect: Gets the permissions assigned to the corresponding user type (UserType.SPEAKER)
     * @return ArrayList<Permission>: returns the array of permissions.
     */
    @Override
    public ArrayList<Permission> getPermissions() {
        return UserType.SPEAKER.getPermissions();
    }
}