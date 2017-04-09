package database_domain;

import javax.persistence.*;
import static javax.persistence.GenerationType.IDENTITY;

/**
 * Name:         ConfigurationConferenceMemberTable
 * Effect:       Class for the db table ConfigurationConferenceMember.
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

    /**
     * Effect: Returns the id of the conference member.
     * @return [Integer] : returns the id.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Effect: Sets the id of a configuration conference member.
     * @param id: new value for id.
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
     * Effect: Return whether the user is a co-chair.
     * @return [Boolean] : returns true if it is a co-chair, false otherwise.
     */
    public Boolean getCochair() {
        return isCochair;
    }

    /**
     * Effect: Sets whether the user is a co-chair.
     * @param cochair: new value for co-chair.
     */
    public void setCochair(Boolean cochair) {
        isCochair = cochair;
    }

    /**
     * Effect: Return whether the user is a pc-member.
     * @return [Boolean] : returns true if it is a pc-member, false otherwise.
     */
    public Boolean getPcmember() {
        return isPcmember;
    }

    /**
     * Effect: Sets whether the user is a pc-member.
     * @param pcmember: new value for pc-member.
     */
    public void setPcmember(Boolean pcmember) {
        isPcmember = pcmember;
    }

}
