package checker;

import domain.TopicEntity;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@SuppressWarnings("unused")
public class TopicPermissionChecker extends PermissionChecker<TopicEntity> {

    public TopicPermissionChecker() {
        super();
    }

    @Override
    protected void setUpChecker() {
        checker.put(OperationType.TO_ADD, (active, item) -> Boolean.TRUE);
        checker.put(OperationType.TO_DELETE, (active, item) -> Boolean.FALSE);
        checker.put(OperationType.TO_UPDATE, (active, item) -> Boolean.FALSE);
        checker.put(OperationType.TO_READ, (active, item) -> Boolean.TRUE);
    }

}
