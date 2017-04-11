package databasedomain;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Name:         SectionTable
 * Effect:       Class for the db table Section.
 * Date:         08/04/2017
 * @author       Tiron Andreea- Ecaterina
 * @version      1.0
 */

@Entity
@Table(name = "Section")
@SuppressWarnings("unused")
public class SectionTable {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_section")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_conference")
    private Integer idConference;

    @Column(name = "name")
    private String name;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "location")
    private String location;

    @Column(name = "bio")
    private String bio;

    @Column(name = "seats")
    private Integer seats;

    public SectionTable() { }

    /**
     * Effect: Return the id of a section.
     * @return [Integer] : returns the id of a section.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Effect: Sets the id of a section.
     * @param id : new value for id.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Effect: Return the id of the conference.
     * @return [Integer] : returns the id of the conference.
     */
    public Integer getIdConference() {
        return idConference;
    }

    /**
     * Effect: Sets the id of a conference.
     * @param idConference : new value for id.
     */
    public void setIdConference(Integer idConference) {
        this.idConference = idConference;
    }

    /**
     * Effect: Return the name of a section.
     * @return [String] : returns the name of a section.
     */
    public String getName() {
        return name;
    }

    /**
     * Effect: Sets the name of a section.
     * @param name : new value for name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Effect: Return the starting date of a section.
     * @return [Date] : returns the starting date of a section.
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Effect: Sets the starting date of a section.
     * @param startDate : new value for start date.
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Effect: Return the ending date of a section.
     * @return [Date] : returns the ending date of a section.
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Effect: Sets the ending date of a section.
     * @param endDate : new value for end date.
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * Effect: Return the location of a section.
     * @return [String] : returns the location of a section.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Effect: Sets the location of a section.
     * @param location : new value for location.
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Effect: Return the bio of a section.
     * @return [String] : returns the bio of a section.
     */
    public String getBio() {
        return bio;
    }

    /**
     * Effect: Sets the bio of a section.
     * @param bio : new value of bio.
     */
    public void setBio(String bio) {
        this.bio = bio;
    }

    /**
     * Effect: Return the number of seats of a section.
     * @return [Integer] : returns the seats of a section.
     */
    public Integer getSeats() {
        return seats;
    }

    /**
     * Effect: Sets the number of seats of a section.
     * @param seats : new value for seats.
     */
    public void setSeats(Integer seats) {
        this.seats = seats;
    }

}
