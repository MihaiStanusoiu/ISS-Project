package transferable;

import java.util.Date;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@SuppressWarnings("all")
public class Session extends Idable<Integer> {

    /**
     * The session's name [String] [not null]
     */
    private String name;

    /**
     * The session's starting date. [day:month:year, hour:min]
     */
    private Date startDate;

    /**
     * The session's ending date. [day:month:year, hour:min]
     */
    private Date endDate;

    /**
     * The session's location. [city/country] [String]
     */
    private String location;

    /**
     * Additional information about session.
     */
    private String bio;

    /**
     * The number of available seats.
     */
    private Integer seats;

    /**
     * @param id The session's id [not null]
     * @param name The session's name [String] [not null]
     * @param startDate The session's starting date. [day:month:year, hour:min]
     * @param endDate The session's ending date. [day:month:year, hour:min]
     * @param location The session's location. [city/country] [String]
     * @param bio Additional information about session.
     * @param seats The number of available seats.
     */
    public Session(Integer id,
                   String name,
                   Date startDate,
                   Date endDate,
                   String location,
                   String bio,
                   Integer seats) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
        this.bio = bio;
        this.seats = seats;
    }

    /**
     * @param name The session's name [String] [not null]
     * @param startDate The session's starting date. [day:month:year, hour:min]
     * @param endDate The session's ending date. [day:month:year, hour:min]
     * @param location The session's location. [city/country] [String]
     * @param bio Additional information about session.
     * @param seats The number of available seats.
     */
    public Session(String name,
                   Date startDate,
                   Date endDate,
                   String location,
                   String bio,
                   Integer seats) {
        this(0, name, startDate, endDate, location, bio, seats);
    }


    /**
     * @param name The session's name [String] [not null]
     * @param startDate The session's starting date. [day:month:year, hour:min]
     * @param endDate The session's ending date. [day:month:year, hour:min]
     */
    public Session(String name, Date startDate, Date endDate) {
        this(0, name, startDate, endDate, "", "", 0);
    }

    /**
     * @return The session's name [String] [not null]
     */
    public String getName() {
        return name;
    }

    /**
     * @return The session's starting date. [day:month:year, hour:min]
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @return The session's ending date. [day:month:year, hour:min]
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @return The session's location. [city/country] [String]
     */
    public String getLocation() {
        return location;
    }

    /**
     * @return Additional information about session.
     */
    public String getBio() {
        return bio;
    }

    /**
     * @return The number of available seats.
     */
    public Integer getSeats() {
        return seats;
    }
}
