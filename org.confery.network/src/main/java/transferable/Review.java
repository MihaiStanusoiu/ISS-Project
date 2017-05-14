package transferable;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@SuppressWarnings("all")
public class Review extends Idable<Integer> {

    /**
     * The submission's reviewer (final or proposed)
     */
    private User reviewer;
    /**
     * The reviewer's recomandation. [as url]
     */
    private String recommendationUrl;

    /**
     * The review's status.
     */
    private Status status;

    /**
     * The review's qualifier [final grade from reviewer].
     */
    private Qualifier qualifier;

    /**
     * The co-chair's response for proposed reviewer. -- provides permissions for reviewer.
     */
    private Response response;

    /**
     * @param id The review's id -- from database [not null]
     * @param reviewer The reviewer [user from system]
     * @param recommendationUrl The reviewer's recomandation [as url]
     * @param status The review's status [reviewed | not reviewed]
     * @param qualifier The reviewer's grade for submission.
     * @param response The co-chair's response to reviewer.
     */
    public Review(Integer id,
                  User reviewer,
                  String recommendationUrl,
                  Status status,
                  Qualifier qualifier,
                  Response response) {
        this.id = id;
        this.reviewer = reviewer;
        this.recommendationUrl = recommendationUrl;
        this.status = status;
        this.qualifier = qualifier;
        this.response = response;
    }

    /**
     * @param reviewer The reviewer [user from system]
     * @param recommendationUrl The reviewer's recomandation [as url]
     * @param status The review's status [reviewed | not reviewed]
     * @param qualifier The reviewer's grade for submission.
     * @param response The co-chair's response to reviewer.
     */
    public Review(User reviewer, String recommendationUrl,
                  Status status, Qualifier qualifier, Response response) {
        this(0, reviewer, recommendationUrl, status, qualifier, response);
    }
    /**
     * @param reviewer The reviewer [user from system]
     * @param status The review's status [reviewed | not reviewed]
     */
    public Review(User reviewer, Status status) {
        this.reviewer = reviewer;
        this.status = status;
    }

    /**
     * @return The reviewer [user from system]
     */
    public User getReviewer() {
        return reviewer;
    }

    /**
     * @return The reviewer's recomandation [as url]
     */
    public String getRecommendationUrl() {
        return recommendationUrl;
    }

    /**
     * @return The review's status [reviewed | not reviewed]
     */
    public Status getStatus() {
        return status;
    }

    /**
     * @return The reviewer's grade for submission.
     */
    public Qualifier getQualifier() {
        return qualifier;
    }

    /**
     * @return The co-chair's response to reviewer.
     */
    public Response getResponse() {
        return response;
    }
}
