package domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NotificationTest {
    private Notification notif;

    @Before
    public void setUp() throws Exception {
        notif = new Notification(0,"due date today",false,9);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getText() throws Exception {
        assertTrue(notif.getId().equals(0));
        assertTrue(notif.getText().equals("due date today"));
    }

    @Test
    public void getPayment() throws Exception {
        assertTrue(notif.getPayment().equals(false));
    }

    @Test
    public void getIdUser() throws Exception {
        assertTrue(notif.getIdUser().equals(9));
    }

    @Test
    public void setPayment() throws Exception {
        notif.setPayment(true);
        assertTrue(notif.getPayment().equals(true));
    }

}