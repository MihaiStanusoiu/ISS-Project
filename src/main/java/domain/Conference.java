package domain;

/**
 * Name:             Conference
 * Effect:           Class for conference
 * Date:             4/15/2017
 * Tested:           True
 *
 * @author           {Teodorescu Vlad}
 * @version          1.0
 */
public class Conference extends Idable<Integer> {
  private String name;
  private String acronym;

  public Conference(Integer id, String name, String acronym) {
    this.id = id;
    this.name = name;
    this.acronym = acronym;
  }

  public Conference(String name, String acronym) {
    this.id = 0;
    this.name = name;
    this.acronym = acronym;
  }

  /**
   * Effect: Return the name of the conference.
   * @return [String] : returns the name.
   */
  public String getName() {
    return name;
  }

  /**
   * Effect: Return the acronym for the conference.
   * @return [String] : returns the acronym.
   */
  public String getAcronym() {
    return acronym;
  }
}
