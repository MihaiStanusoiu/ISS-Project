package database_domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

/**
 * Name:         SectionMemberId
 * Effect:       Helping class for composite primary key.
 * Date:         08/04/2017
 * Tested:       False
 * @author Tiron Andreea- Ecaterina
 * @version 1.0
 */

@Embeddable
public class SectionMemberId {

    @ManyToOne
    @Column(name = "id_section")
    private Integer idSection;

    @ManyToOne
    @Column(name = "id_user")
    private Integer idUser;

    public SectionMemberId(){
    }

    public Integer getIdSection() {
        return idSection;
    }

    public void setIdSection(Integer idSection) {
        this.idSection = idSection;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }



}
