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
  public Integer insert(SessionMember obj) throws Exception {
    return null;
  }

  @Override
  public SessionMember delete(SessionMember obj) throws Exception {
    return null;
  }

  @Override
  public void update(SessionMember objInit, SessionMember objUpd) throws Exception {
  }

  @Override
  public List<SessionMember> selectAll() {
    return null;
  }

  @Override
  public SessionMember selectById(Integer id) throws Exception {
    return null;
  }
}
