package database_domain;

import javax.persistence.*;

/**
 * Name:         SectionMemberTable
 * Date:         08/04/2017
 * @author Tiron Andreea- Ecaterina
 * @version 1.0
 */

@Entity
@Table(name = "SectionMember")
public class SectionMemberTable {

    @EmbeddedId
    private SectionMemberId id;

    @ManyToOne
    @JoinColumn(name = "id_configuration")
    private Integer idConfiguration;

    public SectionMemberTable(){
    }

    public SectionMemberId getId() {
        return id;
    }

    public void setId(SectionMemberId id) {
        this.id = id;
    }

    public Integer getIdConfiguration() {
        return idConfiguration;
    }

    public void setIdConfiguration(Integer idConfiguration) {
        this.idConfiguration = idConfiguration;
    }


}
