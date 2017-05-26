package context;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public enum UserType {

    /**
     * For regular users - with no permissions over the data in relationship
     */
    REGULAR,

    EDITION_PC_MEMBER,

    EDITION_CHAIR,

    EDITION_CO_CHAIR,

    REVIEWER,

    SECTION_CHAIR,

    SECTION_SPEAKER,

    SECTION_LISTENER,

}
