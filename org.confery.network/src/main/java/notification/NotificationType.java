package notification;

import java.io.Serializable;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public enum NotificationType implements Serializable {

    SIGNAL_LOGIN,
    SIGNAL_SIGN_UP,
    SIGNAL_LOGOUT,
    UPDATE_USER

}
