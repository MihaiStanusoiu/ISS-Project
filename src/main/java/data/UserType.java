package data;

/**
 * Enum design to describe the user's type in the system and his permissions.
 * @author Alexandru Stoica
 * @version 1.0
 */

public enum UserType implements UserTypeInterface {

    /**
     * Regular User -- user that has just an account in our system.
     */
    REGULAR {
        @Override
        public Permission[] getPermissions() {
            return new Permission[] {
                    Permission.READ_CONFERENCE,
                    Permission.INSERT_CONFERENCE,
                    Permission.INSERT_SUBMISSION,
                    Permission.READ_USER,
                    Permission.INSERT_USER,
                    Permission.UPDATE_USER,
                    Permission.DELETE_USER,
            };
        }
    }
}
