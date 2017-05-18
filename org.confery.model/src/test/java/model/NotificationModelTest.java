package model;

import database.DatabaseLoaderFactory;
import database.DatabaseLoaderInterface;
import database.DatabaseLoaderType;
import domain.NotificationEntity;
import domain.UserEntity;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class NotificationModelTest {

    @Test
    public void isSendingNotificationToUser() throws Exception {
        DatabaseLoaderInterface loader = new DatabaseLoaderFactory().getLoader(DatabaseLoaderType.TEST);
        UserModel userModel = new UserModel(loader);
        NotificationModel notificationModel = new NotificationModel(loader);
        UserEntity first = new UserEntity("username", "password");
        UserEntity second = new UserEntity("username", "password");
        List<UserEntity> list = new ArrayList<>();
        NotificationEntity notification = new NotificationEntity("Test", Boolean.FALSE);
        userModel.add(first);
        userModel.add(second);
        list.add(first);
        list.add(second);
        notificationModel.sendNotificationTo(second, notification);
        Assert.assertTrue(userModel.getElementById(second.getId())
                .getNotifications().stream().anyMatch(item-> item.getText().equals(notification.getText())));
        notificationModel.sendNotificationToUsers(list, notification);
        Assert.assertTrue(userModel.getElementById(first.getId())
                .getNotifications().stream().anyMatch(item-> item.getText().equals(notification.getText())));
    }

}