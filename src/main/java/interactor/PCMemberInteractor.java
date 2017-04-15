package interactor;

import domain.PCMember;
import java.util.List;

/**
 * Name:             PCMemberInteractor
 * Effect:           Class for PCMemberInteractor
 * Date:             4/15/2017
 * Tested:           False
 *
 * @author           {Teodorescu Vlad}
 * @version          1.0
 */
public class PCMemberInteractor implements InteractorInterface <PCMember>{

  @Override
  public boolean insert(PCMember obj) {
    return false;
  }

  @Override
  public boolean delete(PCMember obj) {
    return false;
  }

  @Override
  public boolean update(PCMember obj) {
    return false;
  }

  @Override
  public List<PCMember> selectAll() {
    return null;
  }

  @Override
  public PCMember selectById(Integer id) {
    return null;
  }
}
