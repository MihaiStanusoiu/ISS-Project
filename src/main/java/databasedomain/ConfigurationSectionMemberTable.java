package databasedomain;

import javax.persistence.*;
import java.util.ArrayList;
import static javax.persistence.GenerationType.IDENTITY;

/**
 * Name:         ConfigurationSectionMemberTable
 * Effect:       Class for the db table ConfigurationSectionMember
 * Date:         08/04/2017
 * Tested:       False
 * @author       Tiron Andreea- Ecaterina
 * @version      1.0
 */

@Entity
@Table(name = "ConfigurationSectionMember")
@SuppressWarnings("unused")
public class ConfigurationSectionMemberTable {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_configuration_section_member")
    private Integer id;

    @Column(name = "is_chair")
    private Boolean isChair;

    @Column(name = "is_speaker")
    private Boolean isSpeaker;

    @Column(name = "is_listener")
    private Boolean isListener;

    @OneToMany(mappedBy = "idConfiguration")
    private ArrayList<SectionMemberTable> sectionMembers = new ArrayList<>();

    /**
     * Effect: Return the section members with this configuration.
     * @return [ArrayList<SessionMember>] : returns the list of members.
     */
    public ArrayList<SectionMemberTable> getSectionMembers() {
        return this.sectionMembers;
    }

    /**
     * Effect: Sets the list of members to a section configuration.
     * @param sectionMembers : new value for section members.
     */
    public void setSectionMembers(ArrayList<SectionMemberTable> sectionMembers) {
        this.sectionMembers = sectionMembers;
    }

    public ConfigurationSectionMemberTable(){
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
