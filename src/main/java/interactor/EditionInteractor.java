package interactor;

import domain.Edition;
import java.util.List;

/**
 * Name:             EditionInteractor
 * Effect:           Class for EditionInteractor
 * Date:             4/15/2017
 * Tested:           False
 *
 * @author           {Teodorescu Vlad}
 * @version          1.0
 */
public class EditionInteractor implements InteractorInterface<Edition>{

  @Override
  public Integer insert(Edition obj) throws Exception {
    return null;
  }

  @Override
  public Edition delete(Edition obj) throws Exception {
    return null;
  }

  @Override
  public void update(Edition objInit, Edition objUpd) throws Exception {
  }

  @Override
  public List<Edition> selectAll() {
    return null;
  }

  @Override
  public Edition selectById(Integer id) throws Exception {
    return null;
  }
}
