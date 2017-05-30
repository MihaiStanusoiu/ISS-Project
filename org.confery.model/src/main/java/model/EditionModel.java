package model;

import database.DatabaseLoaderInterface;
import domain.*;
import exception.ModelException;
import exception.SystemException;
import protocol.EditionProtocol;
import repository.RepositoryEntity;
import repository.RepositoryInterface;

import static utils.Conditional.basedOn;
import static utils.Try.runFunction;
import static utils.Try.runMethod;

/**
 * Tested: True
 *
 * @author Alexandru Stoica
 * @version 1.0
 */

public class EditionModel extends Model<EditionEntity, Integer> implements EditionProtocol {

    private RepositoryInterface<ConfigurationEditionMemberEntity, Integer> repositoryConfiguration;
    private RepositoryInterface<EditionMemberEntity, Integer> repositoryMember;
    private RepositoryInterface<SessionEntity, Integer> repositorySession;
    private RepositoryInterface<SubmissionEntity, Integer> repositorySubmission;
    private RepositoryInterface<UserEntity, Integer> repositoryUser;

    public EditionModel(DatabaseLoaderInterface loader) {
        super(EditionEntity.class, loader);
        repositoryConfiguration = new RepositoryEntity<>(ConfigurationEditionMemberEntity.class, loader);
        repositoryMember = new RepositoryEntity<>(EditionMemberEntity.class, loader);
        repositorySession = new RepositoryEntity<>(SessionEntity.class, loader);
        repositorySubmission = new RepositoryEntity<>(SubmissionEntity.class, loader);
        repositoryUser = new RepositoryEntity<>(UserEntity.class, loader);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EditionEntity addMemberTo(EditionEntity edition, UserEntity user, MemberRole role) throws SystemException {
        basedOn(edition.getUsers().stream().noneMatch(item -> item.getId().equals(user.getId())))
                .orThrow(new ModelException("The user is already a member of the edition!"));

//        EditionEntity oldEdition = runFunction(repository::getElementById, edition.getId()).getElement();
//        UserEntity oldUser = runFunction(repositoryUser::getElementById, user.getId()).getElement();
        EditionMemberEntity member = new EditionMemberEntity(user, edition,
                new ConfigurationEditionFactory(repositoryConfiguration).getConfiguration(role));

//        edition.getMembers().add(member);
//        user.getEditionMembers().add(member);

//        runMethod(repository::update, oldEdition, edition);
//        runMethod(repositoryUser::update, oldUser, user);
        repositoryMember.add(member);

        return getElementById(edition.getId());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EditionEntity deleteMemberOf(EditionEntity edition, UserEntity member) throws SystemException {
        repositoryMember.delete(edition.getMembers().stream().findFirst()
                .filter(item -> item.getId().equals(member.getId()))
                .orElseThrow(() -> new ModelException("404 Member Not Found!")).getId());

//        EditionEntity oldEdition = runFunction(repository::getElementById, edition.getId()).getElement();
//        UserEntity oldUser = runFunction(repositoryUser::getElementById, member.getId()).getElement();
//        edition.getMembers().removeIf(item -> item.getId().equals(member.getId()));
//        member.getEditionMembers().removeIf(item -> item.getId().equals(member.getId()));
//
//        runMethod(repository::update, oldEdition, edition);
//        runMethod(repositoryUser::update, oldUser, member);

        return getElementById(edition.getId());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EditionEntity addSessionTo(EditionEntity edition, SessionEntity session) throws SystemException {
        basedOn(edition.getSessions().stream().noneMatch(item -> item.getId().equals(session.getId())))
                .orThrow(new ModelException("The session is already a member of the edition!"));

//        EditionEntity oldEdition = runFunction(repository::getElementById, edition.getId()).getElement();

        session.setEdition(edition);
        repositorySession.add(session);
        return getElementById(edition.getId());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EditionEntity deleteSessionOf(EditionEntity edition, SessionEntity session) throws SystemException {
        repositorySession.delete(edition.getSessions().stream().findFirst()
                .filter(item -> item.getId().equals(session.getId()))
                .orElseThrow(() -> new ModelException("404 Session Not Found!")).getId());
        return getElementById(edition.getId());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EditionEntity addSubmissionTo(EditionEntity edition, SubmissionEntity submission) throws SystemException {
        basedOn(edition.getSubmissions().stream().noneMatch(item -> item.getId().equals(submission.getId())))
                .orThrow(new ModelException("The submission is already a member of the edition!"));
        submission.setEdition(edition);
        repositorySubmission.add(submission);
        return getElementById(edition.getId());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EditionEntity deleteSubmissionOf(EditionEntity edition, SubmissionEntity submission) throws SystemException {
        repositorySubmission.delete(edition.getSubmissions().stream().findFirst()
                .filter(item -> item.getId().equals(submission.getId()))
                .orElseThrow(() -> new ModelException("404 Submission Not Found!")).getId());
        return getElementById(edition.getId());

    }
}
