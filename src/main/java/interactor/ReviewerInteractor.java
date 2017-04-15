package interactor;


import domain.Reviewer;
import java.util.List;

/**
 * Name:             ReviewerInteractor
 * Effect:           Class for ReviewerInteractor
 * Date:             4/15/2017
 * Tested:           False
 *
 * @author           {Teodorescu Vlad}
 * @version          1.0
 */
public class ReviewerInteractor implements InteractorInterface<Reviewer>{

  @Override
  public boolean insert(Reviewer obj) {
    return false;
  }

  @Override
  public boolean delete(Reviewer obj) {
    return false;
  }

  @Override
  public boolean update(Reviewer obj) {
    return false;
  }

  @Override
  public List<Reviewer> selectAll() {
    return null;
  }

  @Override
  public Reviewer selectById(Integer id) {
    return null;
  }
}
