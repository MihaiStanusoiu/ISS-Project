package checker;

import domain.SubmissionEntity;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@SuppressWarnings("unused")
public class SubmissionPermissionChecker extends PermissionChecker<SubmissionEntity> {

    public SubmissionPermissionChecker() {
        super();
    }

    @Override
    protected void setUpChecker() {
        // TODO
        checker.put(OperationType.TO_ADD, (active, item) -> Boolean.TRUE);
        checker.put(OperationType.TO_DELETE, (active, item) -> Boolean.FALSE);
        // Should a user be allowed to delete / update a tag / topic from our system?
        checker.put(OperationType.TO_UPDATE, (active, item) -> Boolean.FALSE);
        checker.put(OperationType.TO_READ, (active, item) -> Boolean.TRUE);
    }

}
