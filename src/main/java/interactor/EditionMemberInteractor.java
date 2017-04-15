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
  public boolean insert(EditionMember obj) {
    return false;
  }

  @Override
  public boolean delete(EditionMember obj) {
    return false;
  }

  @Override
  public boolean update(EditionMember obj) {
    return false;
  }

  @Override
  public List<EditionMember> selectAll() {
    return null;
  }

  @Override
  public EditionMember selectById(Integer id) {
    return null;
  }
}
