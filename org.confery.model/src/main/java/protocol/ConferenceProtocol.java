package protocol;

import domain.ConferenceEntity;
import domain.EditionEntity;
import exception.SystemException;

/**
 * Created by Mike on 5/29/2017.
 */
public interface ConferenceProtocol extends ModelInterface<ConferenceEntity, Integer> {

    ConferenceEntity addEditionTo(ConferenceEntity conference, EditionEntity edition) throws SystemException;

}
