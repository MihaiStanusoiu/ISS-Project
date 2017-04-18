package domaintest;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Name:         UserEntity
 * Effect:       Ignore this entity (for testing only)
 * Date:         4/17/2017
 *
 * @author      Alexandru Stoica
 * @version     1.0
 */

@Entity
@Table(name = "USER")
@SuppressWarnings("unused")
public class UserEntity
            implements Idable<Integer> {

    /**
     * User's ID in our database system. [auto-generated]
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Integer userId;

    /**
     * User's unique name in our database system. [required]
     */
    @Column(name = "USERNAME", nullable = false)
    private String username;

    /**
     * User's password in our database system. [required]
     */
    @Column(name = "PASSWORD", nullable = false)
    private String password;

    /**
     * User's email in our database system. [required]
     */
    @Column(name = "EMAIL", nullable = false)
    private String email;

    /**
     * User's real name in our database system. [required]
     */
    @Column(name = "NAME", nullable = false)
    private String name;

    /**
     * User's website in our database system.
     * [not required] [default = '']
     */
    @Column(name = "WEBSITE", nullable = false)
    private String website;

    /**
     * User's bio in our database system.
     * [not required] [default = '']
     */
    @Column(name = "BIO", nullable = false)
    private String bio;

    /**
     * User's location in our database system.
     * [not required] [default = '']
     */
    @Column(name = "LOCATION", nullable = false)
    private String location;

    public UserEntity() { }

    /**
     * @param username The user's unique username
     * @param password The user's password
     * @param email The user's email
     * @param name The user's name
     */
    @SuppressWarnings("all")
    public UserEntity(String username,
                      String password,
                      String email,
                      String name) {
        this(username, password, email,name, "", "", "");
    }

    /**
     * @param username The user's unique name
     * @param password The user's password
     * @param email The user's email
     * @param name The user's name
     * @param website The user's website
     * @param bio The user's bio
     * @param location The user's location
     */
    @SuppressWarnings("all")
    public UserEntity(String username,
                      String password,
                      String email,
                      String name,
                      String website,
                      String bio,
                      String location) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.website = website;
        this.bio = bio;
        this.location = location;
        this.notifications = new HashSet<>();
    }

    /**
     * @return The user's id (primary key in User table)
     */
    public Integer getId() {
        return userId;
    }

    @Override
    public void setId(Integer id) {
        this.userId = id;
    }

    /**
     * @return The user's unique username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Don't use this method to create a user, use the constructor.
     * @param username The user's unique username.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return The user's password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Don't use this method to create a user, use the constructor.
     * @param password The user's password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return The user's email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Don't use this method to create a user, use the constructor.
     * @param email The user's email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return The user's real name,
     */
    public String getName() {
        return name;
    }

    /**
     * Don't use this method to create a user, use the constructor.
     * @param name The user's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The user's website.
     */
    public String getWebsite() {
        return website;
    }

    /**
     * Don't use this method to create a user, use the constructor.
     * @param website The user's website.
     */
    public void setWebsite(String website) {
        this.website = website;
    }

    /**
     * @return The user's bio.
     */
    public String getBio() {
        return bio;
    }

    /**
     * Don't use this method to create a user, use the constructor.
     * @param bio The user's bio.
     */
    public void setBio(String bio) {
        this.bio = bio;
    }

    /**
     * @return The user's location.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Don't use this method to create a user, use the constructor.
     * @param location The user's location.
     */
    public void setLocation(String location) {
        this.location = location;
    }

    @SuppressWarnings("all")
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    public Set<NotificationEntity> notifications;

    public Set<NotificationEntity> getNotifications() {
        return notifications;
    }

    @Override
    public String toString() {
        return this.userId + " " + this.username + " " + this.password + " " + this.email;
    }
}
