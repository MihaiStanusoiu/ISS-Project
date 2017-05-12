package validator;

import domain.NotificationEntity;

import java.util.List;

/**
 * Name:         Validator behaviour for NotificationEntity
 * Effect:       Validates a notification
 * Date:         06/05/2017
 * Tested:       False
 *
 * @author Teodorescu Vlad
 * @version 1.0
 */
@SuppressWarnings("all")
public class ValidatorRepositoryBehaviourNotificationEntity extends ValidatorRepositoryBehaviour<NotificationEntity> {

    /**
     * @param object : the notification to validate
     * @return [List<String>] : list of error messages found
     */
    @Override
    public List<String> check(NotificationEntity object) {
        if (object.getId() == null) {
            accumulator.add("Notification id is null.");
        }
        if (object.getPaymentType() == null) {
            accumulator.add("Notification payment type is null.");
        }
        if(object.getText() != null) {
            if (object.getText().equals("")) {
                accumulator.add("Notification text is empty.");
            }
        }
        else{
            accumulator.add("Notification text is null.");
        }
        if (object.getUser() == null) {
            accumulator.add("Notification user is null.");
        }
        return accumulator;
    }
}
