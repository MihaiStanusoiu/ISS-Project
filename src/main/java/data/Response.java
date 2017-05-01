package data;

/**
 * Enum design to provide a response type from co-chair to reviewer based on review object.
 * @author Alexandru Stoica
 * @version 1.0
 */

public enum Response {

    /**
     * The proposed reviewer is still waiting for an answer from co-chair.
     */
    NOT_ASSIGNED,

    /**
     * The reviewer is allowed by co-chair to review the submission.
     */
    ALLLOWED_TO_REVIEW,

    /**
     * The reviewer is not allowed by the co-chair to review the submission.
     */
    NOT_ALLOWED_TO_REVIEW
}
