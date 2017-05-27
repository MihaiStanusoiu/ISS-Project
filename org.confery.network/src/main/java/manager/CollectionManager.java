package manager;

import domain.UserEntity;
import service.CollectionService;
import service.LoginService;
import service.Service;
import service.SignUpService;

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
    private UserEntity activeUser;

    public CollectionManager() { }

    public CollectionManager setLoginService(final LoginService loginService) {
        this.loginService = loginService;
        return this;
    }

    public CollectionManager setSignUpService(final SignUpService signUpService) {
        this.signUpService = signUpService;
        return this;
    }

    @Override
    public LoginService getLoginService() {
        return loginService;
    }

    @Override
    public SignUpService getSignUpService() {
        return signUpService;
    }

    @Override
    public UserEntity getActiveUser() {
        return activeUser;
    }

    @Override
    public void setActiveUser(UserEntity activeUser) throws RemoteException {
        this.activeUser = activeUser;
        List<Service> list = asList(loginService, signUpService);
        list.forEach(service -> runFunction(service::setActiveUser, activeUser));
    }
}
