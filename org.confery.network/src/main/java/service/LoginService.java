package service;


import transfarable.User;

import java.rmi.RemoteException;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface LoginService extends ServiceInterface {

    User login(String username, String password) throws RemoteException;

}
