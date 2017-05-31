package manager;

import checker.TagPermissionChecker;
import domain.TagEntity;
import protocol.TagProtocol;
import service.TagService;
import transfarable.Tag;
import translator.TagTranslator;

import java.rmi.RemoteException;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class TagManager extends GenericManager<Tag, Integer, TagEntity> implements TagService {

    public TagManager(TagProtocol model) throws RemoteException {
        super(model);
        this.translator = new TagTranslator();
        this.checker = new TagPermissionChecker();
    }
}
