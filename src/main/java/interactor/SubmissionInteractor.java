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
  public boolean insert(Submission obj) {
    return false;
  }

  @Override
  public boolean delete(Submission obj) {
    return false;
  }

  @Override
  public boolean update(Submission obj) {
    return false;
  }

  @Override
  public List<Submission> selectAll() {
    return null;
  }

  @Override
  public Submission selectById(Integer id) {
    return null;
  }
}
