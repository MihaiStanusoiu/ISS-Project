package model;

import database.DatabaseLoaderInterface;
import domain.*;
import exception.ModelException;
import exception.SystemException;
import protocol.SessionProtocol;
import repository.RepositoryEntity;
import repository.RepositoryInterface;

import java.util.Objects;

import static utils.Conditional.basedOn;

/**
 * Tested: True
 *
 * @author Alexandru Stoica
 * @version 1.0
 */


public class SessionModel extends Model<SessionEntity, Integer> implements SessionProtocol {

    private RepositoryInterface<UserEntity, Integer> repositoryUser;
    private RepositoryInterface<SessionMemberEntity, Integer> repositoryMember;
    private RepositoryInterface<ConfigurationSessionMemberEntity, Integer> repositoryConfiguration;

    public SessionModel(DatabaseLoaderInterface loader) {
        super(SessionEntity.class, loader);
        repositoryMember = new RepositoryEntity<>(SessionMemberEntity.class, loader);
        repositoryUser = new RepositoryEntity<>(UserEntity.class, loader);
        repositoryConfiguration = new RepositoryEntity<>(ConfigurationSessionMemberEntity.class, loader);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SessionEntity addMemberTo(SessionEntity session, UserEntity user, MemberRole role) throws SystemException {
        basedOn(Objects.nonNull(getElementById(session.getId())))
                .orThrow(new ModelException("404 Session Not Found!"));
        basedOn(Objects.nonNull(repositoryUser.getElementById(user.getId())))
                .orThrow(new ModelException("404 User Not Found!"));
        repositoryMember.add(new SessionMemberEntity(session, user,
                new ConfigurationSessionFactory(repositoryConfiguration).getConfiguration(role)));
        return getElementById(session.getId());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SessionEntity removeMemberFrom(SessionEntity session, UserEntity user) throws SystemException {
        repositoryMember.delete(session.getMembers().stream()
                .filter(member -> member.getUser().getId().equals(user.getId()))
                .findFirst().orElseThrow(() -> new ModelException("404! Member Not Found!"))
                .getId());
        return getElementById(session.getId());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SessionEntity changeMemberRoleIn(SessionEntity session, UserEntity user, MemberRole role) throws SystemException {
        repositoryMember.update(session.getMembers().stream()
                        .filter(member -> member.getUser().getId().equals(user.getId()))
                        .findFirst().orElseThrow(() -> new ModelException("404! Member Not Found!")),
                new SessionMemberEntity(session, user,
                        new ConfigurationSessionFactory(repositoryConfiguration).getConfiguration(role)));
        return getElementById(session.getId());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SessionEntity changeChairIn(SessionEntity session, UserEntity user) throws SystemException {
        repositoryMember.update(session.getMembers().stream()
                        .filter(member -> member.getConfiguration().getChair().equals(Boolean.TRUE))
                        .findFirst().orElseThrow(() -> new ModelException("404! Member Not Found!")),
                new SessionMemberEntity(session, user,
                        new ConfigurationSessionFactory(repositoryConfiguration)
                                .getConfiguration(MemberRole.SESSION_CHAIR)));
        return getElementById(session.getId());
    }
}
