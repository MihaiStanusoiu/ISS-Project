package service;


import org.jetbrains.annotations.NotNull;
import transfarable.User;

import java.rmi.RemoteException;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface LoginService extends ServiceInterface {

    User login(@NotNull String username, @NotNull String password) throws RemoteException;

}
