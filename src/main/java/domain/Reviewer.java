package domain;

import java.util.ArrayList;

/**
 * Name:         Reviewer
 * Effect:         Class for Reviewer user
 * Date:           4/3/2017
 * Tested:        True
 *
 * @author {Stanusoiu Mihai-Teodor}
 * @version 1.0
 */
public class Reviewer extends PCMember {

    private ArrayList<Integer> reviews;

    public Reviewer(Integer id,
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
        this.reviews = new ArrayList<>();
    }

    public Reviewer(Integer id, Integer idConference, Integer idSection) {
        super(id, idConference, idSection);
        this.reviews = new ArrayList<>();
    }

    /**
     * Effect: Adds a submission in reviewer's list.
     * @param submission: The wanted submission.
     */
    public void addSubmission(Submission submission) {
        this.reviews.add(submission.getId());
    }

    /**
     * Effect: Returns UserType.REVIEWER
     * @return UserType : returns the corresponding user type.
     */
    @Override
    public UserType getType() {
        return UserType.REVIEWER;
    }

    /**
     * Effect: Gets the permissions assigned to the corresponding user type (UserType.REVIEWER)
     * @return ArrayList<Permission>: returns the array of permissions.
     */
    @Override
    public ArrayList<Permission> getPermissions() {
        return UserType.REVIEWER.getPermissions();
    }
}
