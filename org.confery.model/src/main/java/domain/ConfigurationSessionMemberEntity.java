
package domain;

import javax.persistence.*;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Tested:       True
 *
 * @author Tiron Andreea-Ecaterina & Alexandru Stoica
 * @version 1.1
 */

@Entity
@Table(name = "CONFIGURATION_SESSION_MEMBER")
@SuppressWarnings("unused")
public class ConfigurationSessionMemberEntity implements Idable<Integer> {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID_CONFIGURATION_SESSION_MEMBER")
    private Integer id;

    @Column(name = "IS_CHAIR")
    private Boolean isChair;

    @Column(name = "IS_SPEAKER")
    private Boolean isSpeaker;

    @Column(name = "IS_LISTENER")
    private Boolean isListener;

    @OneToMany(targetEntity = SessionMemberEntity.class, fetch = FetchType.EAGER,
            mappedBy = "configuration", cascade = CascadeType.ALL)
    private Set<SessionMemberEntity> sectionMembers;

    private static final Integer DEFAULT_ID = 0;
    private static final Boolean DEFAULT_CHAIR = Boolean.FALSE;
    private static final Boolean DEFAULT_SPEAKER = Boolean.FALSE;
    private static final Boolean DEFAULT_LISTENER = Boolean.FALSE;

    /**
     * @param id         The object's id
     * @param isChair    If member is the chair of the session
     * @param isSpeaker  If member is a speaker at the session
     * @param isListener If member is a listener at the session
     */
    public ConfigurationSessionMemberEntity(Integer id, Boolean isChair, Boolean isSpeaker, Boolean isListener) {
        this.id = id;
        this.isChair = isChair;
        this.isSpeaker = isSpeaker;
        this.isListener = isListener;
    }

    /**
     * @param isChair    If member is the chair of the session
     * @param isSpeaker  If member is a speaker at the session
     * @param isListener If member is a listener at the session
     */
    public ConfigurationSessionMemberEntity(Boolean isChair, Boolean isSpeaker, Boolean isListener) {
        this(DEFAULT_ID, isChair, isSpeaker, isListener);
    }

    public ConfigurationSessionMemberEntity() {
        this(DEFAULT_ID, DEFAULT_CHAIR, DEFAULT_SPEAKER, DEFAULT_LISTENER);
    }

    /**
     * Effect: Return the section members with this configuration.
     *
     * @return [ArrayList<SessionMemberEntity>] : returns the list of members.
     */
    public Set<SessionMemberEntity> getSectionMembers() {
        return this.sectionMembers;
    }

    /**
     * Effect: Sets the list of members to a section configuration.
     *
     * @param sectionMembers : new value for section members.
     */
    public void setSectionMembers(Set<SessionMemberEntity> sectionMembers) {
        this.sectionMembers = sectionMembers;
    }

    /**
     * Effect: Return the id of a configuration section member.
     *
     * @return [Integer] : returns the id.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Effect: Sets the id of a configuration section member.
     *
     * @param id : new value for id.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Effect: Return whether the user is a chair.
     *
     * @return [Boolean] : returns true if it is a chair, false otherwise.
     */
    public Boolean getChair() {
        return isChair;
    }

    /**
     * Effect: Sets whether the user is a chair.
     *
     * @param chair: new value for chair.
     */
    public void setChair(Boolean chair) {
        isChair = chair;
    }

    /**
     * Effect: Return whether the user is a speaker.
     *
     * @return [Boolean] : returns true if it is a speaker, false otherwise.
     */
    public Boolean getSpeaker() {
        return isSpeaker;
    }

    /**
     * Effect: Sets whether the user is a speaker.
     *
     * @param speaker: new value for speaker.
     */
    public void setSpeaker(Boolean speaker) {
        isSpeaker = speaker;
    }

    /**
     * Effect: Return whether the user is a listener.
     *
     * @return [Boolean] : returns true if it is a listener, false otherwise.
     */
    public Boolean getListener() {
        return isListener;
    }

    /**
     * Effect: Sets whether the user is a listener.
     *
     * @param listener: new value for listener.
     */
    public void setListener(Boolean listener) {
        isListener = listener;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConfigurationSessionMemberEntity that = (ConfigurationSessionMemberEntity) o;
        return isChair.equals(that.isChair) &&
                isSpeaker.equals(that.isSpeaker) &&
                isListener.equals(that.isListener);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + isChair.hashCode();
        result = 31 * result + isSpeaker.hashCode();
        result = 31 * result + isListener.hashCode();
        return result;
    }

    public String toString() {
        return (this.getChair() ? "1" : "0") +
                (this.getListener() ? "1" : "0") +
                (this.getSpeaker() ? "1" : "0");
    }

}
