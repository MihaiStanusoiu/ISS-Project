
package domain;

import nulldomain.NullEditionEntity;

import javax.persistence.*;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Tested True
 *
 * @author Tiron Andreea-Ecaterina & Alexandru Stoica
 * @version 1.1
 */


@Entity
@Table(name = "CONFERENCE")
public class ConferenceEntity implements Idable<Integer> {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID_CONFERENCE")
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "ACRONYM")
    private String acronym;

    @OneToMany(targetEntity = EditionEntity.class, fetch = FetchType.EAGER, mappedBy = "conference", cascade = CascadeType.ALL)
    private Set<EditionEntity> editions;

    private static final String DEFAULT_NAME = "Conference";
    private static final String DEFAULT_ACRONYM = "";
    private static final Integer DEFAULT_ID = 0;

    public ConferenceEntity() {
        this(DEFAULT_ID, DEFAULT_NAME, DEFAULT_ACRONYM);
    }

    /**
     * @param name    The conference's name
     * @param acronym The conference's acronym
     */
    public ConferenceEntity(String name, String acronym) {
        this(DEFAULT_ID, name, acronym);
    }

    /**
     * @param name The conference's name
     */
    public ConferenceEntity(String name) {
        this(DEFAULT_ID, name, DEFAULT_ACRONYM);
    }

    /**
     * @param id      The conference's id
     * @param name    The conference's name
     * @param acronym The conference's acronym
     */
    public ConferenceEntity(Integer id, String name, String acronym) {
        this.id = id;
        this.name = name;
        this.acronym = acronym;
        this.editions = new HashSet<>();
    }

    /**
     * Effect: Return the id of this conference.
     *
     * @return [Integer]: returns the id of conference.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Effect: Sets the id of a conference.
     *
     * @param id: new value for conference id.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Effect: Return the name of this conference.
     *
     * @return [String]: returns the name of conference.
     */
    public String getName() {
        return name;
    }

    /**
     * Effect: Sets the name of a conference.
     *
     * @param name: new value for conference name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Effect: Return the acronym of this conference.
     *
     * @return [String]: returns the acronym of conference.
     */
    public String getAcronym() {
        return acronym;
    }

    /**
     * Effect: Return the editions of a conference.
     *
     * @return Set<EditionEntity>: returns the editions of a conference.
     */
    public Set<EditionEntity> getEditions() {
        return editions;
    }

    public EditionEntity getLatestEdition() {
        return editions.stream().sorted(Comparator.comparing(EditionEntity::getStartDate))
                .findFirst().orElse(new NullEditionEntity());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConferenceEntity that = (ConferenceEntity) o;
        return id.equals(that.id) && name.equals(that.name) && acronym.equals(that.acronym) &&
                (editions != null ? editions.equals(that.editions) : that.editions == null);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + acronym.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return this.getName() + " " + this.getAcronym();
    }

}