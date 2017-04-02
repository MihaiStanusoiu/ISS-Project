package model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class IdableTest {
    private HasId<Integer> ob;

    @Before
    public void setUp() throws Exception {
        ob = new Idable<>();
        ob.setId(12);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void isGettingId() throws Exception {
        assertTrue(ob.getId().equals(12));
    }

    @Test
    public void isSettingId() throws Exception {
        ob.setId(11);
        assertTrue(ob.getId().equals(11));
    }

}