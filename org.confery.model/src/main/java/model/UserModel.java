package model;

import database.DatabaseLoaderInterface;
import domain.UserEntity;
import protocol.UserProtocol;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class UserModel extends Model<UserEntity, Integer> implements UserProtocol {

    public UserModel(DatabaseLoaderInterface loader) {
        super(UserEntity.class, loader);
    }

}
