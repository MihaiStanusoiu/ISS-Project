package notification;

import java.io.Serializable;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class Notification implements Serializable {

    private NotificationType type;

    public Notification(NotificationType type) {
        this.type = type;
    }

    public NotificationType getType() {
        return type;
    }

}
