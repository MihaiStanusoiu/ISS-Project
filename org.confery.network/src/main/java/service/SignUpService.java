package service;

import domain.UserEntity;
import transferable.User;

import java.rmi.RemoteException;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface SignUpService extends ServiceInterface {

    User signUp(String username, String password, String confirm, String email, String name)
            throws RemoteException;

}
