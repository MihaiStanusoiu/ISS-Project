package databasedomain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

/**
 * Name:         SectionMemberId
 * Effect:       Helping class for composite primary key.
 * Date:         08/04/2017
 * Tested:       False
 * @author       Tiron Andreea- Ecaterina
 * @version      1.0
 */

@Embeddable
@SuppressWarnings("unused")
public class SectionMemberId {

    @ManyToOne
    @Column(name = "id_section")
    private Integer idSection;

    @ManyToOne
    @Column(name = "id_user")
    private Integer idUser;

    public SectionMemberId() { }

    /**
     * Effect: Return the id of a section.
     * @return [Integer] : returns the id of a section.
     */
    public Integer getIdSection() {
        return idSection;
    }

    /**
     * Effect: Sets the id of a section.
     * @param idSection : new value for id.
     */
    public void setIdSection(Integer idSection) {
        this.idSection = idSection;
    }

    /**
     * Effect: Return the id of a section member user.
     * @return [Integer] : returns the id of an user.
     */
    public Integer getIdUser() {
        return idUser;
    }

    /**
     * Effect: Sets the id of a section member user.
     * @param idUser : new value for id.
     */
    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

}
