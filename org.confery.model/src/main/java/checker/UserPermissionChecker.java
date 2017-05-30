package checker;

import domain.EditionEntity;
import domain.UserEntity;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.function.BiFunction;

import static utils.Try.runFunction;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class UserPermissionChecker {

    private UserEntity active;
    private UserEntity user;
    private EditionEntity edition;
    private OperationType operation;
    private Dictionary<OperationType, BiFunction<UserEntity, UserEntity, Boolean>> checkerUser = new Hashtable<>();
    private Dictionary<OperationType, BiFunction<UserEntity, EditionEntity, Boolean>> checkerEdition = new Hashtable<>();

    public UserPermissionChecker(UserEntity user) {
        this.active = user;
        setUpUserChecker();
        setUpEditionChecker();
    }

    private void setUpUserChecker() {
        checkerUser.put(OperationType.TO_ADD, (active, item) -> active != null);
        checkerUser.put(OperationType.TO_DELETE, UserEntity::equals);
        checkerUser.put(OperationType.TO_UPDATE, UserEntity::equals);
        checkerUser.put(OperationType.TO_READ, (active, item) -> Boolean.TRUE);
    }

    private void setUpEditionChecker() {
        checkerEdition.put(OperationType.TO_ADD, (active, item) -> Boolean.TRUE); // TODO: TRY TO IMPROVE THIS
        checkerEdition.put(OperationType.TO_DELETE,
                (active, item) -> runFunction(item::getChair).or(new NullUserEntity()).equals(active));
        checkerEdition.put(OperationType.TO_UPDATE,
                (active, item) -> runFunction(item::getChair).or(new NullUserEntity()).equals(active));
        checkerEdition.put(OperationType.TO_READ, (active, item) -> Boolean.TRUE);
    }

    private enum OperationType {
        TO_ADD, TO_READ, TO_UPDATE, TO_DELETE
    }

    public static UserPermissionChecker isAllowed(UserEntity user) {
        return new UserPermissionChecker(user);
    }

    public UserPermissionChecker toDelete() {
        this.operation = OperationType.TO_DELETE;
        return this;
    }

    public UserPermissionChecker toAdd() {
        this.operation = OperationType.TO_ADD;
        return this;
    }

    public UserPermissionChecker toUpdate() {
        this.operation = OperationType.TO_UPDATE;
        return this;
    }

    public UserPermissionChecker toRead() {
        this.operation = OperationType.TO_READ;
        return this;
    }

    public Boolean theUser(UserEntity user) {
        return check(this.active, user, operation);
    }

    public Boolean theEdition(EditionEntity edition) {
        return check(this.active, edition, operation);
    }


    private Boolean check(UserEntity active, UserEntity user, OperationType operation) {
        return checkerUser.get(operation).apply(active, user);
    }

    private Boolean check(UserEntity user, EditionEntity edition, OperationType operation) {
        return checkerEdition.get(operation).apply(active, edition);
    }

}
