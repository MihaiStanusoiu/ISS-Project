package service;

import domain.Idable;
import transfarable.IdableTransfer;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.List;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface Service<TransferT extends IdableTransfer<Id>, Id extends Serializable,
        EntityT extends Idable<Id>> extends ServiceInterface {

    Id add(TransferT element) throws RemoteException;

    void update(TransferT element, TransferT with) throws RemoteException;

    TransferT delete(TransferT element) throws RemoteException;

    TransferT getElementById(Id id) throws RemoteException;

    List<TransferT> getAll() throws RemoteException;

}
