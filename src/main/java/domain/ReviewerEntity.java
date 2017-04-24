package domain;

import javax.persistence.*;

/**
 * Name:    ReviewerEntity
 * Effect:  Class for the database table ReviewerEntity
 * Date:    9/4/2017
 * Tested:  False
 *
 * @author Simion George-Vlad
 * @version 1.0
 */

@Entity
@Table(name = "REVIEWER")
@SuppressWarnings("unused")
public class ReviewerEntity  {

    @EmbeddedId
    private ReviewerId idReviewer;

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
    private SubmissionEntity submission;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "id", insertable = false, updatable = false),
            @JoinColumn(name = "idEditionMember", insertable = false, updatable = false),
            @JoinColumn(name = "submission", insertable = false, updatable = false)
    })
    private EditionMemberEntity idEditionMember;

    /**
     * Empty constructor.
     */
    public ReviewerEntity(){}

    /**
     * Effect: Return the idReviewer of a reviewer.
     * @return [ReviewerId] : returns the idReviewer of a ReviewerEntity.
     */
    public ReviewerId getIdReviewer() {
        return idReviewer;
    }

    /**
     * Effect: Sets the idReviewer of a reviewer.
     * @param idReviewer: new value for reviewer idReviewer.
     */
    public void setIdReviewer(ReviewerId idReviewer) {
        this.idReviewer = idReviewer;
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
}
