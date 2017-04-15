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
  public boolean insert(Conference obj) {
    return false;
  }

  @Override
  public boolean delete(Conference obj) {
    return false;
  }

  @Override
  public boolean update(Conference obj) {
    return false;
  }

  @Override
  public List<Conference> selectAll() {
    return null;
  }

  @Override
  public Conference selectById(Integer id) {
    return null;
  }
}
