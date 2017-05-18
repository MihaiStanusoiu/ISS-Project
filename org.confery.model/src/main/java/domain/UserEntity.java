package domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Name:        UserEntity
 * Effect:      Corresponding class for the UserEntity table in the database.
 * Date:        4/8/2017
 * Tested:      True
 * @author      Stanusoiu Mihai-Teodor
 * @version     1.0
 */

@Entity
@Table(name = "USER")
@SuppressWarnings("unused")
public class UserEntity implements Serializable, Idable<Integer> {

    @Id
    @GeneratedValue
    @Column(name = "ID_USER")
    private Integer id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "NAME")
    private String name;

    @Column(name = "WEBSITE")
    private String website;

    @Column(name = "BIO")
    private String bio;

    @Column(name = "LOCATION")
    private String location;

    public UserEntity() {}

    public UserEntity(String username, String password, String email,
                      String name, String website, String bio, String location) {
        this(0, username, password, email, name, website, bio, location);
    }

    public UserEntity(Integer id, String username, String password, String email,
                      String name, String website, String bio, String location) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.website = website;
        this.bio = bio;
        this.location = location;
    }

    public UserEntity(String username, String password) {
        this(0, username, password, "", "", "", "", "");
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
    private Set<NotificationEntity> notifications = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "userSession", cascade = CascadeType.ALL)
    private Set<SessionMemberEntity> sessionMembers = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "idUser", cascade = CascadeType.ALL)
    private Set<EditionMemberEntity> editionMembers = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "userSubmission", cascade = CascadeType.ALL)
    private Set<AuthorSubmissionEntity> authorSubmissions = new HashSet<>();

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
     * @return [Set<NotificationEntity>]: notifications of the user
     */
    public Set<NotificationEntity> getNotifications() {
        return notifications;
    }

    /**
     * Effect: Sets the notifications array to the given value
     * @param notifications [Set<NotificationEntity>]: new value for the notifications array
     */
    public void setNotifications(Set<NotificationEntity> notifications) {
        this.notifications = notifications;
    }

    /**
     * Effect: Returns the sessionMembers of a UserEntity.
     * @return [Set<SessionMemberEntity>]: sessionMembers of the UserEntity.
     */
    public Set<SessionMemberEntity> getSessionMembers() {
        return sessionMembers;
    }

    /**
     * Effect: Sets the sessionMembers of a UserEntity.
     * @param sessionMembers: new value for the sessionMembers.
     */
    public void setSessionMembers(Set<SessionMemberEntity> sessionMembers) {
        this.sessionMembers = sessionMembers;
    }

    /**
     * Effect: Returns the author submissions of a UserEntity.
     * @return [Set<AuthorSubmissionEntity>]: authorSubmissions of the UserEntity.
     */
    public Set<AuthorSubmissionEntity> getAuthorSubmissions() {
        return authorSubmissions;
    }

    /**
     * Effect: Sets the author submission of a UserEntity.
     * @param authorSubmissions: new value for authorSubmissions.
     */
    public void setAuthorSubmissions(Set<AuthorSubmissionEntity> authorSubmissions) {
        this.authorSubmissions = authorSubmissions;
    }

    /**
     * Effect: Returns the edition members of a UserEntity.
     * @return [Set<EditionMemberEntity>]: editionMembers of the UserEntity.
     */
    public Set<EditionMemberEntity> getEditionMembers() {
        return editionMembers;
    }

    /**
     * Effect: Sets the edition members of a UserEntity.
     * @param editionMembers: new value for editionMembers.
     */
    public void setEditionMembers(Set<EditionMemberEntity> editionMembers) {
        this.editionMembers = editionMembers;
    }
}
