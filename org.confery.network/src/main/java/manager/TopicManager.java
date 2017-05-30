package manager;

import domain.TopicEntity;
import domain.UserEntity;
import exception.SystemException;
import protocol.ModelInterface;
import service.TopicService;
import transferable.Topic;
import transferable.User;
import translator.TopicTranslator;
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

public class TopicManager implements TopicService {

    protected ModelInterface<TopicEntity, Integer> model;
    protected UserEntity active;
    private Function<SystemException, RemoteException> thrower;

    public TopicManager(ModelInterface<TopicEntity, Integer> model) throws RemoteException {
        this.model = model;
        thrower = exception -> new RemoteException(exception != null ? exception.getMessage() : null);
    }

    @Override
    public void activeUser(User user) throws RemoteException {
        UserEntity userEntity = UserTranslator.translate(user);
        active = userEntity;
    }

    @Override
    public Integer add(Topic element) throws RemoteException {
        return Try.runFunction(model::add, TopicTranslator.translate(element)).orThrow(thrower);
    }

    @Override
    public Topic update(Topic element, Topic with) throws RemoteException {
        Try.runMethod(
                model::update,
                TopicTranslator.translate(element),
                TopicTranslator.translate(with)
        ).orThrow(thrower);
        return with;
    }

    @Override
    public Topic delete(Topic element) throws RemoteException {
        return TopicTranslator.translate(
                Try.runFunction(model::delete, TopicTranslator.translate(element))
                        .orThrow(thrower)
        );
    }

    @Override
    public Topic getElementById(Integer id) throws RemoteException {
        return TopicTranslator.translate(
                Try.runFunction(model::getElementById, id)
                        .orThrow(thrower)
        );
    }

    @Override
    public List<Topic> getAll() throws RemoteException {
        List<Topic> transferableTopics = new ArrayList<>();
        List<TopicEntity> Topics = model.getAll();

        for (TopicEntity TopicEntity : Topics) {
            transferableTopics.add(TopicTranslator.translate(TopicEntity));
        }

        return transferableTopics;
    }

}
