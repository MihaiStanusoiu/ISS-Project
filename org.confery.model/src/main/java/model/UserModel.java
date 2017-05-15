package model;

import database.DatabaseLoader;
import domain.UserEntity;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class UserModel extends Model<UserEntity, Integer> implements UserProtocol {

    public UserModel(DatabaseLoader loader) {
        super(UserEntity.class, loader);
    }

}
