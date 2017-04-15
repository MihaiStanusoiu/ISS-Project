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
  public Integer insert(SessionChair obj) throws Exception {
    return null;
  }

  @Override
  public SessionChair delete(SessionChair obj) throws Exception {
    return null;
  }

  @Override
  public void update(SessionChair objInit, SessionChair objUpd) throws Exception {
  }

  @Override
  public List<SessionChair> selectAll() {
    return null;
  }

  @Override
  public SessionChair selectById(Integer id) throws Exception {
    return null;
  }
}
