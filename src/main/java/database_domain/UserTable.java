package database_domain;

import domain.UserType;

import javax.persistence.*;
import java.util.ArrayList;

/**
 * Name:        UserTable
 * Effect:      Corresponding class for the User table in the database.
 * Date:        4/8/2017
 * Tested:      False
 * @author      Stanusoiu Mihai-Teodor
 * @version     1.0
 */
@Entity
@Table(name = "User")
public class UserTable {
    @Id
    @GeneratedValue
    @Column(name = "id_user")
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "name")
    private String name;

    @Column(name = "website")
    private String website;

    @Column(name = "bio")
    private String bio;

    @Column(name = "location")
    private String location;

    @OneToMany(mappedBy = "idUser")
    private ArrayList<NotificationTable> notifications = new ArrayList<NotificationTable>();

    public UserTable() {}

    /**
     * Effect: Returns the id of the user
     * @return [Integer]: id of the user
     */
    public Integer getId() {
        return id;
    }

    /**
     * Effect: Returns the username of the user
     * @return [String]: username of the user
     */
    public String getUsername() {
        return username;
    }

    /**
     * Effect: Returns the password of the user
     * @return [String]: password of the user
     */
    public String getPassword() {
        return password;
    }

    /**
     * Effect: Returns the email of the user
     * @return [String]: email of the user
     */
    public String getEmail() {
        return email;
    }

    /**
     * Effect: Returns the name of the user
     * @return [String]: name of the user
     */
    public String getName() {
        return name;
    }

    /**
     * Effect: Returns the website of the user
     * @return [String]: website of the user
     */
    public String getWebsite()
    {
        return website;
    }

    /**
     * Effect: Returns the bio of the user
     * @return [String]: bio of the user
     */
    public String getBio() {
        return bio;
    }

    /**
     * Effect: Returns the location of the user
     * @return [String]: location of the user
     */
    public String getLocation() {
        return location;
    }

    /**
     * Effect: Sets the id to the given value
     * @param id [Integer]: new value for the id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Effect: Sets the username to the given value
     * @param username [String]: new value for the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Effect: Sets the password to the given value
     * @param password [String]: new value for the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Effect: Sets the email to the given value
     * @param email [String]: new value for the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Effect: Sets the name to the given value
     * @param name [String]: new value for the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Effect: Sets the website to the given value
     * @param website [String]: new value for the website
     */
    public void setWebsite(String website) {
        this.website = website;
    }

    /**
     * Effect: Sets the bio to the given value
     * @param bio [String]: new value for the bio
     */
    public void setBio(String bio) {
        this.bio = bio;
    }

    /**
     * Effect: Sets the location to the given value
     * @param location [String]: new value for the location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Effect: Returns the notifications of the user
     * @return [ArrayList<NotificationTable>]: nofitications of the user
     */
    public ArrayList<NotificationTable> getNotifications() {
        return notifications;
    }

    /**
     * Effect: Sets the notifications array to the given value
     * @param notifications [ArrayList<NotificationTable>]: new value for the notifications array
     */
    public void setNotifications(ArrayList<NotificationTable> notifications) {
        this.notifications = notifications;
    }
}
