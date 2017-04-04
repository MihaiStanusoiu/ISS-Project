package domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ListenerTest {

    private Listener listener;

    @Before
    public void setUp() throws Exception {
        listener = new Listener(1, 1, 1);
    }

    @Test
    public void getType() throws Exception {
        assertTrue(listener.getType() == UserType.LISTENER);
    }

    @Test
    public void getPermissions() throws Exception {
        assertTrue(listener.getPermissions().contains(Permission.CHOOSE_SECTION));
    }

}