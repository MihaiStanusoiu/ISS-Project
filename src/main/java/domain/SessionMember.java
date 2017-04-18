package domain;

import javax.persistence.*;

/**
 * Name:         SessionMember
 * Effect:       Class for the db table SessionMember.
 * Date:         08/04/2017
 * @author       Tiron Andreea- Ecaterina
 * @version      1.0
 */

@Entity
@Table(name = "SessionMember")
@SuppressWarnings("unused")
public class SessionMember {

    @EmbeddedId
    private SessionMemberId id;

    @ManyToOne
    @JoinColumn(name = "id_configuration")
    private Integer idConfiguration;

    public SessionMember() { }

    /**
     * Effect: Return the id of a section member.
     * @return [Integer] : returns the id of a section member.
     */
    public SessionMemberId getId() {
        return id;
    }

    /**
     * Effect: Sets the id of a section member.
     * @param id: new value for id.
     */
    public void setId(SessionMemberId id) {
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
