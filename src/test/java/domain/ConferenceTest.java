package domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;


public class ConferenceTest {
    private Conference c;
    private Date start,end,paper,abstractt,bidding,evaluation;

    @Before
    public void setUp() throws Exception {
        String s_start="13-03-2017";
        String s_end="14-03-2017";
        String s_paper="15-03-2017";
        String s_abstract="16-03-2017";
        String s_bidding="17-03-2017";
        String s_evaluation="18-03-2017";
        DateFormat formatter ;
        formatter = new SimpleDateFormat("dd-MM-yyyy");
        start = formatter.parse(s_start);
        end = formatter.parse(s_end);
        abstractt = formatter.parse(s_end);
        paper = formatter.parse(s_end);
        bidding = formatter.parse(s_end);
        evaluation = formatter.parse(s_end);
        c=new Conference(0,"ioi","acro",start,end,"cluj","bio",abstractt,paper,evaluation,bidding);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getName() throws Exception {
        assertTrue(c.getName().equals("ioi"));
    }

    @Test
    public void getAcronym() throws Exception {
        assertTrue(c.getAcronym().equals("acro"));
    }

    @Test
    public void getStartDate() throws Exception {
        assertTrue(c.getStartDate().equals(start));
    }

    @Test
    public void getEndDate() throws Exception {
        assertTrue(c.getEndDate().equals(end));
    }

    @Test
    public void getLocation() throws Exception {
        assertTrue(c.getLocation().equals("cluj"));
    }

    @Test
    public void getBio() throws Exception {
        assertTrue(c.getBio().equals("bio"));
    }

    @Test
    public void getAbstractDeadline() throws Exception {
        assertTrue(c.getAbstractDeadline().equals(abstractt));
    }

    @Test
    public void getPaperDeadline() throws Exception {
        assertTrue(c.getPaperDeadline().equals(paper));
    }

    @Test
    public void getBiddingDeadline() throws Exception {
        assertTrue(c.getBiddingDeadline().equals(bidding));
    }

    @Test
    public void getEvaluationDeadline() throws Exception {
        assertTrue(c.getEvaluationDeadline().equals(evaluation));
    }
}