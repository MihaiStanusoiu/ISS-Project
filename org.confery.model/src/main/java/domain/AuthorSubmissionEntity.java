
package domain;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Tested: True
 *
 * @author Teodorescu Vlad & Alexandru Stoica
 * @version 1.1
 */


@Entity
@Table(name = "AUTHOR_SUBMISSION")
public class AuthorSubmissionEntity implements Idable<Integer> {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID_AUTHOR_SUBMISSION")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "ID_USER")
    private UserEntity author;

    @ManyToOne
    @JoinColumn(name = "ID_SUBMISSION")
    private SubmissionEntity submission;

    @Column(name = "IS_OWNER")
    private Boolean isOwner;

    @Column(name = "PRESENTATION_URL")
    private String presentationUrl;

    private final static Integer DEFAULT_ID = 0;
    private final static Boolean DEFAULT_OWNER_FLAG = Boolean.FALSE;
    private final static String DEFAULT_PRESENTATION_URL = "";

    public AuthorSubmissionEntity() {
        this(DEFAULT_ID, DEFAULT_OWNER_FLAG, DEFAULT_PRESENTATION_URL);
    }

    /**
     * @param id The object's id
     */
    public AuthorSubmissionEntity(Integer id) {
        this(id, DEFAULT_OWNER_FLAG, DEFAULT_PRESENTATION_URL);
    }

    /**
     * @param isOwner Flag [true if the target author is the owner of the submission]
     */
    public AuthorSubmissionEntity(Boolean isOwner) {
        this(DEFAULT_ID, isOwner, DEFAULT_PRESENTATION_URL);
    }

    /**
     * @param presentationUrl The submission's presentation URL
     */
    public AuthorSubmissionEntity(String presentationUrl) {
        this(DEFAULT_ID, DEFAULT_OWNER_FLAG, DEFAULT_PRESENTATION_URL);
    }

    /**
     * @param presentationUrl The submission's presentation URL
     * @param isOwner         Flag [true if the target author is the owner of the submission]
     */
    public AuthorSubmissionEntity(Boolean isOwner, String presentationUrl) {
        this(DEFAULT_ID, isOwner, presentationUrl);
    }

    /**
     * @param id              The object's id
     * @param presentationUrl The submission's presentation URL
     * @param isOwner         Flag [true if the target author is the owner of the submission]
     */
    public AuthorSubmissionEntity(Integer id, Boolean isOwner, String presentationUrl) {
        this(id, isOwner, presentationUrl, null, null);
    }

    /**
     * @param submission The target submission
     * @param author     The target user
     */
    public AuthorSubmissionEntity(SubmissionEntity submission, UserEntity author) {
        this(DEFAULT_ID, DEFAULT_OWNER_FLAG, DEFAULT_PRESENTATION_URL, submission, author);
    }

    /**
     * @param submission The target submission
     * @param author     The target user
     * @param isOwner    Flag [true if the target author is the owner of the submission]
     */
    public AuthorSubmissionEntity(SubmissionEntity submission, UserEntity author, Boolean isOwner) {
        this(DEFAULT_ID, isOwner, "", submission, author);
    }

    /**
     * @param id              The object's id
     * @param presentationUrl The submission's presentation URL
     * @param isOwner         Flag [true if the target author is the owner of the submission]
     * @param submission      The target submission
     * @param author          The target user
     */
    public AuthorSubmissionEntity(Integer id,
                                  Boolean isOwner,
                                  String presentationUrl,
                                  SubmissionEntity submission,
                                  UserEntity author) {
        this.id = id;
        this.isOwner = isOwner;
        this.presentationUrl = presentationUrl;
        this.submission = submission;
        this.author = author;
    }

    /**
     * Effect: Getter for the primary key.
     *
     * @return Integer : returns id.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Effect: Sets the pkId to the given value
     *
     * @param id: new value for pkId
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Effect: Getter for the presentationUrl.
     *
     * @return String : returns presentationUrl.
     */
    public String getPresentationUrl() {
        return presentationUrl;
    }

    /**
     * Effect: Sets the presentationUrl to the given value.
     *
     * @param presentationUrl: new value for presentationUrl.
     */
    public void setPresentationUrl(String presentationUrl) {
        this.presentationUrl = presentationUrl;
    }

    /**
     * Effect: Getter for the author.
     *
     * @return UserEntity: returns author.
     */
    public UserEntity getAuthor() {
        return author;
    }

    /**
     * Effect: Sets the author to the given value.
     *
     * @param author: new value for author.
     */
    public void setAuthor(UserEntity author) {
        this.author = author;
    }

    /**
     * Effect: Getter for the submission.
     *
     * @return SubmissionEntity: returns submission.
     */
    public SubmissionEntity getSubmission() {
        return submission;
    }

    /**
     * Effect: Sets the submission to the given value.
     *
     * @param submission: new value for submission.
     */
    public void setSubmission(SubmissionEntity submission) {
        this.submission = submission;
    }

    /**
     * Effect: Getter for the isOwner.
     *
     * @return Boolean: returns isOwner.
     */
    public Boolean getOwner() {
        return isOwner;
    }

    /**
     * Effect: Sets the isOwner to the given value.
     *
     * @param owner: new value for isOwner.
     */
    public void setOwner(Boolean owner) {
        isOwner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorSubmissionEntity that = (AuthorSubmissionEntity) o;
        return id.equals(that.id) &&
                (isOwner != null ? isOwner.equals(that.isOwner) : that.isOwner == null) &&
                (presentationUrl != null ? presentationUrl.equals(that.presentationUrl)
                        : that.presentationUrl == null);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (isOwner != null ? isOwner.hashCode() : 0);
        result = 31 * result + (presentationUrl != null ? presentationUrl.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return this.getId() +
                this.getAuthor().toString() +
                this.getPresentationUrl() +
                this.getOwner();
    }
}
