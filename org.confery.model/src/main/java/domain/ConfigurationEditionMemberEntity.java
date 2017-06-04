
package domain;

import javax.persistence.*;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Tested: True
 *
 * @author Tiron Andreea-Ecaterina & Alexandru Stoica
 * @version 1.1
 */


@Entity
@Table(name = "CONFIGURATION_EDITION_MEMBER")
public class ConfigurationEditionMemberEntity implements Idable<Integer> {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID_CONFIGURATION_EDITION_MEMBER")
    private Integer id;

    @Column(name = "IS_CHAIR")
    private Boolean isChair;

    @Column(name = "IS_COCHAIR")
    private Boolean isCoChair;

    @Column(name = "IS_PCMEMBER")
    private Boolean isPcMember;

    @OneToMany(targetEntity = EditionMemberEntity.class, fetch = FetchType.EAGER,
            mappedBy = "configuration", cascade = CascadeType.ALL)
    private Set<EditionMemberEntity> editionMembers;

    private static final Boolean DEFAULT_CHAIR = Boolean.FALSE;
    private static final Boolean DEFAULT_CO_CHAIR = Boolean.FALSE;
    private static final Boolean DEFAULT_PC_MEMBER = Boolean.TRUE;
    private static final Integer DEFAULT_ID = 0;

    public ConfigurationEditionMemberEntity() {
        this(DEFAULT_ID, DEFAULT_CHAIR, DEFAULT_CO_CHAIR, DEFAULT_PC_MEMBER);
    }

    /**
     * @param isChair    true if the edition member is chair
     * @param isCoChair  true if the edition member is co-chair
     * @param isPcMember true if the edition member if pc-member
     */
    public ConfigurationEditionMemberEntity(Boolean isChair, Boolean isCoChair, Boolean isPcMember) {
        this(DEFAULT_ID, isChair, isCoChair, isPcMember);
    }

    /**
     * @param id         The object's id
     * @param isChair    true if the edition member is chair
     * @param isCoChair  true if the edition member is co-chair
     * @param isPcMember true if the edition member if pc-member
     */
    public ConfigurationEditionMemberEntity(Integer id, Boolean isChair, Boolean isCoChair, Boolean isPcMember) {
        this.id = id;
        this.isChair = isChair;
        this.isCoChair = isCoChair;
        this.isPcMember = isPcMember;
    }

    /**
     * Effect: Returns the editionMembers of a ConfigurationEditionMemberEntity
     *
     * @return [editionMembers] : return the editionMembers
     */
    public Set<EditionMemberEntity> getEditionMembers() {
        return editionMembers;
    }

    /**
     * Effect: Returns the id of the conference member.
     *
     * @return [Integer] : returns the id.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Effect: Sets the id of a configuration conference member.
     *
     * @param id: new value for id.
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
     * Effect: Return whether the user is a co-chair.
     *
     * @return [Boolean] : returns true if it is a co-chair, false otherwise.
     */
    public Boolean getCoChair() {
        return isCoChair;
    }

    /**
     * Effect: Return whether the user is a pc-member.
     *
     * @return [Boolean] : returns true if it is a pc-member, false otherwise.
     */
    public Boolean getPcMember() {
        return isPcMember;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConfigurationEditionMemberEntity that = (ConfigurationEditionMemberEntity) o;
        return isChair.equals(that.isChair) &&
                isCoChair.equals(that.isCoChair) &&
                isPcMember.equals(that.isPcMember);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + isChair.hashCode();
        result = 31 * result + isCoChair.hashCode();
        result = 31 * result + isPcMember.hashCode();
        return result;
    }
}
