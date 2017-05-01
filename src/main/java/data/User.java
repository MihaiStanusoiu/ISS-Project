package data;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */


@SuppressWarnings("All")
public class User extends Idable<Integer> {

    /**
     * The user's name in the system. [unique] [not null]
     */
    private String username;

    /**
     * The user's password [at least 8 chars]
     */
    private String password;

    /**
     * The user's email address.
     */
    private String email;

    /**
     * The user's real name [the display name]
     */
    private String name;

    /**
     * The user's website url.
     */
    private String website;

    /**
     * Additional information about user.
     */
    private String bio;

    /**
     * The user's location [city/country]
     */
    private String location;

    /**
     * The user's type in our system -- provides the user's permissions.
     */
    private UserTypeInterface type;

    /**
     * @param id The user's id [unique] [not null]
     * @param username The user's name in the system. [unique] [not null]
     * @param password The user's password [at least 8 chars]
     * @param email The user's email address.
     * @param name The user's real name [the display name]
     * @param website The user's website url.
     * @param bio Additional information about user.
     * @param location The user's location [city/country]
     * @param type The user's type in our system -- provides the user's permissions.
     */
    public User(Integer id,
                String username,
                String password,
                String email,
                String name,
                String website,
                String bio,
                String location,
                UserTypeInterface type) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.website = website;
        this.bio = bio;
        this.location = location;
        this.type = type;
    }

    /**
     * @param username The user's name in the system. [unique] [not null]
     * @param password The user's password [at least 8 chars]
     * @param email The user's email address.
     * @param name The user's real name [the display name]
     * @param website The user's website url.
     * @param bio Additional information about user.
     * @param location The user's location [city/country]
     * @param type The user's type in our system -- provides the user's permissions.
     */
    public User(String username,
                String password,
                String email,
                String name,
                String website,
                String bio,
                String location,
                UserTypeInterface type) {
        this(0, username, password, email, name, website, bio, location, type);
    }

    /**
     * @param username The user's name in the system. [unique] [not null]
     * @param password The user's password [at least 8 chars]
     * @param email The user's email address.
     * @param name The user's real name [the display name]
     */
    public User(String username, String password, String email, String name) {
        this(0, username, password, email, name, "", "", "", UserType.REGULAR);
    }

    /**
     * @return The user's name in the system. [unique] [not null]
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return The user's password [at least 8 chars]
     */
    public String getPassword() {
        return password;
    }

    /**
     * @return The user's email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return The user's real name [the display name]
     */
    public String getName() {
        return name;
    }

    /**
     * @return The user's website url.
     */
    public String getWebsite() {
        return website;
    }

    /**
     * @return Additional information about user.
     */
    public String getBio() {
        return bio;
    }

    /**
     * @return The user's location [city/country]
     */
    public String getLocation() {
        return location;
    }

    /**
     * @return The user's type in our system -- provides the user's permissions.
     */
    public UserTypeInterface getType() {
        return type;
    }
}
