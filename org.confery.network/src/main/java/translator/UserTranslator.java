package translator;


import domain.UserEntity;
import transfarable.User;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */


public class UserTranslator {

    public static UserEntity translate(User user) {
        return new UserEntity(user.getId(), user.getUsername(), user.getPassword(), user.getEmail(), user.getName(),
                user.getWebsite(), user.getBio(), user.getLocation());
    }


    public static User translate(UserEntity user) {
        return new User(user.getId(), user.getUsername(), user.getPassword(),
                user.getEmail(), user.getName(), user.getWebsite(), user.getBio(), user.getLocation());
    }


}
