package domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;


public class SectionTest {
    Section s;
    Date dateStart,dateEnd ;
    @Before
    public void setUp() throws Exception {
        String str_date="13-03-2017";
        String str_date2="14-03-2017";
        DateFormat formatter ;
        formatter = new SimpleDateFormat("dd-MM-yyyy");
        dateStart = formatter.parse(str_date);
        dateEnd = formatter.parse(str_date2);
        s=new Section(0,"mate",dateStart,dateEnd,"cluj","iss",1,2);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getName() throws Exception {
        assertTrue(s.getName().equals("mate"));
    }

    @Test
    public void getStartDate() throws Exception {
        assertTrue(s.getStartDate().equals(dateStart));
    }

    @Test
    public void getEndDate() throws Exception {
        assertTrue(s.getEndDate().equals(dateEnd));
    }

    @Test
    public void getLocation() throws Exception {
        assertTrue(s.getLocation().equals("cluj"));
    }

    @Test
    public void getBio() throws Exception {
        assertTrue(s.getBio().equals("iss"));
    }

    @Test
    public void getSeats() throws Exception {
        assertTrue(s.getSeats().equals(1));
    }

    @Test
    public void getIdConference() throws Exception {
        assertTrue(s.getIdConference().equals(2));
    }

}