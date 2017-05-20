
package domain;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Tested: True
 * @author Tiron Andreea-Ecaterina & Alexandru Stoica
 * @version 1.0
 */

@Entity
@Table(name = "SESSION_MEMBER")
@SuppressWarnings("unused")
public class SessionMemberEntity implements Idable<Integer>{

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID_SESSION_MEMBER")
    private Integer id;

    @ManyToOne(targetEntity = SessionEntity.class)
    @JoinColumn(name = "ID_SESSION")
    private SessionEntity session;

    @ManyToOne(targetEntity = UserEntity.class)
    @JoinColumn(name = "ID_USER")
    private UserEntity user;

    @ManyToOne(targetEntity = ConfigurationSessionMemberEntity.class)
    @JoinColumn(name = "ID_CONFIGURATION_SESSION_MEMBER")
    private ConfigurationSessionMemberEntity configuration;

    private static final Integer DEFAULT_ID = 0;

    /**
     * @apiNote Don't user this constructor [it's for testing only]
     */
    @Deprecated
    public SessionMemberEntity() {
        this(DEFAULT_ID, null, null, null);
    }

    public SessionMemberEntity(SessionEntity session, UserEntity user,
                               ConfigurationSessionMemberEntity configuration) {
        this(DEFAULT_ID, session, user, configuration);
    }

    public SessionMemberEntity(Integer id, SessionEntity session, UserEntity user,
                               ConfigurationSessionMemberEntity configuration) {
        this.id = id;
        this.session = session;
        this.user = user;
        this.configuration = configuration;
    }

    /**
     * Effect: Return the id of a section member.
     * @return [Integer] : returns the id of a section member.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Effect: Sets the id of a section member.
     * @param id: new value for id.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Effect: Return the id of a section configuration.
     * @return [ConfigurationSessionMemberEntity] : returns the id of a section configuration.
     */
    public ConfigurationSessionMemberEntity getConfiguration() {
        return configuration;
    }

    /**
     * Effect: Sets the id of a section configuration.
     * @param idConfiguration: new value for id configuration.
     */
    public void setConfiguration(ConfigurationSessionMemberEntity idConfiguration) {
        this.configuration = idConfiguration;
    }

    /**
     * Effect: Returns the session of a SessionMember.
     * @return [SessionEntity]: returns the session of a session member.
     */
    public SessionEntity getSession() {
        return session;
    }

    /**
     * Effect: Sets the session of a Session Member.
     * @param session: new value for the session.
     */
    public void setSession(SessionEntity session) {
        this.session = session;
    }

    /**
     * Effect: Returns the user of a SessionMemberEntity.
     * @return [UserEntity]: returns the user of a session member.
     */
    public UserEntity getUser() {
        return user;
    }

    /**
     * Effect: Sets the user of a SessionMemberEntity.
     * @param user: new value for user.
     */
    public void setUser(UserEntity user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionMemberEntity that = (SessionMemberEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
