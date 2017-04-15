package interactor;

import domain.SessionMember;
import java.util.List;

/**
 * Name:             SessionMemeberInteractor
 * Effect:           Class for SessionMemeberInteractor
 * Date:             4/15/2017
 * Tested:           False
 *
 * @author           {Teodorescu Vlad}
 * @version          1.0
 */
public class SessionMemberInteractor implements InteractorInterface<SessionMember>{

  @Override
  public boolean insert(SessionMember obj) {
    return false;
  }

  @Override
  public boolean delete(SessionMember obj) {
    return false;
  }

  @Override
  public boolean update(SessionMember obj) {
    return false;
  }

  @Override
  public List<SessionMember> selectAll() {
    return null;
  }

  @Override
  public SessionMember selectById(Integer id) {
    return null;
  }
}
