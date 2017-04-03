package domain;
import domain.Idable;

/**
 * Name:         Tag
 * Effect:       Class for the paper tags.
 * Date:         02.04.2017
 * Tested:       True
 * @author       Tiron Andreea - Ecaterina
 * @version      1.0
 */
public class Tag extends Idable<Integer> {
    private String content;

    public Tag(String content) {
        this(0, content);
    }

    public Tag(Integer id, String content) {
        this.id = id;
        this.content = content;
    }

    /**
     * Effect: Get the content of the tag.
     * @return content: the content.
     */
    public String getContent() {
        return content;
    }

    /**
     * Effect: Set the content of the tag.
     * @param content: [String] the new content.
     */
    public void setContent(String content) {
        this.content = content;
    }

}
