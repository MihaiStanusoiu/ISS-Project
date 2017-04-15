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
  public Integer insert(Topic obj) throws Exception {
    return null;
  }

  @Override
  public Topic delete(Topic obj) throws Exception {
    return null;
  }

  @Override
  public void update(Topic objInit, Topic objUpd) throws Exception {
  }

  @Override
  public List<Topic> selectAll() {
    return null;
  }

  @Override
  public Topic selectById(Integer id) throws Exception {
    return null;
  }
}
