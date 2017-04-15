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
  public boolean insert(Notification obj) {
    return false;
  }

  @Override
  public boolean delete(Notification obj) {
    return false;
  }

  @Override
  public boolean update(Notification obj) {
    return false;
  }

  @Override
  public List<Notification> selectAll() {
    return null;
  }

  @Override
  public Notification selectById(Integer id) {
    return null;
  }
}
