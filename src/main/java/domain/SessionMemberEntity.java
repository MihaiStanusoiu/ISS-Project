package domain;

import javax.persistence.*;

/**
 * Name:         SessionMemberEntity
 * Effect:       Class for the db table SessionMemberEntity.
 * Date:         08/04/2017
 * @author       Tiron Andreea- Ecaterina
 * @version      1.0
 */

@Entity
@Table(name = "SESSION_MEMBER")
@SuppressWarnings("unused")
public class SessionMemberEntity implements Idable<SessionMemberId>{

    @EmbeddedId
    private SessionMemberId id;

    @ManyToOne
    @JoinColumn(name = "ID_SESSION")
    private SessionEntity session;

    @ManyToOne
    @JoinColumn(name = "ID_USER")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "ID_CONFIGURATION_SESSION_MEMBER ")
    private ConfigurationSessionMemberEntity idConfigurationSession;

    public SessionMemberEntity() { }

    /**
     * Effect: Return the id of a section member.
     * @return [Integer] : returns the id of a section member.
     */
    public SessionMemberId getId() {
        return id;
    }

    /**
     * Effect: Sets the id of a section member.
     * @param id: new value for id.
     */
    public void setId(SessionMemberId id) {
        this.id = id;
    }

    /**
     * Effect: Return the id of a section configuration.
     * @return [ConfigurationSessionMemberEntity] : returns the id of a section configuration.
     */
    public ConfigurationSessionMemberEntity getIdConfiguration() {
        return idConfigurationSession;
    }

    /**
     * Effect: Sets the id of a section configuration.
     * @param idConfiguration: new value for id configuration.
     */
    public void setIdConfiguration(ConfigurationSessionMemberEntity idConfiguration) {
        this.idConfigurationSession = idConfiguration;
    }

    public SessionEntity getSession() {
        return session;
    }

    public void setSession(SessionEntity session) {
        this.session = session;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

}
