package data;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@SuppressWarnings("unused")
public enum Status {
    /**
     * The submission has been reviewed by reviewer.
     */
    REVIEWED,

    /**
     * The submission is 'dead' (nobody wants to review) or still in the review process.
     */
    NOT_REVIEWED
}
