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
  public boolean insert(Edition obj) {
    return false;
  }

  @Override
  public boolean delete(Edition obj) {
    return false;
  }

  @Override
  public boolean update(Edition obj) {
    return false;
  }

  @Override
  public List<Edition> selectAll() {
    return null;
  }

  @Override
  public Edition selectById(Integer id) {
    return null;
  }
}
