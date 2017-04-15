package interactor;

import domain.Notification;
import java.util.List;

/**
 * Name:             NotificationInteractor
 * Effect:           Class for NotificationInteractor
 * Date:             4/15/2017
 * Tested:           False
 *
 * @author           {Teodorescu Vlad}
 * @version          1.0
 */
public class NotificationInteractor implements InteractorInterface<Notification>{

  @Override
  public Integer insert(Notification obj) throws Exception {
    return null;
  }

  @Override
  public Notification delete(Notification obj) throws Exception {
    return null;
  }

  @Override
  public void update(Notification obj) throws Exception {
  }

  @Override
  public List<Notification> selectAll() {
    return null;
  }

  @Override
  public Notification selectById(Integer id) throws Exception {
    return null;
  }
}
