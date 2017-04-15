package interactor;

import domain.Session;
import java.util.List;

/**
 * Name:             SessionInteractor
 * Effect:           Class for SessionInteractor
 * Date:             4/15/2017
 * Tested:           False
 *
 * @author           {Teodorescu Vlad}
 * @version          1.0
 */
public class SessionInteractor implements InteractorInterface<Session>{

  @Override
  public boolean insert(Session obj) {
    return false;
  }

  @Override
  public boolean delete(Session obj) {
    return false;
  }

  @Override
  public boolean update(Session obj) {
    return false;
  }

  @Override
  public List<Session> selectAll() {
    return null;
  }

  @Override
  public Session selectById(Integer id) {
    return null;
  }
}
