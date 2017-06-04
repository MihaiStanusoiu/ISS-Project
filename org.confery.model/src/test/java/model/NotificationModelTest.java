package model;

import database.DatabaseLoaderFactory;
import database.DatabaseLoaderInterface;
import database.DatabaseLoaderType;
import domain.NotificationEntity;
import domain.UserEntity;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class NotificationModelTest {

    private UserModel userModel;
    private NotificationModel notificationModel;

    @Before
    public void setUp() throws Exception {
        DatabaseLoaderInterface loader =
                new DatabaseLoaderFactory().getLoader(DatabaseLoaderType.TEST);
        userModel = new UserModel(loader);
        notificationModel = new NotificationModel(loader);
    }

    @Test
    public void isSendingNotificationToUser() throws Exception {
        // declarations:
        NotificationEntity notification = new NotificationEntity("Test", Boolean.FALSE);
        UserEntity user = new UserEntity("username", "password");
        // preconditions:
        userModel.add(user);
        // when:
        notificationModel.sendNotificationTo(user, notification);
        // then:
        Assert.assertTrue(userModel.getElementById(user.getId())
                .getNotifications().stream()
                .anyMatch(item -> item.getText().equals(notification.getText())));
    }

    @Test
    public void isSendingNotificationToUsers() throws Exception {
        // declarations:
        NotificationEntity notification = new NotificationEntity("Test", Boolean.FALSE);
        UserEntity first = new UserEntity("username", "password");
        UserEntity second = new UserEntity("username", "password");
        List<UserEntity> list = asList(first, second);
        // preconditions:
        userModel.add(first);
        userModel.add(second);
        // when:
        notificationModel.sendNotificationToUsers(list, notification);
        // than:
        Assert.assertTrue(userModel.getElementById(first.getId())
                .getNotifications().stream()
                .anyMatch(item -> item.getText().equals(notification.getText())));
    }
}
