package model;

import database.DatabaseLoaderInterface;
import domain.LoginEntity;
import domain.UserEntity;
import exception.SystemException;
import nulldomain.LoginNullEntity;
import nulldomain.NullUserEntity;
import protocol.LoginProtocol;
import repository.RepositoryEntity;
import repository.RepositoryInterface;

/**
 * Tested: True
 *
 * @author Vlad Teodorescu
 * @version 1.0
 */

public class LoginModel
        extends Model<LoginEntity, Integer> implements LoginProtocol {

    private RepositoryInterface<UserEntity, Integer> repositoryUser;

    public LoginModel(DatabaseLoaderInterface loader) {
        super(LoginEntity.class, loader);
        repositoryUser = new RepositoryEntity<>(UserEntity.class, loader);
    }

    @Override
    public LoginEntity getLoginDataByIp(String ip) throws SystemException {
        return getAll().stream().filter(data -> data.getHostUser().equals(ip))
                .findFirst().orElse(new LoginNullEntity());
    }

    @Override
    public UserEntity getUserByIp(String ip) throws SystemException {
        LoginEntity data = getLoginDataByIp(ip);
        return repositoryUser.getAll().stream()
                .filter(user -> user.getId().equals(data.getIdUser()))
                .findFirst().orElse(new NullUserEntity());
    }
}
