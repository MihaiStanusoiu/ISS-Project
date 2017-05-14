package transferable;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@SuppressWarnings("all")
public class Tag extends Idable<Integer> {

    /**
     * The word or group of words used to describe the tag.
     */
    private String content;

    /**
     * @param content The word or group of words used to describe the tag.
     */
    public Tag(String content) {
        this(0, content);
    }

    /**
     * @param id The tag's id -- from database.
     * @param content The word or group of words used to describe the tag.
     */
    public Tag(Integer id, String content) {
        this.id = id;
        this.content = content;
    }

    /**
     * @return The word or group of words used to describe the tag.
     */
    public String getContent() {
        return content;
    }
}
