package domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

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
    private ReviewerId id;

    @Column(name = "RESPONSE")
    private String response;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "QUALIFIER")
    private String qualifier;

    @Column(name = "RECOMMENDATION_URL")
    private String recommendationUrl;

    /**
     * Empty constructor.
     */
    public ReviewerEntity(){}

    /**
     * Effect: Return the id of a reviewer.
     * @return [ReviewerId] : returns the id of a ReviewerEntity.
     */
    public ReviewerId getId() {
        return id;
    }

    /**
     * Effect: Sets the id of a reviewer.
     * @param id: new value for reviewer id.
     */
    public void setId(ReviewerId id) {
        this.id = id;
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
