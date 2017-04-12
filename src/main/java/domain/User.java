package domain;

import java.util.ArrayList;

/**
 * Name:        User
 * Effect:      Class for basic user type
 * Date:        4/2/2017
 * Tested:      True
 *
 * @author      {Stanusoiu Mihai-Teodor}
 * @version     1.0
 */

public class User extends Idable<Integer> implements UserInterface {

    protected String username;
    protected String password;
    protected String email;
    protected String name;
    protected String website;
    protected String bio;
    protected String location;

    /**
     * Creates an empty / default User.
     */
    @SuppressWarnings("all")
    public User() {
        this(0, "", "", "", "", "", "", "");
    }

    /**
     * Creates an instance of type User.
     * @param id The user's id.
     * @param username The user's name.
     * @param password The user's password.
     * @param email The user's email.
     * @param name The user's real name.
     * @param website The user's website.
     * @param bio The user's bio description.
     * @param location The user's location.
     */
    @SuppressWarnings("all")
    public User( Integer id,
                 String username,
                 String password,
                 String email,
                 String name,
                 String website,
                 String bio,
                 String location) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.website = website;
        this.bio = bio;
        this.location = location;
    }

    /**
     * Effect: Return the username.
     * @return String : returns the username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Effect: Sets the username to the given value
     * @param username: new value for username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Effect: Getter for email.
     * @return String : returns the email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Effect: Sets the email to the given value
     * @param email: new value for username
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Effect: Getter for name.
     * @return String : returns the name.
     */
    public String getName() {
        return name;
    }

    /**
     * Effect: Sets the name to the given value
     * @param name: new value for username
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Effect: Getter for website link.
     * @return String : returns the website.
     */
    public String getWebsite() {
        return website;
    }

    /**
     * Effect: Sets the website to the given value
     * @param website: new value for username
     */
    public void setWebsite(String website) {
        this.website = website;
    }

    /**
     * Effect: Getter for bio.
     * @return String : returns the bio.
     */
    public String getBio() {
        return bio;
    }

    /**
     * Effect: Sets the bio to the given value
     * @param bio: new value for username
     */
    public void setBio(String bio) {
        this.bio = bio;
    }

    /**
     * Effect: Getter for location.
     * @return String : returns the location.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Effect: Sets the location to the given value
     * @param location: new value for username
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Effect: Sets the password to the given value
     * @param password: new value for username
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Effect: Returns UserType.USER
     * @return UserType : returns the corresponding user type.
     */
    @Override
    public UserType getType() {
        return UserType.USER;
    }

    /**
     * Effect: Gets the permissions assigned to the corresponding user type (UserType.USER)
     * @return ArrayList<Permission>: returns the array of permissions.
     */
    public ArrayList<Permission> getPermissions() {
        return UserType.USER.getPermissions();
    }
}
