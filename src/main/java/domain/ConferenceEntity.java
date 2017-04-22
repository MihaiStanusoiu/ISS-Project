package domain;

import javax.persistence.*;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Name:         The conference class that can hold multiple editions.
 * Effect:       ConferenceEntity with general data.
 * Date:         22/04/2017
 * Tested:       False
 *
 * @author Tiron Andreea- Ecaterina
 * @version 1.0
 */
@Entity
@Table(name = "CONFERENCE")
@SuppressWarnings("unused")
public class ConferenceEntity implements Idable<Integer>{

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID_CONFERENCE")
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "ACRONYM")
    private String acronym;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "conference",cascade = CascadeType.ALL)
    private Set<EditionEntity> editions;

    /**
     * Empty constructor
     */
    public ConferenceEntity() { }

    public ConferenceEntity(String name, String acronym) {
        this.name = name;
        this.acronym = acronym;
    }

    /**
     * Effect: Return the id of this conference.
     * @return [Integer]: returns the id of conference.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Effect: Sets the id of a conference.
     * @param id: new value for conference id.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Effect: Return the name of this conference.
     * @return [String]: returns the name of conference.
     */
    public String getName() {
        return name;
    }

    /**
     * Effect: Sets the name of a conference.
     * @param name: new value for conference name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Effect: Return the acronym of this conference.
     * @return [String]: returns the acronym of conference.
     */
    public String getAcronym() {
        return acronym;
    }

    /**
     * Effect: Sets the acronym of a conference.
     * @param acronym: new value for conference acronym.
     */
    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public Set<EditionEntity> getEditions() {
        return editions;
    }

    public void setEditions(Set<EditionEntity> editions) {
        this.editions = editions;
    }

}