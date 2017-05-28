package translator;

import domain.NotificationEntity;
import transfarable.Notification;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */
public class NotificationTranslator {

    public static Notification translate(NotificationEntity notification) {
        return new Notification(notification.getId(), notification.getText(), notification.getPaymentType());
    }

    public static NotificationEntity translate(Notification notification) {
        return new NotificationEntity(notification.getId(), notification.getText(), notification.getPaymentType());
    }
    
}
