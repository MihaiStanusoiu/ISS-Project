package model;

import database.DatabaseLoaderInterface;
import domain.ConferenceEntity;
import domain.EditionEntity;
import domain.UserEntity;
import exception.ModelException;
import exception.SystemException;
import protocol.ConferenceProtocol;
import repository.RepositoryEntity;
import repository.RepositoryInterface;

import java.util.List;
import java.util.stream.Collectors;
import static utils.Conditional.basedOn;

/**
 * Created by Mike on 5/29/2017.
 */

public class ConferenceModel
        extends Model<ConferenceEntity, Integer>
        implements ConferenceProtocol {

    private RepositoryInterface<EditionEntity, Integer> repositoryEdition;

    public ConferenceModel(DatabaseLoaderInterface loader) {
        super(ConferenceEntity.class, loader);
        repositoryEdition = new RepositoryEntity<>(EditionEntity.class, loader);
    }

    @Override
    public EditionEntity addEditionTo(ConferenceEntity conference, EditionEntity edition) throws SystemException {
        basedOn(conference.getEditions().stream().noneMatch(item -> item.getId().equals(edition.getId())))
                .orThrow(new ModelException("The edition is already part of the conference"));
        edition.setConference(conference);
        repositoryEdition.add(edition);
        return repositoryEdition.getElementById(edition.getId());
    }

    @Override
    public ConferenceEntity removeEditionFrom(ConferenceEntity conference, EditionEntity edition) throws SystemException {
        basedOn(conference.getEditions().stream().anyMatch(item -> item.getId().equals(edition.getId())))
                .orThrow(new ModelException("The edition is not a part of the conference"));
        repositoryEdition.delete(edition.getId());
        return getElementById(conference.getId());
    }

    @Override
    public List<ConferenceEntity> getConferencesOf(UserEntity user) throws SystemException {
        return getAll().stream()
                .filter(conference -> conference.getLatestEdition().getChair().getId().equals(user.getId()))
                .collect(Collectors.toList());
    }

    @Override public UserEntity getChairOf(ConferenceEntity conference) throws SystemException {
        return conference.getLatestEdition().getChair();
    }
}
