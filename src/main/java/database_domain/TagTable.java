package database_domain;

import javax.persistence.*;
import java.util.ArrayList;

/**
 * Name:         TagTable
 * Effect:       Corresponding class for the paper tags table in the database.
 * Date:         08.04.2017
 * Tested:       False
 * @author       Tanasie Luiza Maria
 * @version      1.0
 */

@Entity
@Table(name = "Tag")
@SuppressWarnings("unused")
public class TagTable {

    @Id
    @GeneratedValue
    @Column(name = "id_tag")
    private Integer id;

    @Column(name = "word")
    private String word;

    @OneToMany(mappedBy="idTag")
    private ArrayList<SubmissionTagTable> tagSubmissionTags = new ArrayList<>();

    public TagTable() { }

    /**
     * Effect: Return the id of tag.
     * @return [Integer]: returns the id of tag.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Effect: Sets the id of tag.
     * @param id [Integer]: new value for id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Effect: Return the word of the tag.
     * @return [String]: returns the word of the tag.
     */
    public String getWord() {
        return word;
    }

    /**
     * Effect: Sets the word of the tag.
     * @param word [Integer]: new value for the word
     */
    public void setWord(String word) {
        this.word = word;
    }

    /**
     * Effect: Return the submission tags of the tag.
     * @return [ArrayList<SubmissionTagTable>]: returns the submission tags of the tag.
     */
    public ArrayList<SubmissionTagTable> getTagSubmissionTags() {
        return tagSubmissionTags;
    }

    /**
     * Effect: Sets the submission tags of the tag.
     * @param tagSubmissionTags [ArrayList<SubmissionTagTable>]: new value for the submission tags
     */
    public void setTagSubmissionTags(ArrayList<SubmissionTagTable> tagSubmissionTags) {
        this.tagSubmissionTags = tagSubmissionTags;
    }

}
