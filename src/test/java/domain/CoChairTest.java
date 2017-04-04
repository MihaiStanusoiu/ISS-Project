package domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CoChairTest {

    private CoChair coChair;

    @Before
    public void setUp() throws Exception {
        coChair = new CoChair(1, 1, 1);
    }

    @Test
    public void getType() throws Exception {
        assertTrue(coChair.getType().equals(UserType.CO_CHAIR));
    }

    @Test
    public void getPermissions() throws Exception {
        assertTrue(coChair.getPermissions().contains(Permission.UPDATE_CONFERENCE));
    }

}