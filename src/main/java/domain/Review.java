package domain;

/**
 * Name:         Review
 * Effect:       Review from a reviewer for a submission.
 * Date:         02/04/2017
 * Tested:       True
 * @author       Tiron Andreea- Ecaterina
 * @version      1.0
 */
public class Review extends Idable<Integer>{

    private Integer idSubmission;
    private Integer idReviewer;
    private ReviewQualifier reviewQualifier;
    private ReviewStatus reviewStatus;
    private ReviewResponse reviewResponse;
    private String recommandationUrl;

    public Review(Integer id,Integer idSubmission, Integer idReviewer, ReviewQualifier reviewQualifier, ReviewStatus reviewStatus, ReviewResponse reviewResponse, String recommandationUrl) {
        this.id=id;
        this.idSubmission = idSubmission;
        this.idReviewer = idReviewer;
        this.reviewQualifier = reviewQualifier;
        this.reviewStatus = reviewStatus;
        this.reviewResponse = reviewResponse;
        this.recommandationUrl = recommandationUrl;
    }

    public Review(Integer idSubmission, Integer idReviewer, ReviewQualifier reviewQualifier, ReviewStatus reviewStatus, ReviewResponse reviewResponse, String recommandationUrl){
        this(0,idSubmission,idReviewer,reviewQualifier,reviewStatus,reviewResponse,recommandationUrl);
    }

    /**
     * Effect: Return the id of submission.
     * @return [Integer] : returns the id of submission.
     */
    public Integer getIdSubmission() {
        return idSubmission;
    }

    /**
     * Effect: Return the id of reviewer.
     * @return [Integer] : returns the id of reviewer.
     */
    public Integer getIdReviewer() {
        return idReviewer;
    }

    /**
     * Effect: Return the reviewQualifier.
     * @return [ReviewQualifier] : returns the reviewQualifier.
     */
    public ReviewQualifier getReviewQualifier() {
        return reviewQualifier;
    }

    /**
     * Effect: Return the review status.
     * @return [ReviewStatus] : returns the review status.
     */
    public ReviewStatus getReviewStatus() {
        return reviewStatus;
    }

    /**
     * Effect: Return the review response.
     * @return [ReviewResponse] : returns the review response.
     */
    public ReviewResponse getReviewResponse() {
        return reviewResponse;
    }

    /**
     * Effect: Return the recommandation url.
     * @return [String] : returns the recommandation url.
     */
    public String getRecommandationUrl() {
        return recommandationUrl;
    }
}
