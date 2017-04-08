package database_domain;

import domain.SectionMember;

import javax.persistence.*;

import java.util.ArrayList;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Name:         ConfigurationConferenceMemberTable
 * Date:         08/04/2017
 * Tested:       False
 * @author       Tiron Andreea- Ecaterina
 * @version      1.0
 */

@Entity
@Table(name = "ConfigurationConferenceMember")
public class ConfigurationConferenceMemberTable {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_configuration_conference_member")
    private Integer id;

    @Column(name = "is_chair")
    private Boolean isChair;

    @Column(name = "is_cochair")
    private Boolean isCochair;

    @Column(name = "is_pcmember")
    private Boolean isPcmember;


    public ConfigurationConferenceMemberTable(){
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

    public Boolean getCochair() {
        return isCochair;
    }

    public void setCochair(Boolean cochair) {
        isCochair = cochair;
    }

    public Boolean getPcmember() {
        return isPcmember;
    }

    public void setPcmember(Boolean pcmember) {
        isPcmember = pcmember;
    }

}
