package protocol;

import domain.ConferenceEntity;
import domain.EditionEntity;
import domain.UserEntity;
import exception.SystemException;

import java.util.List;

/**
 * Created by Mike on 5/29/2017.
 */

public interface ConferenceProtocol extends ModelInterface<ConferenceEntity, Integer> {

    /**
     * Adds an edition to a conference.
     * @param conference The target conference [already in the database]
     * @param edition The target edition [already in the database]
     * @return The conference updated
     * @throws SystemException If it's unable to add the edition to the database.
     */
    ConferenceEntity addEditionTo(ConferenceEntity conference, EditionEntity edition) throws SystemException;

    /**
     * Removes an edition from conference.
     * @param conference The target conference [already in the database]
     * @param edition The target edition [already in the database]
     * @return The conference updated
     * @throws SystemException If it's unable to remove the edition.
     */
    ConferenceEntity removeEditionFrom(ConferenceEntity conference, EditionEntity edition) throws SystemException;

    /**
     * Returns all the user's conferences. [if that conference has at least an edition]
     * @param user The target user
     * @return The list of conference for that specific user.
     * @throws SystemException [Only hibernate can throw something]
     */
    List<ConferenceEntity> getConferencesOf(UserEntity user) throws SystemException;

}
