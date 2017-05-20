package validator;

import domain.NotificationEntity;

import java.util.List;
import java.util.Objects;

import static utils.Conditional.basedOn;

/**
 * Tested: True
 *
 * @author Teodorescu Vlad
 * @version 1.0
 */

public class ValidatorRepositoryBehaviourNotificationEntity
        extends ValidatorRepositoryBehaviour<NotificationEntity> {

    /**
     * @param object : the notification to validate
     * @return [List<String>] : list of error messages found
     */
    @Override
    public List<String> check(NotificationEntity object) {
        basedOn(Objects.isNull(object))
                .runTrue(accumulator::add, "Notification is NULL!");
        basedOn(Objects.isNull(object.getId()))
                .runTrue(accumulator::add, "Notification's id is NULL!");
        basedOn(Objects.isNull(object.getPaymentType()))
                .runTrue(accumulator::add, "Notification's payment type is NULL!");
        basedOn(Objects.isNull(object.getText()) || object.getText().equals(""))
                .runTrue(accumulator::add, "Notification's text is INVALID!");
        return accumulator;
    }
}
