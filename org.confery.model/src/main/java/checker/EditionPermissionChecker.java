package checker;

import domain.EditionEntity;

import static utils.Try.runFunction;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class EditionPermissionChecker extends PermissionChecker<EditionEntity> {

    public EditionPermissionChecker() {
        super();
    }

    @Override
    protected void setUpChecker() {
        checker.put(OperationType.TO_ADD, (active, item) -> Boolean.TRUE); // TODO: TRY TO IMPROVE THIS
        checker.put(OperationType.TO_DELETE,
                (active, item) -> runFunction(item::getChair).or(new NullUserEntity()).equals(active));
        checker.put(OperationType.TO_UPDATE,
                (active, item) -> runFunction(item::getChair).or(new NullUserEntity()).equals(active));
        checker.put(OperationType.TO_READ, (active, item) -> Boolean.TRUE);
    }
}
