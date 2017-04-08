package database_domain;

import domain.SectionMember;

import javax.persistence.*;

import java.util.ArrayList;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Name:         ConfigurationSectionMemberTable
 * Date:         08/04/2017
 * Tested:       False
 * @author Tiron Andreea- Ecaterina
 * @version 1.0
 */

@Entity
@Table(name = "ConfigurationSectionMember")
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
    private ArrayList<SectionMember> sectionMembers = new ArrayList<>();

    public ArrayList<SectionMember> getSectionMembers() {
        return this.sectionMembers;
    }

    public void setSectionMembers(ArrayList<SectionMember> sectionMembers) {
        this.sectionMembers = sectionMembers;
    }

    public ConfigurationSectionMemberTable(){
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getChair() {
        return isChair;
    }

    public void setChair(Boolean chair) {
        isChair = chair;
    }

    public Boolean getSpeaker() {
        return isSpeaker;
    }

    public void setSpeaker(Boolean speaker) {
        isSpeaker = speaker;
    }

    public Boolean getListener() {
        return isListener;
    }

    public void setListener(Boolean listener) {
        isListener = listener;
    }
}
