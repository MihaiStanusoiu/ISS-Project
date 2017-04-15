package interactor;

import domain.CoChair;
import java.util.List;

/**
 * Name:             CoChairInteractor
 * Effect:           Class for CoChairInteractor
 * Date:             4/15/2017
 * Tested:           False
 *
 * @author           {Teodorescu Vlad}
 * @version          1.0
 */
public class CoChairInteractor implements InteractorInterface<CoChair>{

  @Override
  public boolean insert(CoChair obj) {
    return false;
  }

  @Override
  public boolean delete(CoChair obj) {
    return false;
  }

  @Override
  public boolean update(CoChair obj) {
    return false;
  }

  @Override
  public List<CoChair> selectAll() {
    return null;
  }

  @Override
  public CoChair selectById(Integer id) {
    return null;
  }
}
