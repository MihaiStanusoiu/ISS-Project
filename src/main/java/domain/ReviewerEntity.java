package domain;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Name:    ReviewerEntity
 * Effect:  Class for the database table ReviewerEntity
 * Date:    12/5/2017
 * Tested:  True
 * @author  Tiron Andreea-Ecaterina
 * @version 1.0
 */

@Entity
@Table(name = "REVIEWER")
@SuppressWarnings("unused")
public class ReviewerEntity implements Idable<Integer>{
    @Id @GeneratedValue(strategy = IDENTITY)
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

    @ManyToOne
    @JoinColumn(name = "ID_SUBMISSION")
    private SubmissionEntity submissionReview;

    @ManyToOne
    @JoinColumn(name = "ID_EDITION_MEMBER")
    private EditionMemberEntity idEditionMember;

    /**
     * Empty constructor.
     */
    public ReviewerEntity(){}

    public ReviewerEntity(String response, String status, String qualifier, String recommendationUrl) {
        this.response = response;
        this.status = status;
        this.qualifier = qualifier;
        this.recommendationUrl = recommendationUrl;
    }

    /**
     * Effect: Return the idReviewer of a reviewer.
     * @return [Integer] : returns the idReviewer of a ReviewerEntity.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Effect: Sets the idReviewer of a reviewer.
     * @param idReviewer: new value for reviewer idReviewer.
     */
    public void setId(Integer idReviewer) {
        this.id = idReviewer;
    }

    /**
     * Effect: Return the response of a reviewer.
     * @return [String] : returns the response of a ReviewerEntity.
     */
    public String getResponse() {
        return response;
    }

    /**
     * Effect: Sets the response of a reviewer.
     * @param response: new value for reviewer response.
     */
    public void setResponse(String response) {
        this.response = response;
    }

    /**
     * Effect: Return the status of a reviewer.
     * @return [String] : returns the status of a ReviewerEntity.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Effect: Sets the status of a reviewer.
     * @param status: new value for reviewer status.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Effect: Return the qualifier of a reviewer.
     * @return [String] : returns the qualifier of a ReviewerEntity.
     */
    public String getQualifier() {
        return qualifier;
    }

    /**
     * Effect: Sets the qualifier of a reviewer.
     * @param qualifier: new value for reviewer qualifier.
     */
    public void setQualifier(String qualifier) {
        this.qualifier = qualifier;
    }

    /**
     * Effect: Return the recommendation url of a reviewer.
     * @return [String] : returns the recommendationUrl of a ReviewerEntity.
     */
    public String getRecommendationUrl() {
        return recommendationUrl;
    }

    /**
     * Effect: Sets the recommendation url of a reviewer.
     * @param recommendationUrl: new value for reviewer recommendationUrl.
     */
    public void setRecommendationUrl(String recommendationUrl) {
        this.recommendationUrl = recommendationUrl;
    }

    /**
     * Effect: Returns the submission of the ReviewerEntity.
     * @return [SubmissionEntity]: returns the submissionReview of a ReviewerEntity.
     */
    public SubmissionEntity getSubmission() {
        return submissionReview;
    }

    /**
     * Effect: Sets the submission of the ReviewerEntity.
     * @param submission: new value for submission.
     */
    public void setSubmission(SubmissionEntity submission) {
        this.submissionReview = submission;
    }

    /**
     * Effect: Returns the idEditionMember of the ReviewerEntity.
     * @return [EditionMemberEntity]: returns the id of the EditionMemberEntity.
     */
    public EditionMemberEntity getIdEditionMember() {
        return idEditionMember;
    }

    /**
     * Effect: Sets the idEditionMember of the ReviewerEntity.
     * @param idEditionMember: new value for idEditionMember
     */
    public void setIdEditionMember(EditionMemberEntity idEditionMember) {
        this.idEditionMember = idEditionMember;
    }

}
