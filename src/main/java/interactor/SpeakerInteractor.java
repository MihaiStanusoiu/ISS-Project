package interactor;

import domain.Speaker;
import java.util.List;

/**
 * Name:             SpeakerInteractor
 * Effect:           Class for SpeakerInteractor
 * Date:             4/15/2017
 * Tested:           False
 *
 * @author           {Teodorescu Vlad}
 * @version          1.0
 */
public class SpeakerInteractor implements InteractorInterface<Speaker>{

  @Override
  public Integer insert(Speaker obj) throws Exception {
    return null;
  }

  @Override
  public Speaker delete(Speaker obj) throws Exception {
    return null;
  }

  @Override
  public void update(Speaker objInit, Speaker objUpd) throws Exception {
  }

  @Override
  public List<Speaker> selectAll() {
    return null;
  }

  @Override
  public Speaker selectById(Integer id) throws Exception {
    return null;
  }
}
