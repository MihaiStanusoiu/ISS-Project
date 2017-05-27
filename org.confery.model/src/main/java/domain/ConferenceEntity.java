
package domain;

import javax.persistence.*;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;
import static utils.Comparator.checkClass;
import static utils.Comparator.checkObjects;

/**
 * Tested True
 *
 * @author Tiron Andreea-Ecaterina & Alexandru Stoica
 * @version 1.1
 */

@Entity
@Table(name = "CONFERENCE")
@SuppressWarnings("unused")
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
     * Effect: Sets the acronym of a conference.
     *
     * @param acronym: new value for conference acronym.
     */
    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    /**
     * Effect: Return the editions of a conference.
     *
     * @return Set<EditionEntity>: returns the editions of a conference.
     */
    public Set<EditionEntity> getEditions() {
        return editions;
    }

    /**
     * Effect: Sets the editions of this conference.
     *
     * @param editions: new value for conference editions.
     */
    public void setEditions(Set<EditionEntity> editions) {
        this.editions = editions;
    }

    public EditionEntity getLatestEdition() {
        // TODO
        return editions.stream().findFirst().orElse(new EditionEntity());
    }

    @Override
    public boolean equals(Object obj) {
        return checkObjects((left, right) -> left.getId().equals(right.getId()) &&
                        left.getName().equals(right.getName()) && left.getAcronym().equals(right.getAcronym()),
                this, checkClass(obj, this.getClass()));
    }

    @Override
    public String toString() {
        return this.getName() + " " + this.getAcronym();
    }

}