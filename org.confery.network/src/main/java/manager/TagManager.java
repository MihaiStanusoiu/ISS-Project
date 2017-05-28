package manager;

import domain.TagEntity;
import protocol.TagProtocol;
import service.TagService;

import java.rmi.RemoteException;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class TagManager extends GenericManager<TagEntity, Integer> implements TagService {

    public TagManager(TagProtocol model) throws RemoteException {
        super(model);
    }

}
