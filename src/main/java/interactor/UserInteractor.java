package interactor;

import domain.User;
import java.util.List;

/**
 * Name:             UserInteractor
 * Effect:           Class for UserInteractor
 * Date:             4/15/2017
 * Tested:           False
 *
 * @author           {Teodorescu Vlad}
 * @version           1.0
 */
public class UserInteractor implements InteractorInterface<User> {

  @Override
  public Integer insert(User obj) throws Exception {
    return null;
  }

  @Override
  public User delete(User obj) throws Exception {
    return null;
  }

  @Override
  public void update(User objInit, User objUpd) throws Exception {
  }

  @Override
  public List<User> selectAll() {
    return null;
  }

  @Override
  public User selectById(Integer id) throws Exception {
    return null;
  }
}
