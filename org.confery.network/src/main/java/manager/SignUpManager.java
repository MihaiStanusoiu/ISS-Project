package manager;

import domain.UserEntity;
import exception.SystemException;
import notification.NotificationCenter;
import protocol.UserProtocol;
import service.SignUpService;
import translator.UserTranslator;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.stream.Collectors;

import static utils.Conditional.basedOn;
import static utils.Try.runFunction;

/**
 * @author Alexandru Stoica
 * @version 1.0
 * Tested: True
 */

public class SignUpManager extends UnicastRemoteObject implements SignUpService {

    private NotificationCenter notificationCenter;
    private UserProtocol userModel;
    private UserEntity active;

    public SignUpManager(NotificationCenter notificationCenter, UserProtocol userModel)
            throws RemoteException {
        this.notificationCenter = notificationCenter;
        this.userModel = userModel;
    }

    private Integer countDigitLetters(String string) {
        return string.chars().mapToObj(character -> (char)character)
                .filter(Character::isDigit).collect(Collectors.toList()).size();
    }

    private Integer countUpperCaseLetters(String string) {
        return string.chars().mapToObj(character -> (char)character)
                .filter(Character::isUpperCase).collect(Collectors.toList()).size();
    }

    private Integer countLowerCaseLetters(String string) {
        return string.chars().mapToObj(character -> (char)character)
                .filter(Character::isLowerCase).collect(Collectors.toList()).size();
    }

    private Integer getPasswordScore(String password) {
        return password.length() * 4 +
                countDigitLetters(password) * 4 +
                (password.length() - countUpperCaseLetters(password)) * 2 +
                (password.length() - countLowerCaseLetters(password)) * 2;
    }

    private Boolean isPasswordValid(String password, String confirm) {
        return password.equals(confirm) && getPasswordScore(password) > 48;
    }

    private Boolean isUsernameUnique(String username) throws SystemException {
        return userModel.getAll().stream().noneMatch(user -> user.getUsername().equals(username));
    }

    private Boolean isEmailValid(String email) {
        return email.matches(".*@.*");
    }

    @Override
    public User signUp(String username, String password, String confirm, String email, String name)
            throws RemoteException {
        basedOn(runFunction(this::isUsernameUnique, username)
                .orThrow(exception-> new RemoteException("Invalid Username")))
                .orThrow(new RemoteException("Username already used in our system"));
        UserEntity user = (isPasswordValid(password, confirm) && isEmailValid(email)) ?
                new UserEntity(username, password, email, name) : null;
        Integer id = runFunction(userModel::add, user).orThrow(exception -> new RemoteException(exception.getMessage()));
        User transferableUser = UserTranslator.translate(
                runFunction(userModel::getElementById, id).orThrow(exception -> new RemoteException(exception.getMessage()))
        );

        return transferableUser;
    }

    @Override
    public void activeUser(User user) throws RemoteException {
        UserEntity userEntity = UserTranslator.translate(user);
        active = userEntity;
    }
}
