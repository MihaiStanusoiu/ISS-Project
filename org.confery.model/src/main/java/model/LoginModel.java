package model;

import database.DatabaseLoaderInterface;
import domain.LoginEntity;
import domain.UserEntity;
import exception.ModelException;
import exception.SystemException;
import protocol.LoginProtocol;
import repository.RepositoryEntity;
import repository.RepositoryInterface;

import static utils.Conditional.basedOn;


/**
 * Created by Vlad on 6/1/2017.
 */
public class LoginModel
    extends Model<LoginEntity, Integer>
    implements LoginProtocol
{

    private RepositoryInterface<UserEntity, Integer> repositoryUsers;

    public LoginModel(DatabaseLoaderInterface loader) {
        super(LoginEntity.class, loader);
        repositoryUsers = new RepositoryEntity<>(UserEntity.class, loader);
    }

    @Override
    public LoginEntity getByIp(String ip) throws SystemException {
        for(LoginEntity c : getAll()) {
            if (c.getIp().equals(ip)) {
                return c;
            }
        }
        return null;
    }

    @Override
    public UserEntity getById(String ip) throws SystemException {
        LoginEntity entity = getByIp(ip);
        for(UserEntity user : repositoryUsers. getAll()) {
            if (entity.getId_user().equals(user.getId())) {
                return user;
            }
        }
        return null;
    }
}
