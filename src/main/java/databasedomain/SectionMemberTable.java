package databasedomain;

import javax.persistence.*;

/**
 * Name:         SectionMemberTable
 * Effect:       Class for the db table SessionMember.
 * Date:         08/04/2017
 * @author       Tiron Andreea- Ecaterina
 * @version      1.0
 */

@Entity
@Table(name = "SessionMember")
@SuppressWarnings("unused")
public class SectionMemberTable {

    @EmbeddedId
    private SectionMemberId id;

    @ManyToOne
    @JoinColumn(name = "id_configuration")
    private Integer idConfiguration;

    public SectionMemberTable() { }

    /**
     * Effect: Return the id of a section member.
     * @return [Integer] : returns the id of a section member.
     */
    public SectionMemberId getId() {
        return id;
    }

    /**
     * Effect: Sets the id of a section member.
     * @param id: new value for id.
     */
    public void setId(SectionMemberId id) {
        this.id = id;
    }

    /**
     * Effect: Return the id of a section configuration.
     * @return [Integer] : returns the id of a section configuration.
     */
    public Integer getIdConfiguration() {
        return idConfiguration;
    }

    /**
     * Effect: Sets the id of a section configuration.
     * @param idConfiguration: new value for id configuration.
     */
    public void setIdConfiguration(Integer idConfiguration) {
        this.idConfiguration = idConfiguration;
    }


}
