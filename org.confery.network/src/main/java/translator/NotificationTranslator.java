package translator;

import domain.NotificationEntity;
import org.jetbrains.annotations.NotNull;
import transfarable.Notification;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class NotificationTranslator implements GenericTranslator<NotificationEntity, Notification>{

    @Override
    public Notification translate(@NotNull NotificationEntity notification) {
        return new Notification(notification.getId(), notification.getText(), notification.getPaymentType());
    }

    @Override
    public NotificationEntity translate(@NotNull Notification notification) {
        return new NotificationEntity(notification.getId(), notification.getText(), notification.getPaymentType());
    }
    
}
