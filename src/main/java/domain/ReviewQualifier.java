package domain;

/**
 * Name:         ReviewQualifier
 * Effect:       The qualifier of the review.
 * Date:         02/04/2017
 * Tested:       False
 * @author       Tiron Andreea- Ecaterina
 * @version      1.0
 */
public enum ReviewQualifier implements StatusTypeInterface {

    WEAK_AGREE {
        @Override
        public String convertString() {
            return "WeakAgree";
        }
    }
    ,
    STRONG_AGREE {
        @Override
        public String convertString() {
            return "StrongAgree";
        }
    }
    ,
    NEUTRAL_AGREE {
        @Override
        public String convertString() {
            return "NeutralAgree";
        }
    }
    ,

    BORDERLINE {
        @Override
        public String convertString() {
            return "Borderline";
        }
    }
    ,

    WEAK_REJECT {
        @Override
        public String convertString() {
            return "WeakReject";
        }
    }
    ,
    STRONG_REJECT {
        @Override
        public String convertString() {
            return "StrongReject";
        }
    }
    ,
    NEUTRAL_REJECT {
        @Override
        public String convertString() {
            return "NeutralReject";
        }
    }
    ;

    /**
     * Effect: Return the status .
     * @param string : [String]
     * @return [ReviewQualifier] : returns the status.
     */
    public static ReviewQualifier fromString(String string) {
        switch (string) {
            case "WeakAgree":
                return ReviewQualifier.WEAK_AGREE;
            case "NeutralAgree":
                return ReviewQualifier.NEUTRAL_AGREE;
            case "StrongAgree":
                return ReviewQualifier.STRONG_AGREE;
            case "Borderline":
                return ReviewQualifier.BORDERLINE;
            case "WeakReject":
                return ReviewQualifier.WEAK_REJECT;
            case "NeutralReject":
                return ReviewQualifier.NEUTRAL_REJECT;
            case "StrongReject":
                return ReviewQualifier.STRONG_REJECT;
            default:
                return ReviewQualifier.BORDERLINE;
        }
    }
}
