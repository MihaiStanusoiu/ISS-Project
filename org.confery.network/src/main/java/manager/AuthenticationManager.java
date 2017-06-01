package manager;

import domain.LoginEntity;
import protocol.LoginProtocol;
import service.AuthenticationService;
import transfarable.User;

import java.rmi.RemoteException;
import java.rmi.server.RemoteServer;

import static utils.Try.runFunction;

/**
 * Created by Vlad on 6/1/2017.
 */
public class AuthenticationManager implements AuthenticationService {

    private LoginProtocol model;

    public AuthenticationManager(LoginProtocol model) {
        this.model = model;
    }

    @Override
    public void addActiveUser(User user) throws RemoteException {
        runFunction(model::add, new LoginEntity(user.getId(), getClientHost()));
    }

    private String getClientHost() throws RemoteException {
        return runFunction(RemoteServer::getClientHost)
                .orThrow(exception -> new RemoteException("Inactive Server!"));
    }

    @Override
    public void deleteActiveUser(User user) throws RemoteException {
        runFunction(model::delete, getLoginEntityByIp());
    }

    private LoginEntity getLoginEntityByIp() throws RemoteException {
        return runFunction(model::getByIp, getClientHost())
                .orThrow(exception -> new RemoteException(exception.getMessage()));
    }
}
