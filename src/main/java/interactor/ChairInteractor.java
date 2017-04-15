package interactor;

import domain.Chair;
import java.util.List;

/**
 * Name:             ChairInteractor
 * Effect:           Class for ChairInteractor
 * Date:             4/15/2017
 * Tested:           False
 *
 * @author           {Teodorescu Vlad}
 * @version          1.0
 */
public class ChairInteractor implements InteractorInterface<Chair>{

  @Override
  public Integer insert(Chair obj) throws Exception {
    return null;
  }

  @Override
  public Chair delete(Chair obj) throws Exception {
    return null;
  }

  @Override
  public void update(Chair obj) throws Exception {
}

  @Override
  public List<Chair> selectAll() {
    return null;
  }

  @Override
  public Chair selectById(Integer id) throws Exception {
    return null;
  }
}
