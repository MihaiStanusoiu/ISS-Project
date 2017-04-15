package interactor;

import domain.EditionMember;
import java.util.List;

/**
 * Name:             EditionMemberInteractor
 * Effect:           Class for EditionMemberInteractor
 * Date:             4/15/2017
 * Tested:           False
 *
 * @author           {Teodorescu Vlad}
 * @version          1.0
 */
public class EditionMemberInteractor implements InteractorInterface<EditionMember>{

  @Override
  public Integer insert(EditionMember obj) throws Exception {
    return null;
  }

  @Override
  public EditionMember delete(EditionMember obj) throws Exception {
    return null;
  }

  @Override
  public void update(EditionMember obj) throws Exception {
  }

  @Override
  public List<EditionMember> selectAll() {
    return null;
  }

  @Override
  public EditionMember selectById(Integer id) throws Exception {
    return null;
  }
}
