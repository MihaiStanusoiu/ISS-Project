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
  public Integer insert(CoChair obj) throws Exception {
    return null;
  }

  @Override
  public CoChair delete(CoChair obj) throws Exception {
    return null;
  }

  @Override
  public void update(CoChair objInit, CoChair objUpd) throws Exception {
  }

  @Override
  public List<CoChair> selectAll() {
    return null;
  }

  @Override
  public CoChair selectById(Integer id) throws Exception {
    return null;
  }
}
