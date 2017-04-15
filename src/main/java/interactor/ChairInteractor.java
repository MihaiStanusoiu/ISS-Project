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
  public boolean insert(Chair obj) {
    return false;
  }

  @Override
  public boolean delete(Chair obj) {
    return false;
  }

  @Override
  public boolean update(Chair obj) {
    return false;
  }

  @Override
  public List<Chair> selectAll() {
    return null;
  }

  @Override
  public Chair selectById(Integer id) {
    return null;
  }
}
