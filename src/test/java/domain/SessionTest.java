package domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;


public class SessionTest {

    private Session session;
    private Date dateStart, dateEnd ;

    @Before
    public void setUp() throws Exception {
        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        dateStart = formatter.parse("13-03-2017");
        dateEnd = formatter.parse("14-03-2017");
        session = new Session(0,"mate", dateStart,
                dateEnd,"New York","iss",1,2);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getName() throws Exception {
        assertTrue(session.getName().equals("mate"));
    }

    @Test
    public void getStartDate() throws Exception {
        assertTrue(session.getStartDate().equals(dateStart));
    }

    @Test
    public void getEndDate() throws Exception {
        assertTrue(session.getEndDate().equals(dateEnd));
    }

    @Test
    public void getLocation() throws Exception {
        assertTrue(session.getLocation().equals("New York"));
    }

    @Test
    public void getBio() throws Exception {
        assertTrue(session.getBio().equals("iss"));
    }

    @Test
    public void getSeats() throws Exception {
        assertTrue(session.getSeats().equals(1));
    }

    @Test
    public void getIdConference() throws Exception {
        assertTrue(session.getIdConference().equals(2));
    }

}