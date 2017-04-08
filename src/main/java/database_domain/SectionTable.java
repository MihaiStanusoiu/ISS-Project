package database_domain;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Name:         SectionTable
 * Date:         08/04/2017
 * @author Tiron Andreea- Ecaterina
 * @version 1.0
 */

@Entity
@Table(name = "Section")
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

    public SectionTable(){
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdConference() {
        return idConference;
    }

    public void setIdConference(Integer idConference) {
        this.idConference = idConference;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

}
