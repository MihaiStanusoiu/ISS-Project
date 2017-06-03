package transfarable;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */
public class Session implements Serializable, IdableTransfer<Integer> {

    private Integer id;
    private String name;
    private Date startDate;
    private Date endDate;
    private String location;
    private String bio;
    private Integer seats;

    public Session(Integer id, String name, Date startDate, Date endDate, String location, String bio, Integer seats) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
        this.bio = bio;
        this.seats = seats;
    }

    /**
     * Getter for property 'id'.
     *
     * @return Value for property 'id'.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Getter for property 'name'.
     *
     * @return Value for property 'name'.
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for property 'startDate'.
     *
     * @return Value for property 'startDate'.
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Getter for property 'endDate'.
     *
     * @return Value for property 'endDate'.
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Getter for property 'location'.
     *
     * @return Value for property 'location'.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Getter for property 'bio'.
     *
     * @return Value for property 'bio'.
     */
    public String getBio() {
        return bio;
    }

    /**
     * Getter for property 'seats'.
     *
     * @return Value for property 'seats'.
     */
    public Integer getSeats() {
        return seats;
    }
}
