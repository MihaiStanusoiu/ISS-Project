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
    @Column(name = "ID_LOGIN")
    private Integer id;

    @Column(name = "ID_USER")
    private Integer id_user;

    @Column(name = "IP")
    private String ip;

    private static final Integer DEFAULT_ID = 0;

    public LoginEntity() {
        this.id = DEFAULT_ID;
        this.id_user = DEFAULT_ID;
        this.ip = "";
    }

    public LoginEntity(Integer id, Integer id_user, String ip) {
        this.id = id;
        this.id_user = id_user;
        this.ip = ip;
    }

    public LoginEntity(Integer id_user, String ip) {
        this(0, id_user, ip);
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
     * Effect: Returns the ip of the logged user
     *
     * @return [String]: ip of the logged user
     */
    public String getIp() {
        return ip;
    }

    /**
     * Effect: Sets the ip to the given value
     *
     * @param ip [String]: new value for the ip
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * Effect: Returns the id of the logged user
     *
     * @return [Integer]: id of the logged user
     */
    public Integer getId_user() {
        return id_user;
    }

    /**
     * Effect: Sets the id to the given value
     *
     * @param id_user [Integer]: new value for the id
     */
    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }

    public static Integer getDefaultId() {
        return DEFAULT_ID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoginEntity that = (LoginEntity) o;
        return id.equals(that.id) &&
                (ip != null ? ip.equals(that.ip) : that.ip == null);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (ip != null ? ip.hashCode() : 0);
        result = 31 *  result + (id_user != 0 ? id_user.hashCode() : 0);
        return result;
    }
}
