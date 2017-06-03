package protocol;

import domain.LoginEntity;
import domain.UserEntity;
import exception.SystemException;

/**
 * Created by Vlad on 6/1/2017.
 */
public interface LoginProtocol extends ModelInterface<LoginEntity, Integer> {

    /**
     * Returns the login data for a client host.
     * @param ip The client's host address
     * @return The login data [idUser, hostClient]
     * @throws SystemException If it's unable to find the data
     */
    LoginEntity getLoginDataByIp(String ip) throws SystemException;

    /**
     * Returns the user logged with host address == ip.
     * @param ip The host's address
     * @return The user logged with host's address
     * @throws SystemException If it's unable to find the host's address
     */
    UserEntity getUserByIp(String ip) throws SystemException;

}
