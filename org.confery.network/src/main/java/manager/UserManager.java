package manager;

import domain.UserEntity;
import protocol.UserProtocol;
import service.UserService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class UserManager extends UnicastRemoteObject implements UserService {

    private UserProtocol model;

    public UserManager(UserProtocol model) throws RemoteException {
        this.model = model;
    }

    @Override
    public Integer add(UserEntity element) throws RemoteException {
        return null;
    }

    @Override
    public UserEntity update(UserEntity element, UserEntity with) throws RemoteException {
        return null;
    }

    @Override
    public UserEntity delete(UserEntity element) throws RemoteException {
        return null;
    }

    @Override
    public UserEntity getElementById(Integer integer) throws RemoteException {
        return null;
    }

    @Override
    public List<UserEntity> getAll() throws RemoteException {
        return model.getAll();
    }

}
