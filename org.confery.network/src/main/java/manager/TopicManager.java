package manager;

import domain.TopicEntity;
import protocol.TopicProtocol;
import service.TopicService;

import java.rmi.RemoteException;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class TopicManager extends GenericManager<TopicEntity, Integer> implements TopicService {

    public TopicManager(TopicProtocol model) throws RemoteException {
        super(model);
    }

}
