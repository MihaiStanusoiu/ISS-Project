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
  public Integer insert(Tag obj) throws Exception {
    return null;
  }

  @Override
  public Tag delete(Tag obj) throws Exception {
    return null;
  }

  @Override
  public void update(Tag obj) throws Exception {
  }

  @Override
  public List<Tag> selectAll() {
    return null;
  }

  @Override
  public Tag selectById(Integer id) throws Exception {
    return null;
  }
}