
package domain;

/**
 * Tested: True
 *
 * @author Alexandru Stoica
 * @version 1.0
 */

public enum QualifierTypeEntity implements QualifierTypeEntityInterface {


    STRONG_AGREE {
        @Override public Integer getValue() {
            return DEFAULT_STRONG_AGREE;
        }
    },

    AGREE {
        @Override public Integer getValue() {
            return DEFAULT_AGREE;
        }
    },

    WEAK_AGREE{
        @Override public Integer getValue() {
            return DEFAULT_WEAK_AGREE;
        }
    },

    BORDERLINE {
        @Override public Integer getValue() {
            return DEFAULT_BORDERLINE;
        }
    },

    WEAK_DISAGREE {
        @Override public Integer getValue() {
            return DEFAULT_WEAK_DISAGREE;
        }
    },

    DISAGREE {
        @Override public Integer getValue() {
            return DEFAULT_DISAGREE;
        }
    },

    STRONG_DISAGREE {
        @Override public Integer getValue() {
            return DEFAULT_STRONG_DISAGREE;
        }
    };

    private final static Integer DEFAULT_STRONG_AGREE = 3;
    private final static Integer DEFAULT_AGREE = 2;
    private final static Integer DEFAULT_WEAK_AGREE = 1;
    private final static Integer DEFAULT_BORDERLINE = 0;
    private final static Integer DEFAULT_WEAK_DISAGREE = -1;
    private final static Integer DEFAULT_DISAGREE = -2;
    private final static Integer DEFAULT_STRONG_DISAGREE = -3;

    public static QualifierTypeEntity fromString(String string) {
        switch (string) {
            case "STRONG_AGREE":
                return STRONG_AGREE;
            case "AGREE":
                return AGREE;
            case "WEAK_AGREE":
                return WEAK_AGREE;
            case "BORDERLINE":
                return BORDERLINE;
            case "WEAK_DISAGREE":
                return WEAK_DISAGREE;
            case "DISAGREE":
                return DISAGREE;
            case "STRONG_DISAGREE":
                return STRONG_DISAGREE;
            default:
                return BORDERLINE;
        }
    }

}
