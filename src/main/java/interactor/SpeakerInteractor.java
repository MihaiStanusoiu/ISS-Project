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
  public boolean insert(Speaker obj) {
    return false;
  }

  @Override
  public boolean delete(Speaker obj) {
    return false;
  }

  @Override
  public boolean update(Speaker obj) {
    return false;
  }

  @Override
  public List<Speaker> selectAll() {
    return null;
  }

  @Override
  public Speaker selectById(Integer id) {
    return null;
  }
}
