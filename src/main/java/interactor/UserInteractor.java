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
  public boolean insert(User obj) {
    return false;
  }

  @Override
  public boolean delete(User obj) {
    return false;
  }

  @Override
  public boolean update(User obj) {
    return false;
  }

  @Override
  public List<User> selectAll() {
    return null;
  }

  @Override
  public User selectById(Integer id) {
    return null;
  }
}
