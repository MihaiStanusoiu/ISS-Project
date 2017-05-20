package transferable;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@SuppressWarnings("all")
public class Conference extends Idable<Integer> {

    /**
     * The conference's name.
     */
    private String name;

    /**
     * The conference's acronym for easy identification.
     */
    private String acronym;

    /**
     * The conference's edition for aditional information.
     */
    private Edition edition;

    /**
     * Creates a conference with empty/invalid id = 0.
     * @param name The conference's name [String] - not null
     * @param acronym The conference's acronym [String]
     * @param edition The conference's edition [Edition]
     */
    public Conference(String name, String acronym, Edition edition) {
        this(0, name, acronym, edition);
    }

    /**
     * Creates a valid conference.
     * @param id The conference's id [Integer] - not null, from database
     * @param name The conference's name [String] - not null
     * @param acronym The conference's acronym [String]
     * @param edition The conference's edition [Edition]
     */
    public Conference(Integer id, String name, String acronym, Edition edition) {
        this.id = id;
        this.name = name;
        this.acronym = acronym;
        this.edition = edition;
    }

    public Conference(Edition edition) {
        this(0, "", "", edition);
    }

    /**
     * @return The conference's name [String] -- not null
     */
    public String getName() {
        return name;
    }

    /**
     * @return The conference's acronym [String] - empty string.
     */
    public String getAcronym() {
        return acronym;
    }

    /**
     * @return The conference's edition for aditional information.
     */
    public Edition getEdition() {
        return edition;
    }
}
