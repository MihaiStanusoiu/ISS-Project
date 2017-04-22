package domain;

import javax.persistence.*;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Name:         ConfigurationEditionMember
 * Effect:       Class for the db table ConfigurationEditionMember.
 * Date:         08/04/2017
 * Tested:       False
 * @author       Tiron Andreea-Ecaterina
 * @version      1.0
 */

@Entity
@Table(name = "CONFIGURATION_EDITION_MEMBER")
@SuppressWarnings("unused")
public class ConfigurationEditionMemberEntity implements Idable<Integer> {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID_CONFIGURATION_EDITION_MEMBER")
    private Integer id;

    @Column(name = "IS_CHAIR")
    private Boolean isChair;

    @Column(name = "IS_COCHAIR")
    private Boolean isCoChair;

    @Column(name = "IS_PCMEMBER")
    private Boolean isPCMember;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "idConfiguration",cascade = CascadeType.ALL)
    private Set<EditionMemberEntity> editionMembers;

    public ConfigurationEditionMemberEntity(){}

    public ConfigurationEditionMemberEntity(Boolean isChair, Boolean isCoChair, Boolean isPCMember) {
        this.isChair = isChair;
        this.isCoChair = isCoChair;
        this.isPCMember = isPCMember;
    }

    public Set<EditionMemberEntity> getEditionMembers() {
        return editionMembers;
    }

    public void setEditionMembers(Set<EditionMemberEntity> editionMembers) {
        this.editionMembers = editionMembers;
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
    public Boolean getCoChair() {
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
