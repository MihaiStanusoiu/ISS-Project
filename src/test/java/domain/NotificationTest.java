package domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NotificationTest {

    private Notification notification;

    @Before
    public void setUp() throws Exception {
        notification = new Notification(0,"due date today",false,9);
    }

    @Test
    public void getText() throws Exception {
        assertTrue(notification.getId().equals(0));
        assertTrue(notification.getText().equals("due date today"));
    }

    @Test
    public void getPayment() throws Exception {
        assertTrue(notification.getPayment().equals(false));
    }

    @Test
    public void getIdUser() throws Exception {
        assertTrue(notification.getIdUser().equals(9));
    }

    @Test
    public void setPayment() throws Exception {
        notification.setPayment(true);
        assertTrue(notification.getPayment().equals(true));
    }

}