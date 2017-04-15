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
  public Integer insert(Session obj) throws Exception {
    return null;
  }

  @Override
  public Session delete(Session obj) throws Exception {
    return null;
  }

  @Override
  public void update(Session obj) throws Exception {
  }

  @Override
  public List<Session> selectAll() {
    return null;
  }

  @Override
  public Session selectById(Integer id) throws Exception {
    return null;
  }
}
