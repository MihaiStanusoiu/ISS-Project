package protocol;

import domain.ConferenceEntity;
import domain.EditionEntity;
import domain.UserEntity;
import exception.SystemException;

import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by Mike on 5/29/2017.
 */

public interface ConferenceProtocol extends ModelInterface<ConferenceEntity, Integer> {

    ConferenceEntity addEditionTo(ConferenceEntity conference, EditionEntity edition) throws SystemException;

    ConferenceEntity removeEditionFrom(ConferenceEntity conference, EditionEntity edition) throws SystemException;

    public List<ConferenceEntity> getConferencesOf(UserEntity user) throws SystemException;
}
