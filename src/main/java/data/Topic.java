package data;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@SuppressWarnings("All")
public class Topic extends Idable<Integer> {

    /**
     * The word or group of words used to describe the topic.
     */
    private String content;

    /**
     * @param id The topic's id [not null] -- from database
     * @param content The word or group of words used to describe the topic.
     */
    public Topic(Integer id, String content) {
        this.id = id;
        this.content = content;
    }

    /**
     * @param content The word or group of words used to describe the topic.
     */
    public Topic(String content) {
        this(0, content);
    }

    /**
     * @return The word or group of words used to describe the topic.
     */
    public String getContent() {
        return content;
    }
}
