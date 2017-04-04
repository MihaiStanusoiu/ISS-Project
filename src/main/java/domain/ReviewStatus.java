package domain;

/**
 * Name:         ReviewStatus
 * Effect:       The review status of the submission.
 * Date:         02/04/2017
 * Tested:       False
 * @author       Tiron Andreea- Ecaterina
 * @version      1.0
 */
public enum ReviewStatus implements StatusTypeInterface {

    REVIEWED {
        @Override
        public String convertString() {
            return "Reviewed";
        }
    },

    NOT_REVIEWED{
        @Override
        public String convertString() {
            return "NotReviewed";
        }
    };

    /**
     * Effect: Return the status of review.
     * @param string : [String]
     * @return [ReviewStatus] : returns the status.
     */
    public static ReviewStatus fromString(String string) {
        switch (string) {
            case "Reviewed":
                return ReviewStatus.REVIEWED;
            case "NotReviewed":
                return ReviewStatus.NOT_REVIEWED;
            default:
                return ReviewStatus.NOT_REVIEWED;
        }
    }
}
