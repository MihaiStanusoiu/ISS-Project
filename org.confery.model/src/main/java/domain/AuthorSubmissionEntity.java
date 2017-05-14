package domain;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Name:         AuthorSubmissionEntity
 * Effect:       Class for database domain AuthorSubmissionEntity table
 * Date:         4/8/2017
 * Tested:       True
 * @author      {Teodorescu Vlad}
 * @version     1.0
 */

@Entity
@Table(name = "AUTHOR_SUBMISSION")
@SuppressWarnings("unused")
public class AuthorSubmissionEntity implements Idable<Integer>{

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID_AUTHOR_SUBMISSION")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "ID_USER")
    private UserEntity userSubmission;

    @ManyToOne
    @JoinColumn(name = "ID_SUBMISSION")
    private SubmissionEntity submissionAuthor;

    @Column(name = "IS_OWNER")
    private Boolean isOwner;

    @Column(name = "PRESENTATION_URL")
    private String presentationUrl;

    public AuthorSubmissionEntity() { }

    public AuthorSubmissionEntity(Boolean isOwner, String presentationUrl) {
        this.isOwner = isOwner;
        this.presentationUrl = presentationUrl;
    }

    public AuthorSubmissionEntity(Integer id, Boolean isOwner, String presentationUrl) {
        this.id = id;
        this.userSubmission = new UserEntity("a","b","c","d","e","f","g");
        this.submissionAuthor = new SubmissionEntity("x","y","z","w");
        this.isOwner = isOwner;
        this.presentationUrl = presentationUrl;
    }

    /**
     * Effect: Getter for the primary key.
     * @return Integer : returns id.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Effect: Sets the pkId to the given value
     * @param id: new value for pkId
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Effect: Getter for the presentationUrl.
     * @return String : returns presentationUrl.
     */
    public String getPresentationUrl() {
        return presentationUrl;
    }

    /**
     * Effect: Sets the presentationUrl to the given value.
     * @param presentationUrl: new value for presentationUrl.
     */
    public void setPresentationUrl(String presentationUrl) {
        this.presentationUrl = presentationUrl;
    }

    /**
     * Effect: Getter for the userSubmission.
     * @return UserEntity: returns userSubmission.
     */
    public UserEntity getUserSubmission() {
        return userSubmission;
    }

    /**
     * Effect: Sets the userSubmission to the given value.
     * @param userSubmission: new value for userSubmission.
     */
    public void setUserSubmission(UserEntity userSubmission) {
        this.userSubmission = userSubmission;
    }

    /**
     * Effect: Getter for the submissionAuthor.
     * @return SubmissionEntity: returns submissionAuthor.
     */
    public SubmissionEntity getSubmissionAuthor() {
        return submissionAuthor;
    }

    /**
     * Effect: Sets the submissionAuthor to the given value.
     * @param submissionAuthor: new value for submissionAuthor.
     */
    public void setSubmissionAuthor(SubmissionEntity submissionAuthor) {
        this.submissionAuthor = submissionAuthor;
    }

    /**
     * Effect: Getter for the isOwner.
     * @return Boolean: returns isOwner.
     */
    public Boolean getOwner() {
        return isOwner;
    }

    /**
     * Effect: Sets the isOwner to the given value.
     * @param owner: new value for isOwner.
     */
    public void setOwner(Boolean owner) {
        isOwner = owner;
    }
}
