package notification;

import java.io.Serializable;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class NotificationUpdate implements Serializable {

    private final NotificationType type;

    public NotificationUpdate(NotificationType type) {
        this.type = type;
    }

    public NotificationType getType() {
        return type;
    }

}
