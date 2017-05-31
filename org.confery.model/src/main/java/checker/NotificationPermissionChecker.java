package checker;

import domain.NotificationEntity;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@SuppressWarnings("unused")
public class NotificationPermissionChecker extends PermissionChecker<NotificationEntity> {

    public NotificationPermissionChecker() {
        super();
    }

    @Override
    protected void setUpChecker() {
        checker.put(OperationType.TO_ADD, (active, item) -> Boolean.TRUE);
        checker.put(OperationType.TO_DELETE, (active, item) -> Boolean.TRUE);
        checker.put(OperationType.TO_UPDATE, (active, item) -> Boolean.TRUE);
        checker.put(OperationType.TO_READ, (active, item) -> Boolean.TRUE);
    }

}
