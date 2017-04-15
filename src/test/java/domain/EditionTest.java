package domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class EditionTest {

    private Edition conference;
    private Date start, end, paper, abstracts, bidding, evaluation;
    private Integer idConference;

    @Before
    public void setUp() throws Exception {
        String s_start = "13-03-2017";
        String s_end = "14-03-2017";
        String s_paper = "15-03-2017";
        String s_abstract = "16-03-2017";
        String s_bidding = "17-03-2017";
        String s_evaluation = "18-03-2017";
        DateFormat formatter ;
        Integer idConference = 1;
        formatter = new SimpleDateFormat("dd-MM-yyyy");
        start = formatter.parse(s_start);
        end = formatter.parse(s_end);
        abstracts = formatter.parse(s_end);
        paper = formatter.parse(s_end);
        bidding = formatter.parse(s_end);
        evaluation = formatter.parse(s_end);
        conference = new Edition(0, start,
                end,"New York","bio", abstracts, paper, evaluation, bidding, idConference);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getStartDate() throws Exception {
        assertTrue(conference.getStartDate().equals(start));
    }

    @Test
    public void getEndDate() throws Exception {
        assertTrue(conference.getEndDate().equals(end));
    }

    @Test
    public void getLocation() throws Exception {
        assertTrue(conference.getLocation().equals("New York"));
    }

    @Test
    public void getBio() throws Exception {
        assertTrue(conference.getBio().equals("bio"));
    }

    @Test
    public void getAbstractDeadline() throws Exception {
        assertTrue(conference.getAbstractDeadline().equals(abstracts));
    }

    @Test
    public void getPaperDeadline() throws Exception {
        assertTrue(conference.getPaperDeadline().equals(paper));
    }

    @Test
    public void getBiddingDeadline() throws Exception {
        assertTrue(conference.getBiddingDeadline().equals(bidding));
    }

    @Test
    public void getEvaluationDeadline() throws Exception {
        assertTrue(conference.getEvaluationDeadline().equals(evaluation));
    }

    @Test
    public void getIdConference() throws Exception{
        assertTrue(conference.getIdConference().equals(idConference));
    }
}