package manager;

import domain.LoginEntity;
import exception.SystemException;
import protocol.LoginProtocol;
import service.AuthenticationService;
import transfarable.User;
import translator.UserTranslator;

import java.rmi.RemoteException;
import java.rmi.server.RemoteServer;
import java.rmi.server.UnicastRemoteObject;
import java.util.function.Function;

import static utils.Try.runFunction;

/**
 * Tested: True
 *
 * @author Vlad Teodorescu
 * @version 1.0
 */

public class AuthenticationManager extends UnicastRemoteObject implements AuthenticationService {

    private LoginProtocol model;
    private Function<SystemException, RemoteException> thrower;
    private UserTranslator userTranslator;

    public AuthenticationManager(LoginProtocol model) throws RemoteException {
        this.model = model;
        thrower = exception -> new RemoteException(exception.getMessage());
        userTranslator = new UserTranslator();
    }

    @Override
    public User getActiveUser() throws RemoteException {
        return userTranslator.translate(runFunction(model::getUserByIp, getHost()).orThrow(thrower));
    }

    @Override
    public void addActiveUser(User user) throws RemoteException {
        runFunction(model::add, new LoginEntity(user.getId(), getHost()));
    }

    private String getHost() throws RemoteException {
        return runFunction(RemoteServer::getClientHost)
                .orThrow(exception -> new RemoteException("Inactive Server!"));
    }

    @Override
    public void deleteActiveUser(User user) throws RemoteException {
        runFunction(model::delete, getLoginEntityByIp());
    }

    private LoginEntity getLoginEntityByIp() throws RemoteException {
        return runFunction(model::getLoginDataByIp, getHost())
                .orThrow(exception -> new RemoteException(exception.getMessage()));
    }

}
