package interactor;

import domain.Topic;
import java.util.List;

/**
 * Name:             TopicInteractor
 * Effect:           Class for TopicInteractor
 * Date:             4/15/2017
 * Tested:           False
 *
 * @author           {Teodorescu Vlad}
 * @version          1.0
 */
public class TopicInteractor implements InteractorInterface<Topic> {

  @Override
  public boolean insert(Topic obj) {
    return false;
  }

  @Override
  public boolean delete(Topic obj) {
    return false;
  }

  @Override
  public boolean update(Topic obj) {
    return false;
  }

  @Override
  public List<Topic> selectAll() {
    return null;
  }

  @Override
  public Topic selectById(Integer id) {
    return null;
  }
}
