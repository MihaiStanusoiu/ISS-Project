package manager;

import domain.UserEntity;
import exception.SystemException;
import org.jetbrains.annotations.Nullable;
import protocol.LoginProtocol;
import protocol.UserProtocol;
import service.SignUpService;
import transfarable.NullUser;
import transfarable.User;
import translator.UserTranslator;

import java.rmi.RemoteException;
import java.rmi.server.RemoteServer;
import java.rmi.server.UnicastRemoteObject;
import java.util.function.Function;
import java.util.stream.Collectors;

import static utils.Conditional.basedOn;
import static utils.Try.runFunction;

/**
 * Tested: True
 *
 * @author Alexandru Stoica
 * @version 1.0
 */

public class SignUpManager extends UnicastRemoteObject implements SignUpService {

    private final UserProtocol userModel;
    private final UserTranslator translator;
    private LoginProtocol provider;
    private final Function<SystemException, RemoteException> thrower;

    public SignUpManager( UserProtocol userModel, LoginProtocol loginProtocol) throws RemoteException {
        this.provider = loginProtocol;
        this.userModel = userModel;
        this.translator = new UserTranslator();
        this.thrower = exception -> new RemoteException(exception.getMessage());
    }

    private UserEntity getActiveUser() throws RemoteException {
        String host = runFunction(RemoteServer::getClientHost)
                .orThrow(exception -> new RemoteException(exception.getMessage()));
        return runFunction(provider::getUserByIp, host).getElement();
    }

    private Integer countDigitLetters(String string) {
        return string.chars().mapToObj(character -> (char) character)
                .filter(Character::isDigit).collect(Collectors.toList()).size();
    }

    private Integer countUpperCaseLetters(String string) {
        return string.chars().mapToObj(character -> (char) character)
                .filter(Character::isUpperCase).collect(Collectors.toList()).size();
    }

    private Integer countLowerCaseLetters(String string) {
        return string.chars().mapToObj(character -> (char) character)
                .filter(Character::isLowerCase).collect(Collectors.toList()).size();
    }

    private Integer getPasswordScore(String password) {
        return password.length() * 4 + countDigitLetters(password) * 4 +
                (password.length() - countUpperCaseLetters(password)) * 2 +
                (password.length() - countLowerCaseLetters(password)) * 2;
    }

    private Boolean isPasswordValid(String password, String confirm) {
        return password.equals(confirm) && getPasswordScore(password) > 48;
    }

    private Boolean isUsernameUnique(String username) {
        return userModel.getAll().stream().noneMatch(user -> user.getUsername().equals(username));
    }

    private Boolean isEmailValid(String email) {
        return email.matches(".*@.*");
    }

    @Override
    public User signUp(String username, String password, String confirm, String email, String name)
            throws RemoteException {
        checkUsername(username);
        UserEntity user = getUser(username, password, confirm, email, name);
        basedOn(user != null).orThrow(new RemoteException("Invalid Password"));
        Integer id = runFunction(userModel::add, user).orThrow(thrower);
        UserEntity register = runFunction(userModel::getElementById, id)
                .orThrow(exception -> new RemoteException(exception.getMessage()));
        return register != null ? translator.translate(register) : new NullUser();
    }

    @Nullable
    private UserEntity getUser(String username, String password, String confirm, String email, String name) {
        return (isPasswordValid(password, confirm) && isEmailValid(email)) ?
                new UserEntity(username, password, email, name) : null;
    }

    private void checkUsername(String username) throws RemoteException {
        basedOn(runFunction(this::isUsernameUnique, username)
                .orThrow(exception -> new RemoteException("Invalid Username")))
                .orThrow(new RemoteException("Username already used in our system"));
    }

}
