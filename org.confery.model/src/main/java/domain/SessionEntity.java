package domain;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Name:         SessionEntity
 * Effect:       Class for the db table SessionEntity.
 * Date:         08/04/2017
 * Tested:       True
 * @author       Tiron Andreea- Ecaterina
 * @version      1.0
 */

@Entity
@Table(name = "SESSION")
@SuppressWarnings("unused")
public class SessionEntity implements Idable<Integer> {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID_SESSION")
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "START_DATE")
    private Date startDate;

    @Column(name = "END_DATE")
    private Date endDate;

    @Column(name = "LOCATION")
    private String location;

    @Column(name = "BIO")
    private String bio;

    @Column(name = "SEATS")
    private Integer seats;

    @ManyToOne
    @JoinColumn(name = "ID_EDITION")
    private EditionEntity editionSession;

    @OneToMany(mappedBy = "session", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<SessionMemberEntity> sessionMembers;

    public SessionEntity() { }

    public SessionEntity(String name, Date startDate, Date endDate, String location, String bio, Integer seats) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
        this.bio = bio;
        this.seats = seats;
    }

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

    /**
     * Effect: Return the edition.
     * @return [Integer] : returns the edition.
     */
    public EditionEntity getEdition() {
        return editionSession;
    }

    /**
     * Effect: Sets the id of an edition.
     * @param edition : new value for id.
     */
    public void setEdition(EditionEntity edition) {
        this.editionSession = edition;
    }

    /**
     * Effect: Return the sessionMembers of a Session.
     * @return [Set<SessionMemberEntity>]: returns the sessionMembers.
     */
    public Set<SessionMemberEntity> getSessionMembers() {
        return sessionMembers;
    }

    /**
     * Effect: Sets the sessionMembers of a Session.
     * @param sessionMembers: new value for the sessionMembers.
     */
    public void setSessionMembers(Set<SessionMemberEntity> sessionMembers) {
        this.sessionMembers = sessionMembers;
    }
}
