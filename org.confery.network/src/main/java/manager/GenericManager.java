package manager;

import checker.PermissionChecker;
import domain.Idable;
import domain.UserEntity;
import exception.SystemException;
import protocol.LoginProtocol;
import protocol.ModelInterface;
import service.Service;
import translator.GenericTranslator;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.RemoteServer;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static utils.Conditional.basedOn;
import static utils.Try.runFunction;
import static utils.Try.runMethod;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class GenericManager<TransferT, Id extends Serializable, EntityT extends Idable<Id>>
    extends UnicastRemoteObject implements Service<TransferT, Id, EntityT> {

    protected ModelInterface<EntityT, Id> model;
    protected LoginProtocol provider;
    protected PermissionChecker<EntityT> checker;
    protected Function<SystemException, RemoteException> thrower;
    protected GenericTranslator<EntityT, TransferT> translator;


    protected GenericManager(ModelInterface<EntityT, Id> model, LoginProtocol loginProtocol) throws RemoteException {
        this.model = model;
        this.provider = loginProtocol;
        thrower = exception -> new RemoteException(exception.getMessage());
    }

    @Override
    public Id add(TransferT element) throws RemoteException {
        UserEntity active = getActiveUser();
        basedOn(checker.isAllowed(active).toAdd().theObject(translator.translate(element)))
                .orThrow(new RemoteException("You don't have the required permissions to perform this action!"));
        return runFunction(model::add, translator.translate(element)).orThrow(thrower);
    }

    private UserEntity getActiveUser() throws RemoteException {
        String host = runFunction(RemoteServer::getClientHost)
                .orThrow(exception -> new RemoteException(exception.getMessage()));
        return runFunction(provider::getById, host).getElement();
    }

    @Override
    public void update(TransferT element, TransferT with) throws RemoteException {
        UserEntity active = getActiveUser();
        basedOn(checker.isAllowed(active).toUpdate().theObject(translator.translate(element)))
                .orThrow(new RemoteException("You don't have the required permissions to perform this action!"));
        runMethod(model::update, translator.translate(element), translator.translate(with)).orThrow(thrower);
    }

    @Override
    public TransferT delete(TransferT element) throws RemoteException {
        UserEntity active = getActiveUser();
        basedOn(checker.isAllowed(active).toDelete().theObject(translator.translate(element)))
                .orThrow(new RemoteException("You don't have the required permissions to perform this action!"));
        return translator.translate(runFunction(model::delete, translator.translate(element)).orThrow(thrower));
    }

    @Override
    public TransferT getElementById(Id id) throws RemoteException {
        return translator.translate(runFunction(model::getElementById, id).orThrow(thrower));
    }

    @Override
    public List<TransferT> getAll() throws RemoteException {
        System.out.print(runFunction(RemoteServer::getClientHost).orHandle(exception -> System.out.print(exception.getCause())));
        return model.getAll().stream().map(entity -> translator.translate(entity)).collect(Collectors.toList());
    }

}
