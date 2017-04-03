package domain;
import domain.Idable;

/**
 * Name:         Topic
 * Effect:       Class for the paper topics.
 * Date:         02.04.2017
 * Tested:       True
 * @author       Tiron Andreea - Ecaterina
 * @version      1.0
 */
public class Topic extends Idable<Integer> {
    private String content;

    public Topic(String content) {
        this(0, content);
    }

    public Topic(Integer id, String content) {
        this.id = id;
        this.content = content;
    }

    /**
     * Effect: Get the content of the topic.
     * @return content: the content.
     */
    public String getContent() {
        return content;
    }

    /**
     * Effect: Set the content of the topic.
     * @param content: [String] the new content.
     */
    public void setContent(String content) {
        this.content = content;
    }

}
