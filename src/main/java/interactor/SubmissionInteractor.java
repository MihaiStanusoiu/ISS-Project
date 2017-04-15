package interactor;

import domain.Submission;
import java.util.List;

/**
 * Name:             SubmissionInteractor
 * Effect:           Class for SubmissionInteractor
 * Date:             4/15/2017
 * Tested:           False
 *
 * @author           {Teodorescu Vlad}
 * @version          1.0
 */
public class SubmissionInteractor implements InteractorInterface<Submission>{

  @Override
  public Integer insert(Submission obj) throws Exception {
    return null;
  }

  @Override
  public Submission delete(Submission obj) throws Exception {
    return null;
  }

  @Override
  public void update(Submission objInit, Submission objUpd) throws Exception {
  }

  @Override
  public List<Submission> selectAll() {
    return null;
  }

  @Override
  public Submission selectById(Integer id) throws Exception {
    return null;
  }
}
