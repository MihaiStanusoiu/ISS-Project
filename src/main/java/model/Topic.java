package model;

/**
 * Name:         Idable
 * Effect:       Interface class for id.
 * Date:         02.04.2017
 * Tested:       False
 * @author       Tiron Andreea - Ecaterina
 * @version      1.0
 */
public class Topic extends Idable<Integer>{
    private String content;

    /**
     * Effect: Get the content of the topic.
     * @return content:
     */
    public String getContent() {
        return content;

    }

    public void setContent(String content) {
        this.content = content;
    }

}
