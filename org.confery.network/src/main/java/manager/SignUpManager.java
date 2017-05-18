package manager;

import convertor.UserConverter;
import exception.SystemException;
import notification.NotificationCenter;
import protocol.UserProtocol;
import service.SignUpService;
import transferable.User;

import java.rmi.RemoteException;
import java.util.stream.Collectors;

/**
 * @author Alexandru Stoica
 * @version 1.0
 * Tested: True
 */

public class SignUpManager implements SignUpService {

    @SuppressWarnings("all")
    private NotificationCenter notificationCenter;
    private UserProtocol userModel;

    public SignUpManager(NotificationCenter notificationCenter, UserProtocol userModel) {
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
        return userModel.getAll().stream().noneMatch(user ->
                user.getUsername().equals(username));
    }

    private Boolean isEmailValid(String email) {
        return email.matches(".*@.*");
    }

    @Override
    public User signUp(String username, String password, String confirm, String email, String name)
            throws RemoteException, SystemException {
        if (isUsernameUnique(username) && isPasswordValid(password, confirm) && isEmailValid(email)) {
            User user = new User(username, password, email, name);
            Integer id = userModel.add(UserConverter.convertUser(user));
            return UserConverter.convertUserEntity(userModel.getElementById(id));
        }
        throw new RemoteException("Invalid Username or Password");
    }
}
