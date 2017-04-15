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
  public Integer insert(Review obj) throws Exception {
    return null;
  }

  @Override
  public Review delete(Review obj) throws Exception {
    return null;
  }

  @Override
  public void update(Review obj) throws Exception {
  }

  @Override
  public List<Review> selectAll() {
    return null;
  }

  @Override
  public Review selectById(Integer id) throws Exception {
    return null;
  }
}
