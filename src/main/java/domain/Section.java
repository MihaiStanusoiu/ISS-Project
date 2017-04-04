package domain;

import java.util.Date;

/**
 * Name:         Section
 * Effect:       A conference section.
 * Date:         02/04/2017
 * Tested:       True
 * @author       Tiron Andreea- Ecaterina
 * @version      1.0
 */

public class Section extends Idable<Integer>{

    private String name;
    private Date startDate;
    private Date endDate;
    private String location;
    private String bio;
    private Integer seats;
    private Integer idConference;

    public Section(Integer id,
                   String name,
                   Date startDate,
                   Date endDate,
                   String location,
                   String bio,
                   Integer seats,
                   Integer idConference) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
        this.bio = bio;
        this.seats = seats;
        this.idConference = idConference;
    }

    public Section(String name,
                   Date startDate,
                   Date endDate,
                   String location,
                   String bio,
                   Integer seats,
                   Integer idConference){
        this(0, name, startDate, endDate, location, bio, seats, idConference);
    }

    /**
     * Effect: Return the name of the section.
     * @return [String] : returns the name.
     */
    public String getName() {
        return name;
    }

    /**
     * Effect: Return the start date.
     * @return [Date] : returns the date.
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Effect: Return the end date.
     * @return [Date] : returns the date.
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Effect: Return the location of the section.
     * @return [String] : returns the location.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Effect: Return the details of the section.
     * @return [String] : returns the bio.
     */
    public String getBio() {
        return bio;
    }

    /**
     * Effect: Return the number of seats available.
     * @return [Integer] : returns the seats.
     */
    public Integer getSeats() {
        return seats;
    }

    /**
     * Effect: Return the id of the conference.
     * @return [Integer] : returns the id.
     */
    public Integer getIdConference() {
        return idConference;
    }

}
