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
  public Integer insert(Listener obj) throws Exception {
    return null;
  }

  @Override
  public Listener delete(Listener obj) throws Exception {
    return null;
  }

  @Override
  public void update(Listener objInit, Listener objUpd) throws Exception {
  }

  @Override
  public List<Listener> selectAll() {
    return null;
  }

  @Override
  public Listener selectById(Integer id) throws Exception {
    return null;
  }
}
