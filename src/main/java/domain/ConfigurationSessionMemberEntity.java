package domain;

import javax.persistence.*;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Name:         ConfigurationSessionMember
 * Effect:       Class for the db table ConfigurationSectionMember
 * Date:         08/04/2017
 * Tested:       False
 * @author       Tiron Andreea- Ecaterina
 * @version      1.0
 */

@Entity
@Table(name = "CONFIGURATION_SESSION_MEMBER")
@SuppressWarnings("unused")
public class ConfigurationSessionMemberEntity implements Idable<Integer>{

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID_CONFIGURATION_SESSION_MEMBER")
    private Integer id;

    @Column(name = "IS_CHAIR")
    private Boolean isChair;

    @Column(name = "IS_SPEAKER")
    private Boolean isSpeaker;

    @Column(name = "IS_LISTENER")
    private Boolean isListener;

    public ConfigurationSessionMemberEntity(Boolean isChair, Boolean isSpeaker, Boolean isListener) {
        this.isChair = isChair;
        this.isSpeaker = isSpeaker;
        this.isListener = isListener;
    }

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "idConfigurationSession",cascade = CascadeType.ALL)
    private Set<SessionMemberEntity> sectionMembers;

    /**
     * Effect: Return the section members with this configuration.
     * @return [ArrayList<SessionMemberEntity>] : returns the list of members.
     */
    public Set<SessionMemberEntity> getSectionMembers() {
        return this.sectionMembers;
    }

    /**
     * Effect: Sets the list of members to a section configuration.
     * @param sectionMembers : new value for section members.
     */
    public void setSectionMembers(Set<SessionMemberEntity> sectionMembers) {
        this.sectionMembers = sectionMembers;
    }

    public ConfigurationSessionMemberEntity(){
    }

    /**
     * Effect: Return the id of a configuration section member.
     * @return [Integer] : returns the id.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Effect: Sets the id of a configuration section member.
     * @param id : new value for id.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Effect: Return whether the user is a chair.
     * @return [Boolean] : returns true if it is a chair, false otherwise.
     */
    public Boolean getChair() {
        return isChair;
    }

    /**
     * Effect: Sets whether the user is a chair.
     * @param chair: new value for chair.
     */
    public void setChair(Boolean chair) {
        isChair = chair;
    }

    /**
     * Effect: Return whether the user is a speaker.
     * @return [Boolean] : returns true if it is a speaker, false otherwise.
     */
    public Boolean getSpeaker() {
        return isSpeaker;
    }

    /**
     * Effect: Sets whether the user is a speaker.
     * @param speaker: new value for speaker.
     */
    public void setSpeaker(Boolean speaker) {
        isSpeaker = speaker;
    }

    /**
     * Effect: Return whether the user is a listener.
     * @return [Boolean] : returns true if it is a listener, false otherwise.
     */
    public Boolean getListener() {
        return isListener;
    }

    /**
     * Effect: Sets whether the user is a listener.
     * @param listener: new value for listener.
     */
    public void setListener(Boolean listener) {
        isListener = listener;
    }
}
