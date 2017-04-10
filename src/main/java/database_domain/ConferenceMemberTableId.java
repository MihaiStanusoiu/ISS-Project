package database_domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Name:        ConferenceMemberTableId
 * Effect:      Class for the ConferenceMember table composite PK.
 * Date:        4/8/2017
 * Tested:      False
 * @author      Stanusoiu Mihai-Teodor
 * @version     1.0
 */
@Embeddable
public class ConferenceMemberTableId {
    @Column(name = "id")
    private Integer id;

    @Column(name = "id_user")
    private Integer idUser;

    @Column(name = "id_conference")
    private Integer idConference;

    public ConferenceMemberTableId() {}

    /**
     * Effect: Returns the id of the conferencemember record
     * @return [Integer]: id of the conferencemember record
     */
    public Integer getId() {
        return id;
    }

    /**
     * Effect: Sets the id of the record to the given value
     * @param id [Integer]: new value for the id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Effect: Returns the id of the user
     * @return [Integer]: id of the user
     */
    public Integer getIdUser() {
        return idUser;
    }

    /**
     * Effect: Sets the user id to the given value
     * @param idUser [Integer]: new value for the user id
     */
    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    /**
     * Effect: Returns the id of the conference
     * @return [Integer]: id of the conference
     */
    public Integer getIdConference() {
        return idConference;
    }

    /**
     * Effect: Sets the conference id to the given value
     * @param idConference [Integer]: new value for the conference id
     */
    public void setIdConference(Integer idConference) {
        this.idConference = idConference;
    }
}
