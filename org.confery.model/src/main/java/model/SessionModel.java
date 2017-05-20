package model;

import database.DatabaseLoaderInterface;
import domain.*;
import exception.ModelException;
import exception.SystemException;
import protocol.SessionProtocol;
import repository.RepositoryEntity;
import repository.RepositoryInterface;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Tested: True
 *
 * @author Alexandru Stoica
 * @version 1.0
 */

public class SessionModel extends Model<SessionEntity, Integer> implements SessionProtocol {

    private RepositoryInterface<SessionMemberEntity, Integer> repositoryMember;
    private RepositoryInterface<ConfigurationSessionMemberEntity, Integer> repositoryConfiguration;

    public SessionModel(DatabaseLoaderInterface loader) {
        super(SessionEntity.class, loader);
        repositoryMember = new RepositoryEntity<>(SessionMemberEntity.class, loader);
        repositoryConfiguration = new RepositoryEntity<>(ConfigurationSessionMemberEntity.class, loader);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addMemberTo(SessionEntity session, UserEntity user, MemberRole role) throws SystemException {
        SessionMemberEntity member = new SessionMemberEntity(session, user,
                new ConfigurationSessionFactory(repositoryConfiguration).getConfiguration(role));
        repositoryMember.add(member);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UserEntity> getAllSpeakers(SessionEntity session) throws SystemException {
        return repositoryMember.getAll().stream()
                .filter(member -> member.getSession().equals(session) &&
                        member.getConfiguration().getSpeaker().equals(Boolean.TRUE))
                .map(SessionMemberEntity::getUser)
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UserEntity> getAllListeners(SessionEntity session) throws SystemException {
        return repositoryMember.getAll().stream()
                .filter(member -> member.getSession().equals(session) &&
                        member.getConfiguration().getListener().equals(Boolean.TRUE))
                .map(SessionMemberEntity::getUser)
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserEntity getChair(SessionEntity session) throws SystemException {
        return repositoryMember.getAll().stream()
                .filter(member -> member.getSession().equals(session) &&
                        member.getConfiguration().getChair().equals(Boolean.TRUE))
                .map(SessionMemberEntity::getUser).findFirst()
                .orElseThrow(() -> new ModelException("404! Member Not Found!"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserEntity removeMemberFrom(SessionEntity session, UserEntity user) throws SystemException {
        return repositoryMember.delete(repositoryMember.getAll().stream()
                .filter(member -> member.getSession().equals(session) && member.getUser().equals(user))
                .findFirst().orElseThrow(() -> new ModelException("404! Member Not Found!"))
                .getId()).getUser();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void changeMemberRoleIn(SessionEntity session, UserEntity user, MemberRole role) throws SystemException {
        repositoryMember.update(repositoryMember.getAll().stream()
                        .filter(member -> member.getSession().equals(session) && member.getUser().equals(user))
                        .findFirst().orElseThrow(() -> new ModelException("404! Member Not Found!")),
                new SessionMemberEntity(session, user, new ConfigurationSessionFactory(repositoryConfiguration)
                        .getConfiguration(role)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void changeChairIn(SessionEntity session, UserEntity user) throws SystemException {
        repositoryMember.update(repositoryMember.getAll().stream()
                        .filter(member -> member.getSession().equals(session) &&
                                member.getConfiguration().getChair().equals(Boolean.TRUE))
                        .findFirst().orElseThrow(() -> new ModelException("404! Member Not Found!")),
                new SessionMemberEntity(session, user, new ConfigurationSessionFactory(repositoryConfiguration)
                        .getConfiguration(MemberRole.SESSION_CHAIR)));
    }
}
