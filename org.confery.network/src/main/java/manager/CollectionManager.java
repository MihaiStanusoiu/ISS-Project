package manager;

import domain.UserEntity;
import service.*;
import service.Service;

import java.rmi.RemoteException;
import java.util.List;

import static java.util.Arrays.asList;
import static utils.Try.runFunction;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class CollectionManager implements CollectionService {

    private LoginService loginService;
    private SignUpService signUpService;
    private UserService userService;
    private UserEntity activeUser;

    public CollectionManager(UserService userService) {
        this.userService = userService;
    }

    public CollectionManager setLoginService(final LoginService loginService) {
        this.loginService = loginService;
        return this;
    }

    public CollectionManager setSignUpService(final SignUpService signUpService) {
        this.signUpService = signUpService;
        return this;
    }

    @Override
    public LoginService loginService() {
        return loginService;
    }

    @Override
    public SignUpService signUpService() {
        return signUpService;
    }

    @Override
    public UserService userService() throws RemoteException {
        return userService;
    }

    @Override
    public UserEntity getActiveUser() {
        return activeUser;
    }

    @Override
    public void activeUser(UserEntity user) throws RemoteException {
        this.activeUser = user;
       // List<Service> list = asList(loginService, signUpService);
       // list.forEach(service -> runFunction(service::setActiveUser, user));
    }
}
