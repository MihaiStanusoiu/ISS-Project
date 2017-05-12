package domain;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Name:         SessionMemberEntity
 * Effect:       Class for the db table SessionMemberEntity.
 * Date:         08/04/2017
 * Tested:       True
 * @author       Tiron Andreea- Ecaterina
 * @version      1.0
 */

@Entity
@Table(name = "SESSION_MEMBER")
@SuppressWarnings("unused")
public class SessionMemberEntity implements Idable<Integer>{

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID_SESSION_MEMBER")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "ID_SESSION")
    private SessionEntity session;

    @ManyToOne
    @JoinColumn(name = "ID_USER")
    private UserEntity userSession;

    @ManyToOne
    @JoinColumn(name = "ID_CONFIGURATION_SESSION_MEMBER")
    private ConfigurationSessionMemberEntity idConfigurationSession;

    public SessionMemberEntity() { }

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
    public ConfigurationSessionMemberEntity getIdConfigurationSession() {
        return idConfigurationSession;
    }

    /**
     * Effect: Sets the id of a section configuration.
     * @param idConfiguration: new value for id configuration.
     */
    public void setIdConfigurationSession(ConfigurationSessionMemberEntity idConfiguration) {
        this.idConfigurationSession = idConfiguration;
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
     * @return [UserEntity]: returns the userSession of a session member.
     */
    public UserEntity getUser() {
        return userSession;
    }

    /**
     * Effect: Sets the user of a SessionMemberEntity.
     * @param user: new value for userSession.
     */
    public void setUser(UserEntity user) {
        this.userSession = user;
    }

}
