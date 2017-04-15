package interactor;

import domain.SessionChair;
import java.util.List;

/**
 * Name:             SessionChairInteractor
 * Effect:           Class for SessionChairInteractor
 * Date:             4/15/2017
 * Tested:           False
 *
 * @author           {Teodorescu Vlad}
 * @version           1.0
 */
public class SessionChairInteractor implements InteractorInterface<SessionChair>{

  @Override
  public boolean insert(SessionChair obj) {
    return false;
  }

  @Override
  public boolean delete(SessionChair obj) {
    return false;
  }

  @Override
  public boolean update(SessionChair obj) {
    return false;
  }

  @Override
  public List<SessionChair> selectAll() {
    return null;
  }

  @Override
  public SessionChair selectById(Integer id) {
    return null;
  }
}
