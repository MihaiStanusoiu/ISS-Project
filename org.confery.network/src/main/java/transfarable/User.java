package transfarable;


import java.io.Serializable;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class User implements Serializable {

    private Integer id;
    private String username;
    private String password;
    private String email;
    private String name;
    private String website;
    private String bio;
    private String location;

    public User(Integer id, String username, String password, String email, String name, String website, String bio, String location) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.website = website;
        this.bio = bio;
        this.location = location;
    }

    public User(String username, String password, String email, String name, String website, String bio, String location) {
        this(0, username, password, email, name, website, bio, location);
    }

    public User(String username, String password) {
        this(0, username, password, "", "", "", "", "");
    }

    public User(String username, String password, String email, String name) {
        this(username, password, email, name, "", "", "");
    }

    /**
     * Getter for property 'id'.
     *
     * @return Value for property 'id'.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Getter for property 'username'.
     *
     * @return Value for property 'username'.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Getter for property 'password'.
     *
     * @return Value for property 'password'.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Getter for property 'email'.
     *
     * @return Value for property 'email'.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Getter for property 'name'.
     *
     * @return Value for property 'name'.
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for property 'website'.
     *
     * @return Value for property 'website'.
     */
    public String getWebsite() {
        return website;
    }

    /**
     * Getter for property 'bio'.
     *
     * @return Value for property 'bio'.
     */
    public String getBio() {
        return bio;
    }

    /**
     * Getter for property 'location'.
     *
     * @return Value for property 'location'.
     */
    public String getLocation() {
        return location;
    }

}
