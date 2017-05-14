package transferable;

import java.util.ArrayList;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@SuppressWarnings("all")
public class Submission extends Idable<Integer> {

    /**
     * The submission's name (usally the paper's name).
     */
    private String name;

    /**
     * The abstract's url [to pdf].
     */
    private String abstractUrl;

    /**
     * The paper's url [to pdf].
     */
    private String paperUrl;

    /**
     * The presentation's url [to ppt]
     */
    private String presentationUrl;

    /**
     * Tests if the submission was paid by the author.
     */
    private Boolean isPaid;

    /**
     * The list of reviews.
     */
    private ArrayList<Review> reviews;

    /**
     * The list of topics.
     */
    private ArrayList<Topic> topics;

    /**
     * The list of tags.
     */
    private ArrayList<Tag> tags;

    /**
     * The submission's authors.
     */
    private ArrayList<User> authors;

    /**
     * The submission's owner. [the user that created the submission]
     */
    private User owner;

    /**
     * @param id The submission's id [not null] -- from database
     * @param name The submission's name (usally the paper's name).
     * @param abstractUrl The abstract's url [to pdf].
     * @param paperUrl The paper's url [to pdf].
     * @param presentationUrl The presentation's url [to ppt]
     * @param paid Tests if the submission was paid by the author.
     * @param reviews The list of reviews.
     * @param topics The list of topics.
     * @param tags The list of tags.
     * @param authors The submission's authors.
     * @param owner The submission's owner. [the user that created the submission]
     */
    public Submission(Integer id,
                      String name,
                      String abstractUrl,
                      String paperUrl,
                      String presentationUrl,
                      Boolean paid,
                      ArrayList<Review> reviews,
                      ArrayList<Topic> topics,
                      ArrayList<Tag> tags,
                      ArrayList<User> authors,
                      User owner) {
        this.id = id;
        this.name = name;
        this.abstractUrl = abstractUrl;
        this.paperUrl = paperUrl;
        this.presentationUrl = presentationUrl;
        this.isPaid = paid;
        this.reviews = reviews;
        this.topics = topics;
        this.tags = tags;
        this.authors = authors;
        this.owner = owner;
    }

    /**
     * @param name The submission's name (usally the paper's name).
     * @param abstractUrl The abstract's url [to pdf].
     * @param paperUrl The paper's url [to pdf].
     * @param presentationUrl The presentation's url [to ppt]
     * @param paid Tests if the submission was paid by the author.
     * @param reviews The list of reviews.
     * @param topics The list of topics.
     * @param tags The list of tags.
     * @param authors The submission's authors.
     * @param owner The submission's owner. [the user that created the submission]
     */
    public Submission(String name,
                      String abstractUrl,
                      String paperUrl,
                      String presentationUrl,
                      Boolean paid,
                      ArrayList<Review> reviews,
                      ArrayList<Topic> topics,
                      ArrayList<Tag> tags,
                      ArrayList<User> authors,
                      User owner) {
        this(0, name, abstractUrl, paperUrl, presentationUrl,
                paid, reviews, topics, tags, authors, owner);
    }

    /**
     * @param name The submission's name (usally the paper's name).
     * @param abstractUrl The abstract's url [to pdf].
     * @param paid Tests if the submission was paid by the author.
     * @param owner The submission's owner. [the user that created the submission]
     */
    public Submission(String name, String abstractUrl, Boolean paid, User owner) {
        this(0, name, abstractUrl, "", "", paid, new ArrayList<>(), new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>(), owner);
    }

    /**
     * @return The submission's name (usally the paper's name).
     */
    public String getName() {
        return name;
    }

    /**
     * @return The abstract's url [to pdf].
     */
    public String getAbstractUrl() {
        return abstractUrl;
    }

    /**
     * @return The paper's url [to pdf].
     */
    public String getPaperUrl() {
        return paperUrl;
    }

    /**
     * @return The presentation's url [to ppt]
     */
    public String getPresentationUrl() {
        return presentationUrl;
    }

    /**
     * @return Tests if the submission was paid by the author.
     */
    public Boolean getPaid() {
        return isPaid;
    }

    /**
     * @return The list of reviews.
     */
    public ArrayList<Review> getReviews() {
        return reviews;
    }

    /**
     * @return The list of topics.
     */
    public ArrayList<Topic> getTopics() {
        return topics;
    }

    /**
     * @return The list of tags.
     */
    public ArrayList<Tag> getTags() {
        return tags;
    }


    /**
     * @return The submission's authors.
     */
    public ArrayList<User> getAuthors() {
        return authors;
    }

    /**
     * @return The submission's owner. [the user that created the submission]
     */
    public User getOwner() {
        return owner;
    }

}
