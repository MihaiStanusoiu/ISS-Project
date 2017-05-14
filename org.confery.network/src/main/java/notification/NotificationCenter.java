package notification;

import service.SubscriberService;
import service.SubscriptionService;
import utils.ThrowPipe;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class NotificationCenter implements SubscriptionService {

    private ArrayList<SubscriberService> subscribers;

    public NotificationCenter() throws RemoteException {
        subscribers = new ArrayList<>();
    }

    @Override
    public void addSubscriber(SubscriberService subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public void removeSubscriber(SubscriberService subscriber) {
        subscribers.remove(subscriber);
    }

    @Override
    public void notifyAll(Notification notification) {
        subscribers.forEach(subscriberService -> ThrowPipe.wrap(() -> subscriberService.update(notification)));
    }

}
