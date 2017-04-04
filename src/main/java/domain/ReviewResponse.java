package domain;

/**
 * Name:         ReviewResponse
 * Effect:       The response for the review.
 * Date:         02/04/2017
 * Tested:       False
 * @author       Tiron Andreea- Ecaterina
 * @version      1.0
 */

public enum ReviewResponse implements StatusTypeInterface {

    NOT_ASSIGNED {
        @Override
        public String convertString() {
            return "NotAssigned";
        }
    }
    ,
    ALLOWED_TO_REVIEW{
        @Override
        public String convertString() {
            return "AllowedToReview";
        }
    },

    NOT_ALLOWED_TO_REVIEW(){
        @Override
        public String convertString() {
            return "NotAllowedToReview";
        }
    };

    /**
     * Effect: Return the status of response.
     * @param string : [String]
     * @return [ReviewResponse] : returns the response.
     */
    public static ReviewResponse fromString(String string) {
        switch (string) {
            case "NotAssigned":
                return ReviewResponse.NOT_ASSIGNED;
            case "AllowedToReview":
                return ReviewResponse.ALLOWED_TO_REVIEW;
            case "NotAllowedToReview":
                return ReviewResponse.NOT_ALLOWED_TO_REVIEW;
            default:
                return ReviewResponse.NOT_ASSIGNED;
        }
    }
}
