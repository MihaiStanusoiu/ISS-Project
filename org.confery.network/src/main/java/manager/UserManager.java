package manager;

import checker.UserPermissionChecker;
import domain.UserEntity;
import protocol.UserProtocol;
import service.UserService;
import transfarable.User;
import translator.UserTranslator;

import java.rmi.RemoteException;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class UserManager extends GenericManager<User, Integer, UserEntity> implements UserService {

    public UserManager(UserProtocol model) throws RemoteException {
        super(model);
        super.translator = new UserTranslator();
        super.checker = new UserPermissionChecker();
    }

}
