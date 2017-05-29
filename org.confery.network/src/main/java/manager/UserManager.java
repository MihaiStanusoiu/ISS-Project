package manager;

import domain.UserEntity;
import protocol.UserProtocol;
import service.UserService;

import java.rmi.RemoteException;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class UserManager extends GenericManager<UserEntity, Integer> implements UserService {

    public UserManager(UserProtocol model) throws RemoteException {
        super(model);
    }

}
