package convertor;

import domain.UserEntity;
import transferable.User;
import transferable.UserType;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class UserConverter {

    public static User convertUserEntity(UserEntity user) {
        return new User(user.getId(), user.getUsername(), user.getPassword(), user.getEmail(),
                user.getName(), user.getWebsite(), user.getBio(), user.getLocation(), UserType.REGULAR);
    }

    public static UserEntity convertUser(User user) {
        return new UserEntity(user.getId(), user.getUsername(), user.getPassword(), user.getEmail(),
                user.getName(), user.getWebsite(), user.getBio(), user.getLocation());
    }

}
