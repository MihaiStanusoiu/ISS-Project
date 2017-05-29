package translator;


import domain.UserEntity;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */


public class UserTranslator {

    public static UserEntity translate(User user) {
        if (user == null) {
            return null;
        }

        return new UserEntity(user.getId(), user.getUsername(), user.getPassword(), user.getEmail(), user.getName(),
                user.getWebsite(), user.getBio(), user.getLocation());
    }


    public static User translate(UserEntity user) {
        if (user == null) {
            return null;
        }

        return new User(user.getId(), user.getUsername(), user.getPassword(),
                user.getEmail(), user.getName(), user.getWebsite(), user.getBio(), user.getLocation());
    }


}
