package checker;

import domain.UserEntity;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class UserPermissionChecker extends PermissionChecker<UserEntity> {

    public UserPermissionChecker() {
        super();
    }

    @Override
    protected void setUpChecker() {
        checker.put(OperationType.TO_ADD, (active, item) -> active != null);
        checker.put(OperationType.TO_DELETE, UserEntity::equals);
        checker.put(OperationType.TO_UPDATE, UserEntity::equals);
        checker.put(OperationType.TO_READ, (active, item) -> Boolean.TRUE);
    }

}
