package interactor;

import domain.Tag;
import java.util.List;

/**
 * Name:             TagInteractor
 * Effect:           Class for TagInteractor
 * Date:             4/15/2017
 * Tested:           False
 *
 * @author           {Teodorescu Vlad}
 * @version          1.0
 */
public class TagInteractor implements InteractorInterface<Tag>{

  @Override
  public boolean insert(Tag obj) {
    return false;
  }

  @Override
  public boolean delete(Tag obj) {
    return false;
  }

  @Override
  public boolean update(Tag obj) {
    return false;
  }

  @Override
  public List<Tag> selectAll() {
    return null;
  }

  @Override
  public Tag selectById(Integer id) {
    return null;
  }
}
