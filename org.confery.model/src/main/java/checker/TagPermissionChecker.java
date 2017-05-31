package checker;

import domain.TagEntity;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@SuppressWarnings("unused")
public class TagPermissionChecker extends PermissionChecker<TagEntity> {

    public TagPermissionChecker() {
        super();
    }

    @Override
    protected void setUpChecker() {
        checker.put(OperationType.TO_ADD, (active, item) -> Boolean.TRUE);
        checker.put(OperationType.TO_DELETE, (active, item) -> Boolean.FALSE);
        // Should a user be allowed to delete / update a tag / topic from our system?
        checker.put(OperationType.TO_UPDATE, (active, item) -> Boolean.FALSE);
        checker.put(OperationType.TO_READ, (active, item) -> Boolean.TRUE);
    }

}
