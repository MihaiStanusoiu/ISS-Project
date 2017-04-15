package interactor;

import domain.Listener;
import java.util.List;

/**
 * Name:             ListenerInteractor
 * Effect:           Class for ListenerInteractor
 * Date:             4/15/2017
 * Tested:           False
 *
 * @author           {Teodorescu Vlad}
 * @version          1.0
 */
public class ListenerInteractor implements InteractorInterface<Listener>{

  @Override
  public boolean insert(Listener obj) {
    return false;
  }

  @Override
  public boolean delete(Listener obj) {
    return false;
  }

  @Override
  public boolean update(Listener obj) {
    return false;
  }

  @Override
  public List<Listener> selectAll() {
    return null;
  }

  @Override
  public Listener selectById(Integer id) {
    return null;
  }
}
