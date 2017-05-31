package manager;

import checker.TopicPermissionChecker;
import domain.TopicEntity;
import protocol.TopicProtocol;
import service.TopicService;
import transfarable.Topic;
import translator.TopicTranslator;

import java.rmi.RemoteException;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class TopicManager extends GenericManager<Topic, Integer, TopicEntity> implements TopicService {

    public TopicManager(TopicProtocol model) throws RemoteException {
        super(model);
        super.translator = new TopicTranslator();
        super.checker = new TopicPermissionChecker();
    }
}
