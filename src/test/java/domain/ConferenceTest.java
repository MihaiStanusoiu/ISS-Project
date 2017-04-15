package domain;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ConferenceTest {
  private Conference conference;
  private String name;
  private String acronym;

  @Before
  public void setUp() throws Exception {
    name = "conference";
    acronym = "acronym";
    conference = new Conference(0,name,acronym);
  }

  @After
  public void tearDown() throws Exception {

  }

  @Test
  public void getName() throws Exception {
      assertTrue(this.conference.getName().equals(name));
  }

  @Test
  public void getAcronym() throws Exception {
      assertTrue(this.conference.getAcronym().equals(acronym));
  }

}