package model;

import database.DatabaseLoaderInterface;
import domain.*;
import exception.ModelException;
import exception.SystemException;
import protocol.UserProtocol;
import repository.RepositoryEntity;
import repository.RepositoryInterface;

import static utils.Conditional.basedOn;

/**
 * Tested: True
 *
 * @author Alexandru Stoica
 * @version 1.0
 */


public class UserModel extends Model<UserEntity, Integer> implements UserProtocol {

    private RepositoryInterface<ConfigurationEditionMemberEntity, Integer> repositoryEditionConfiguration;
    private RepositoryInterface<ConfigurationSessionMemberEntity, Integer> repositorySessionConfiguration;
    private RepositoryInterface<EditionMemberEntity, Integer> repositoryEditionMember;
    private RepositoryInterface<SessionMemberEntity, Integer> repositorySessionMember;
    private RepositoryInterface<SessionEntity, Integer> repositorySession;
    private RepositoryInterface<NotificationEntity, Integer> repositoryNotification;
    private RepositoryInterface<SubmissionEntity, Integer> repositorySubmission;
    private RepositoryInterface<AuthorSubmissionEntity, Integer> repositoryAuthor;

    public UserModel(DatabaseLoaderInterface loader) {
        super(UserEntity.class, loader);
        repositoryEditionMember= new RepositoryEntity<>(EditionMemberEntity.class, loader);
        repositorySessionMember = new RepositoryEntity<>(SessionMemberEntity.class, loader);
        repositorySession = new RepositoryEntity<>(SessionEntity.class, loader);
        repositorySubmission = new RepositoryEntity<>(SubmissionEntity.class, loader);
        repositoryEditionConfiguration = new RepositoryEntity<>(ConfigurationEditionMemberEntity.class, loader);
        repositorySessionConfiguration = new RepositoryEntity<>(ConfigurationSessionMemberEntity.class, loader);
        repositoryAuthor = new RepositoryEntity<>(AuthorSubmissionEntity.class, loader);
        repositoryNotification = new RepositoryEntity<>(NotificationEntity.class, loader);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserEntity addEditionTo(UserEntity user, EditionEntity edition, MemberRole role) throws SystemException {
        basedOn(user.getEditions().stream().noneMatch(item -> item.getId().equals(edition.getId())))
                .orThrow(new ModelException("User Already Register In Edition!"));
        EditionMemberEntity member = new EditionMemberEntity(user, edition,
                new ConfigurationEditionFactory(repositoryEditionConfiguration).getConfiguration(role));
        repositoryEditionMember.add(member);
        return getElementById(user.getId());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserEntity addSessionTo(UserEntity user, SessionEntity session, MemberRole role) throws SystemException {
        basedOn(user.getSessions().stream().noneMatch(item -> item.getId().equals(session.getId())))
                .orThrow(new ModelException("User Already Register In Session!"));
        SessionMemberEntity member = new SessionMemberEntity(session, user,
                new ConfigurationSessionFactory(repositorySessionConfiguration).getConfiguration(role));
        repositorySessionMember.add(member);
        return getElementById(user.getId());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserEntity addSubmissionTo(UserEntity user, SubmissionEntity submission, Boolean isOwner) throws SystemException {
        basedOn(user.getSubmissions().stream().noneMatch(item -> item.getId().equals(submission.getId())))
                .orThrow(new ModelException("User Already Has Submission!"));
        AuthorSubmissionEntity author = new AuthorSubmissionEntity(submission, user, isOwner);
        repositoryAuthor.add(author);
        return getElementById(user.getId());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserEntity addNotificationTo(UserEntity user, NotificationEntity notification) throws SystemException {
        basedOn(user.getNotifications().stream().noneMatch(item -> item.getId().equals(notification.getId())))
                .orThrow(new ModelException("User Already Has Notification!"));
        notification.setUser(user);
        repositoryNotification.add(notification);
        return getElementById(user.getId());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserEntity removeEditionFrom(UserEntity user, EditionEntity edition) throws SystemException {
        repositoryEditionMember.delete(user.getEditions().stream().findFirst()
                .filter(item -> item.getId().equals(edition.getId()))
                .orElseThrow(() -> new ModelException("404 Edition Not Found!")).getId());
        return getElementById(user.getId());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserEntity removeSessionFrom(UserEntity user, SessionEntity session) throws SystemException {
        repositorySessionMember.delete(user.getSessions().stream().findFirst()
                .filter(item -> item.getId().equals(session.getId()))
                .orElseThrow(() -> new ModelException("404 Session Not Found!")).getId());
        return getElementById(user.getId());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserEntity removeSubmissionFrom(UserEntity user, SubmissionEntity submission) throws SystemException {
        repositoryAuthor.delete(user.getSubmissions().stream().findFirst()
                .filter(item -> item.getId().equals(submission.getId()))
                .orElseThrow(() -> new ModelException("404 Submission Not Found!")).getId());
        return getElementById(user.getId());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserEntity removeNotificationFrom(UserEntity user, NotificationEntity notification) throws SystemException {
        repositoryNotification.delete(user.getNotifications().stream().findFirst()
                .filter(item -> item.getId().equals(notification.getId()))
                .orElseThrow(() -> new ModelException("404 Notification Not Found!")).getId());
        return getElementById(user.getId());
    }
}
