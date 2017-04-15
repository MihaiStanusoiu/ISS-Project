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
  public Integer insert(PCMember obj) throws Exception {
    return null;
  }

  @Override
  public PCMember delete(PCMember obj) throws Exception {
    return null;
  }

  @Override
  public void update(PCMember obj) throws Exception {
  }

  @Override
  public List<PCMember> selectAll() {
    return null;
  }

  @Override
  public PCMember selectById(Integer id) throws Exception {
    return null;
  }
}
