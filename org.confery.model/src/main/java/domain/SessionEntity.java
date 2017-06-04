
package domain;

import nulldomain.NullUserEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static javax.persistence.GenerationType.IDENTITY;
import static utils.Try.runFunction;

/**
 * Tested: True
 *
 * @author Tiron Andreea-Ecaterina & Alexandru Stoica
 * @version 1.0
 */

@Entity
@Table(name = "SESSION")
@SuppressWarnings("unused")
public class SessionEntity implements Idable<Integer>, Cloneable {


    @Id
    @GeneratedValue(strategy = IDENTITY)
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

    @ManyToOne(targetEntity = EditionEntity.class)
    @JoinColumn(name = "ID_EDITION", updatable = false)
    private EditionEntity edition;

    @OneToMany(targetEntity = SessionMemberEntity.class, mappedBy = "session",
            fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<SessionMemberEntity> members;

    private static final Integer DEFAULT_ID = 0;
    private static final Date DEFAULT_DATE = new Date();
    private static final String DEFAULT_LOCATION = "Unknown";
    private static final String DEFAULT_BIO = "";
    private static final Integer DEFAULT_SEATS = 0;

    /**
     * @apiNote Don't use this constructor [it's for testing only]
     */
    @Deprecated
    public SessionEntity() {
        this("");
    }

    public SessionEntity(String name) {
        this(name, DEFAULT_DATE, DEFAULT_DATE,
                DEFAULT_LOCATION, DEFAULT_BIO, DEFAULT_SEATS);
    }

    public SessionEntity(String name, Date startDate, Date endDate,
                         String location, String bio, Integer seats) {
        this(DEFAULT_ID, name, startDate, endDate, location, bio, seats);
    }

    public SessionEntity(String name, Integer seats) {
        this(DEFAULT_ID, name, DEFAULT_DATE, DEFAULT_DATE,
                DEFAULT_LOCATION, DEFAULT_BIO, seats);
    }

    public SessionEntity(Integer id, String name, Date startDate, Date endDate,
                         String location, String bio, Integer seats) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
        this.bio = bio;
        this.seats = seats;
    }

    /**
     * Effect: Return the id of a section.
     *
     * @return [Integer] : returns the id of a section.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Effect: Sets the id of a section.
     *
     * @param id : new value for id.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Effect: Return the name of a section.
     *
     * @return [String] : returns the name of a section.
     */
    public String getName() {
        return name;
    }

    /**
     * Effect: Return the starting date of a section.
     *
     * @return [Date] : returns the starting date of a section.
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Effect: Return the ending date of a section.
     *
     * @return [Date] : returns the ending date of a section.
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Effect: Return the location of a section.
     *
     * @return [String] : returns the location of a section.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Effect: Return the bio of a section.
     *
     * @return [String] : returns the bio of a section.
     */
    public String getBio() {
        return bio;
    }


    /**
     * Effect: Return the number of seats of a section.
     *
     * @return [Integer] : returns the seats of a section.
     */
    public Integer getSeats() {
        return seats;
    }

    /**
     * Effect: Return the edition.
     *
     * @return [Integer] : returns the edition.
     */
    public EditionEntity getEdition() {
        return edition;
    }

    /**
     * Effect: Sets the id of an edition.
     *
     * @param edition : new value for id.
     */
    public void setEdition(EditionEntity edition) {
        this.edition = edition;
    }

    /**
     * Effect: Return the members of a Session.
     *
     * @return [Set<SessionMemberEntity>]: returns the members.
     */
    public Set<SessionMemberEntity> getMembers() {
        return members;
    }

    /**
     * Returns all the speakers at the session.
     *
     * @return All the speakers attending the session.
     */
    public List<UserEntity> getSpeakers() {
        return members.stream()
                .filter(member -> member.getConfiguration().getSpeaker().equals(true))
                .map(SessionMemberEntity::getUser)
                .collect(Collectors.toList());
    }

    /**
     * Returns all the listeners at the session.
     *
     * @return All the listeners attending the session
     */
    public List<UserEntity> getListeners() {
        return members.stream()
                .filter(member -> member.getConfiguration().getListener().equals(true))
                .map(SessionMemberEntity::getUser)
                .collect(Collectors.toList());
    }

    /**
     * Returns the chair of the session.
     *
     * @return The chair of the session or null if the chair is not set
     */
    public UserEntity getChair() {
        return members.stream()
                .filter(member -> member.getConfiguration().getChair().equals(true))
                .findFirst()
                .map(SessionMemberEntity::getUser)
                .orElse(new NullUserEntity());
    }

    /**
     * Returns the number of available sets at the current moment.
     *
     * @return The number of available seats
     */
    public Integer getAvailableSets() {
        return seats - getListeners().size() - getSpeakers().size() -
                (Objects.nonNull(runFunction(this::getChair).or(null)) ? 1 : 0);
    }

    public SessionEntity getClone() {
        return new SessionEntity(id, name, startDate, endDate, location, bio, seats);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SessionEntity that = (SessionEntity) o;
        return id.equals(that.id) && (name != null ? name.equals(that.name) : that.name == null) &&
                (startDate != null ? startDate.equals(that.startDate) : that.startDate == null) &&
                (endDate != null ? endDate.equals(that.endDate) : that.endDate == null) &&
                (location != null ? location.equals(that.location) : that.location == null) &&
                (bio != null ? bio.equals(that.bio) : that.bio == null) &&
                (seats != null ? seats.equals(that.seats) : that.seats == null);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (bio != null ? bio.hashCode() : 0);
        result = 31 * result + (seats != null ? seats.hashCode() : 0);
        return result;
    }
}
