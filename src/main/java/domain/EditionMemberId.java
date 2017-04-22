package domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Name:        EditionMemberId
 * Effect:      Class for the EditionMemberEntity table composite PK.
 * Date:        4/8/2017
 * Tested:      False
 * @author      Stanusoiu Mihai-Teodor
 * @version     1.0
 */

@Embeddable
@SuppressWarnings("unused")
public class EditionMemberId implements Serializable{

   // @Column(name = "ID_EDITION_MEMBER")
    private Integer id;

   // @Column(name = "ID_USER")
    private Integer idUser;

   // @Column(name = "ID_CONFERENCE")
    private Integer idConference;

    public EditionMemberId() {}

    /**
     * Effect: Returns the id of the conference member record
     * @return [Integer]: id of the conference member record
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
