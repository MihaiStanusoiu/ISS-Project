package checker;

import domain.EditionEntity;
import domain.UserEntity;
import exception.SystemException;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class UserPermissionChecker {

    private UserEntity user;
    private EditionEntity edition;
    private OperationType operation;

    private enum OperationType {
        TO_DELETE
    }

    public UserPermissionChecker isAllowed(UserEntity user) {
        this.user = user;
        return this;
    }

    public UserPermissionChecker toDelete() {
        this.operation = OperationType.TO_DELETE;
        return this;
    }

    public Boolean theEdition(EditionEntity edition) throws SystemException {
        return check(this.user, edition, operation);
    }


    private Boolean check(UserEntity user, EditionEntity edition, OperationType operation) throws SystemException {
        switch (operation) {
            case TO_DELETE:
                return edition.getChair().equals(user);
            default:
                return Boolean.FALSE;
        }
    }

}
