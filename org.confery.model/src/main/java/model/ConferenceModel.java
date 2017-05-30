package model;

import database.DatabaseLoaderInterface;
import domain.ConferenceEntity;
import domain.EditionEntity;
import exception.ModelException;
import exception.SystemException;
import protocol.ConferenceProtocol;
import repository.RepositoryEntity;
import repository.RepositoryInterface;

import java.util.HashSet;

import static utils.Conditional.basedOn;
import static utils.Try.runMethod;
import static utils.Try.runFunction;

/**
 * Created by Mike on 5/29/2017.
 */
public class ConferenceModel extends Model<ConferenceEntity, Integer> implements ConferenceProtocol {

    private RepositoryInterface<EditionEntity, Integer> repositoryEdition;

    public ConferenceModel(DatabaseLoaderInterface loader) {
        super(ConferenceEntity.class, loader);
        repositoryEdition = new RepositoryEntity<>(EditionEntity.class, loader);
    }

    @Override
    public ConferenceEntity addEditionTo(ConferenceEntity conference, EditionEntity edition) throws SystemException {
        basedOn(conference.getEditions().stream().noneMatch(item -> item.getId().equals(edition.getId())))
                .orThrow(new ModelException("The edition is already part of the conference"));
//        EditionEntity oldEdition;
//        ConferenceEntity oldConference;
//        oldConference =  runFunction(repository::getElementById, conference.getId()).getElement();
//        oldEdition = runFunction(repositoryEdition::getElementById, edition.getId()).getElement();
        edition.setConference(conference);
//        conference.getEditions().add(edition);
        runMethod(repositoryEdition::add, edition);
//        runMethod(repository::update, oldConference, conference);
        return conference;
    }
}
