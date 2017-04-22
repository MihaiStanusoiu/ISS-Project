package domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * Name:         SessionMemberId
 * Effect:       Helping class for composite primary key.
 * Date:         08/04/2017
 * Tested:       False
 * @author       Tiron Andreea- Ecaterina
 * @version      1.0
 */

@Embeddable
@SuppressWarnings("unused")
public class SessionMemberId implements Serializable{

    private Integer idSession;

    private Integer idUser;

    public SessionMemberId() { }

    /**
     * Effect: Return the id of a section.
     * @return [Integer] : returns the id of a section.
     */
    public Integer getIdSession() {
        return idSession;
    }

    /**
     * Effect: Sets the id of a section.
     * @param idSession : new value for id.
     */
    public void setIdSession(Integer idSession) {
        this.idSession = idSession;
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
