package databasedomain;

import javax.persistence.*;
import static javax.persistence.GenerationType.IDENTITY;

/**
 * Name:         ConfigurationEditionMemberTable
 * Effect:       Class for the db table ConfigurationConferenceMember.
 * Date:         08/04/2017
 * Tested:       False
 * @author       Tiron Andreea-Ecaterina
 * @version      1.0
 */

@Entity
@Table(name = "ConfigurationEditionMember")
@SuppressWarnings("unused")
public class ConfigurationEditionMemberTable {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_configuration_conference_member")
    private Integer id;

    @Column(name = "is_chair")
    private Boolean isChair;

    @Column(name = "is_cochair")
    private Boolean isCoChair;

    @Column(name = "is_pcmember")
    private Boolean isPCMember;


    public ConfigurationEditionMemberTable(){
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
    @SuppressWarnings("unused")
    public Boolean getChair() {
        return isChair;
    }

    /**
     * Effect: Sets whether the user is a chair.
     * @param chair: new value for chair.
     */
    @SuppressWarnings("unused")
    public void setChair(Boolean chair) {
        isChair = chair;
    }

    /**
     * Effect: Return whether the user is a co-chair.
     * @return [Boolean] : returns true if it is a co-chair, false otherwise.
     */
    @SuppressWarnings("unused")
    public Boolean getCochair() {
        return isCoChair;
    }

    /**
     * Effect: Sets whether the user is a co-chair.
     * @param coChair: new value for co-chair.
     */
    @SuppressWarnings("unused")
    public void setCoChair(Boolean coChair) {
        isCoChair = coChair;
    }

    /**
     * Effect: Return whether the user is a pc-member.
     * @return [Boolean] : returns true if it is a pc-member, false otherwise.
     */
    @SuppressWarnings("unused")
    public Boolean getPCMember() {
        return isPCMember;
    }

    /**
     * Effect: Sets whether the user is a pc-member.
     * @param pcMember: new value for pc-member.
     */
    @SuppressWarnings("unused")
    public void setPCMember(Boolean pcMember) {
        isPCMember = pcMember;
    }

}
