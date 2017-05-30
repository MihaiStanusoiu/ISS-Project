package translator;


import domain.UserEntity;
import transfarable.User;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */


public class UserTranslator {

    public static UserEntity translate(User user) {
        return user == null ? new UserEntity(user.getId(), user.getUsername(), user.getPassword(),
                user.getEmail(), user.getName(), user.getWebsite(), user.getBio(), user.getLocation()) : null;
    }


    public static User translate(UserEntity user) {
        return user == null ? new User(user.getId(), user.getUsername(), user.getPassword(),
                user.getEmail(), user.getName(), user.getWebsite(), user.getBio(), user.getLocation()) : null;
    }

}
