package transferable;

/**
 * Interface designed to provide the user's permissions based on user's type.
 * @author Alexandru Stoica
 * @version 1.0
 */

@SuppressWarnings("all")
public interface UserTypeInterface {

    /**
     * @return The user's permissions [based on type]
     */
    Permission[] getPermissions();
}
