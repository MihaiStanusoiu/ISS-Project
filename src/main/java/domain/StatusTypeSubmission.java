package domain;

/**
 * Name:         StatusTypeSubmission
 * Effect:       Submission type
 * Date:         02/04/2017
 * Tested:       True
 * @author       Tiron Andreea- Ecaterina
 * @version      1.0
 */

public enum StatusTypeSubmission implements StatusTypeInterface {

    ACCEPTED_STATUS {
        @Override
        public String convertString() {
            return "Accepted";
        }
    },

    REJECTED_STATUS {
        @Override
        public String convertString() {
            return "Rejected";
        }
    },

    BID_STATUS {
        @Override
        public String convertString() {
            return "Bid";
        }
    },

    REVIEW_STATUS {
        @Override
        public String convertString() {
            return "Review";
        }

    };

    /**
     * Effect: Return the status .
     * @param string : [String]
     * @return [StatusTypeSubmission] : returns the status.
     */
    public static StatusTypeSubmission fromString(String string) {
        switch (string) {
            case "Review":
                return StatusTypeSubmission.REVIEW_STATUS;
            case "Bid":
                return StatusTypeSubmission.BID_STATUS;
            case "Rejected":
                return StatusTypeSubmission.REJECTED_STATUS;
            case "Accepted":
                return StatusTypeSubmission.ACCEPTED_STATUS;
            default:
                return StatusTypeSubmission.ACCEPTED_STATUS;
        }
    }
}
