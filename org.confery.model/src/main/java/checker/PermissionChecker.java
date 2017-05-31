package checker;

import domain.UserEntity;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.function.BiFunction;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public abstract class PermissionChecker<T> {

    private UserEntity active;
    private OperationType operation;
    protected final Dictionary<OperationType, BiFunction<UserEntity, T, Boolean>> checker;

    protected PermissionChecker() {
        this.checker = new Hashtable<>();
        setUpChecker();
    }

    protected abstract void setUpChecker();

    protected enum OperationType {
        TO_ADD, TO_READ, TO_UPDATE, TO_DELETE
    }

    public PermissionChecker<T> isAllowed(UserEntity user) {
        this.active = user;
        return this;
    }

    public PermissionChecker<T> toDelete() {
        this.operation = OperationType.TO_DELETE;
        return this;
    }

    public PermissionChecker<T> toAdd() {
        this.operation = OperationType.TO_ADD;
        return this;
    }

    public PermissionChecker<T> toUpdate() {
        this.operation = OperationType.TO_UPDATE;
        return this;
    }

    public PermissionChecker<T> toRead() {
        this.operation = OperationType.TO_READ;
        return this;
    }

    public Boolean theObject(T object) {
        return check(object, operation);
    }

    private Boolean check(T object, OperationType operation) {
        return checker.get(operation).apply(active, object);
    }

}
