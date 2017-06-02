package domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Tested: False
 *
 * @author Teodorescu Vlad
 * @version 1.0
 */
@Entity
@Table(name = "LOGGED")
public class LoginEntity implements Serializable, Idable<Integer> {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Integer id;

    @Column(name = "ID_USER")
    private Integer idUser;

    @Column(name = "HOST_USER")
    private String hostUser;

    private static final Integer DEFAULT_ID = 0;

    @Deprecated
    public LoginEntity() {
        this.id = DEFAULT_ID;
        this.idUser = DEFAULT_ID;
        this.hostUser = "";
    }

    public LoginEntity(Integer id, Integer idUser, String hostUser) {
        this.id = id;
        this.idUser = idUser;
        this.hostUser = hostUser;
    }

    public LoginEntity(Integer idUser, String hostUser) {
        this(0, idUser, hostUser);
    }

    /**
     * Effect: Returns the id of the log
     *
     * @return [Integer]: id of the log
     */
    @Override
    public Integer getId() {
        return id;
    }

    /**
     * Effect: Sets the id to the given value
     *
     * @param id [Integer]: new value for the id
     */
    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Effect: Returns the hostUser of the logged user
     *
     * @return [String]: hostUser of the logged user
     */
    public String getHostUser() {
        return hostUser;
    }

    /**
     * Effect: Returns the id of the logged user
     *
     * @return [Integer]: id of the logged user
     */
    public Integer getIdUser() {
        return idUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoginEntity that = (LoginEntity) o;
        return id.equals(that.id) &&
                (hostUser != null ? hostUser.equals(that.hostUser) : that.hostUser == null);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (hostUser != null ? hostUser.hashCode() : 0);
        result = 31 *  result + (idUser != 0 ? idUser.hashCode() : 0);
        return result;
    }
}
