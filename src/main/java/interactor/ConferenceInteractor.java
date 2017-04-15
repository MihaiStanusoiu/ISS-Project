package interactor;

import domain.Conference;
import java.util.List;

/**
 * Name:             ConferenceInteractor
 * Effect:           Class for Conferenceinteractor
 * Date:             4/15/2017
 * Tested:           False
 *
 * @author           {Teodorescu Vlad}
 * @version          1.0
 */
public class ConferenceInteractor implements InteractorInterface<Conference>{

  @Override
  public Integer insert(Conference obj) throws Exception {
    return null;
  }

  @Override
  public Conference delete(Conference obj) throws Exception {
    return null;
  }

  @Override
  public void update(Conference obj) throws Exception {
  }

  @Override
  public List<Conference> selectAll() {
    return null;
  }

  @Override
  public Conference selectById(Integer id) throws Exception {
    return null;
  }
}
