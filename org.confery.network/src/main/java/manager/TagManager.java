package manager;

import domain.TagEntity;
import domain.TagEntity;
import domain.UserEntity;
import exception.SystemException;
import protocol.ModelInterface;
import protocol.TagProtocol;
import service.TagService;
import transferable.Tag;
import transferable.User;
import translator.TagTranslator;
import translator.UserTranslator;
import utils.Try;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class TagManager implements TagService {

    protected ModelInterface<TagEntity, Integer> model;
    protected UserEntity active;
    private Function<SystemException, RemoteException> thrower;

    public TagManager(ModelInterface<TagEntity, Integer> model) throws RemoteException {
        this.model = model;
        thrower = exception -> new RemoteException(exception != null ? exception.getMessage() : null);
    }

    @Override
    public void activeUser(User user) throws RemoteException {
        UserEntity userEntity = UserTranslator.translate(user);
        active = userEntity;
    }

    @Override
    public Integer add(Tag element) throws RemoteException {
        return Try.runFunction(model::add, TagTranslator.translate(element)).orThrow(thrower);
    }

    @Override
    public Tag update(Tag element, Tag with) throws RemoteException {
        Try.runMethod(
                model::update,
                TagTranslator.translate(element),
                TagTranslator.translate(with)
        ).orThrow(thrower);
        return with;
    }

    @Override
    public Tag delete(Tag element) throws RemoteException {
        return TagTranslator.translate(
                Try.runFunction(model::delete, TagTranslator.translate(element))
                        .orThrow(thrower)
        );
    }

    @Override
    public Tag getElementById(Integer id) throws RemoteException {
        return TagTranslator.translate(
                Try.runFunction(model::getElementById, id)
                        .orThrow(thrower)
        );
    }

    @Override
    public List<Tag> getAll() throws RemoteException {
        List<Tag> transferableTags = new ArrayList<>();
        List<TagEntity> Tags = model.getAll();

        for (TagEntity TagEntity : Tags) {
            transferableTags.add(TagTranslator.translate(TagEntity));
        }

        return transferableTags;
    }

}
