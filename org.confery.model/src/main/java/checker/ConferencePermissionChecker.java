package checker;

import domain.ConferenceEntity;
import nulldomain.NullUserEntity;

import static utils.Try.runFunction;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@SuppressWarnings("unused")
public class ConferencePermissionChecker extends PermissionChecker<ConferenceEntity> {

    public ConferencePermissionChecker() {
        super();
    }

    @Override
    protected void setUpChecker() {
        checker.put(OperationType.TO_ADD, (active, item) -> active != null);
        checker.put(OperationType.TO_DELETE, (active, item) ->
                runFunction(item.getLatestEdition()::getChair).or(new NullUserEntity()).equals(active));
        checker.put(OperationType.TO_UPDATE, (active, item) ->
                runFunction(item.getLatestEdition()::getChair).or(new NullUserEntity()).equals(active));
        checker.put(OperationType.TO_READ, (active, item) -> Boolean.TRUE);
    }
}
