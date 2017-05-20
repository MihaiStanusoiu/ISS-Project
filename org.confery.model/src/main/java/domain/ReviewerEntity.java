
package domain;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Tested: True
 *
 * @author Tiron Andreea-Ecaterina & Alexandru Stoica
 * @version 1.1
 */

@Entity
@Table(name = "REVIEWER")
@SuppressWarnings("unused")
public class ReviewerEntity implements Idable<Integer> {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID_REVIEW")
    private Integer id;

    @Column(name = "RESPONSE")
    private String response;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "QUALIFIER")
    private String qualifier;

    @Column(name = "RECOMMENDATION_URL")
    private String recommendationUrl;

    @ManyToOne(targetEntity = SubmissionEntity.class)
    @JoinColumn(name = "ID_SUBMISSION")
    private SubmissionEntity submission;

    @ManyToOne(targetEntity = EditionMemberEntity.class)
    @JoinColumn(name = "ID_EDITION_MEMBER")
    private EditionMemberEntity member;

    private static final String DEFAULT_RESPONSE = ResponseEntityType.NOT_ASSIGNED.toString();
    private static final String DEFAULT_STATUS = StatusEntityType.NOT_REVIEWED.toString();
    private static final Integer DEFAULT_ID = 0;
    private static final String DEFAULT_RECOMMENDATION_URL = "";


    public ReviewerEntity() {
        this(DEFAULT_ID, DEFAULT_RESPONSE, DEFAULT_STATUS, null,
                DEFAULT_RECOMMENDATION_URL, null, null);
    }


    public ReviewerEntity(Integer id,
                          String response,
                          String status,
                          String qualifier,
                          String recommendationUrl,
                          SubmissionEntity submission,
                          EditionMemberEntity reviewer) {
        this.id = id;
        this.response = response;
        this.status = status;
        this.qualifier = qualifier;
        this.recommendationUrl = recommendationUrl;
        this.submission = submission;
        this.member = reviewer;
    }

    public ReviewerEntity(String response,
                          String status,
                          String qualifier,
                          String recommendationUrl,
                          SubmissionEntity submission,
                          EditionMemberEntity reviewer) {
        this(DEFAULT_ID, response, status, qualifier, recommendationUrl, submission, reviewer);
    }


    public ReviewerEntity(String response,
                          String status,
                          String qualifier,
                          String recommendationUrl) {
        this(DEFAULT_ID, response, status, qualifier, recommendationUrl, null, null);
    }


    public ReviewerEntity(SubmissionEntity submission, EditionMemberEntity reviewer) {
        this(DEFAULT_ID, DEFAULT_RESPONSE, DEFAULT_STATUS, "", "", null, null);
    }

    /**
     * Effect: Return the idReviewer of a reviewer.
     *
     * @return [Integer] : returns the idReviewer of a ReviewerEntity.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Effect: Sets the idReviewer of a reviewer.
     *
     * @param idReviewer: new value for reviewer idReviewer.
     */
    public void setId(Integer idReviewer) {
        this.id = idReviewer;
    }

    /**
     * Effect: Return the response of a reviewer.
     *
     * @return [String] : returns the response of a ReviewerEntity.
     */
    public String getResponse() {
        return response;
    }

    /**
     * Effect: Sets the response of a reviewer.
     *
     * @param response: new value for reviewer response.
     */
    public void setResponse(String response) {
        this.response = response;
    }

    /**
     * Effect: Return the status of a reviewer.
     *
     * @return [String] : returns the status of a ReviewerEntity.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Effect: Sets the status of a reviewer.
     *
     * @param status: new value for reviewer status.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Effect: Return the qualifier of a reviewer.
     *
     * @return [String] : returns the qualifier of a ReviewerEntity.
     */
    public String getQualifier() {
        return qualifier;
    }

    /**
     * Effect: Sets the qualifier of a reviewer.
     *
     * @param qualifier: new value for reviewer qualifier.
     */
    public void setQualifier(String qualifier) {
        this.qualifier = qualifier;
    }

    /**
     * Effect: Return the recommendation url of a reviewer.
     *
     * @return [String] : returns the recommendationUrl of a ReviewerEntity.
     */
    public String getRecommendationUrl() {
        return recommendationUrl;
    }

    /**
     * Effect: Sets the recommendation url of a reviewer.
     *
     * @param recommendationUrl: new value for reviewer recommendationUrl.
     */
    public void setRecommendationUrl(String recommendationUrl) {
        this.recommendationUrl = recommendationUrl;
    }

    /**
     * Effect: Returns the submission of the ReviewerEntity.
     *
     * @return [SubmissionEntity]: returns the submission of a ReviewerEntity.
     */
    public SubmissionEntity getSubmission() {
        return submission;
    }

    /**
     * Effect: Sets the submission of the ReviewerEntity.
     *
     * @param submission: new value for submission.
     */
    public void setSubmission(SubmissionEntity submission) {
        this.submission = submission;
    }

    /**
     * Effect: Returns the member of the ReviewerEntity.
     *
     * @return [EditionMemberEntity]: returns the id of the EditionMemberEntity.
     */
    public EditionMemberEntity getMember() {
        return member;
    }

    /**
     * Effect: Sets the member of the ReviewerEntity.
     *
     * @param member: new value for member
     */
    public void setMember(EditionMemberEntity member) {
        this.member = member;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReviewerEntity that = (ReviewerEntity) o;
        return id.equals(that.id) &&
                (response != null ? response.equals(that.response) : that.response == null) &&
                (status != null ? status.equals(that.status) : that.status == null) &&
                (qualifier != null ? qualifier.equals(that.qualifier) : that.qualifier == null) &&
                (recommendationUrl != null ? recommendationUrl.equals(that.recommendationUrl) :
                        that.recommendationUrl == null);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (response != null ? response.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (qualifier != null ? qualifier.hashCode() : 0);
        result = 31 * result + (recommendationUrl != null ? recommendationUrl.hashCode() : 0);
        return result;
    }
}
