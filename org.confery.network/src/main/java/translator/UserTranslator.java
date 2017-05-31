package translator;


import domain.UserEntity;
import org.jetbrains.annotations.NotNull;
import transfarable.User;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */


public class UserTranslator implements GenericTranslator<UserEntity, User> {

    @Override
    public UserEntity translate(@NotNull User user) {
        return new UserEntity(user.getId(), user.getUsername(), user.getPassword(),
                user.getEmail(), user.getName(), user.getWebsite(), user.getBio(), user.getLocation());
    }

    @Override
    public User translate(@NotNull UserEntity user) {
        return new User(user.getId(), user.getUsername(), user.getPassword(),
                user.getEmail(), user.getName(), user.getWebsite(), user.getBio(), user.getLocation());
    }

}
