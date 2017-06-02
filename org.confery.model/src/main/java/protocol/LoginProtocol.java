package protocol;

import domain.LoginEntity;
import domain.UserEntity;
import exception.SystemException;

/**
 * Created by Vlad on 6/1/2017.
 */
public interface LoginProtocol extends ModelInterface<LoginEntity, Integer> {

    LoginEntity getLoginDataByIp(String ip) throws SystemException;

    UserEntity getUserByIp(String ip) throws SystemException;
}
