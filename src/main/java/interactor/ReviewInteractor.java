package interactor;

import domain.Review;
import java.util.List;

/**
 * Name:             ReviewInteractor
 * Effect:           Class for ReviewInteractor
 * Date:             4/15/2017
 * Tested:           False
 *
 * @author           {Teodorescu Vlad}
 * @version          1.0
 */
public class ReviewInteractor implements InteractorInterface<Review>{

  @Override
  public boolean insert(Review obj) {
    return false;
  }

  @Override
  public boolean delete(Review obj) {
    return false;
  }

  @Override
  public boolean update(Review obj) {
    return false;
  }

  @Override
  public List<Review> selectAll() {
    return null;
  }

  @Override
  public Review selectById(Integer id) {
    return null;
  }
}
