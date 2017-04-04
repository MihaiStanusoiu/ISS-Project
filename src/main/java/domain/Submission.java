package domain;
import java.util.ArrayList;

/**
 * Name:         Submission
 * Effect:       The class with the paper submissions.
 * Date:         02/04/2017
 * Tested:       True
 * @author       Tiron Andreea-Ecaterina
 * @version      1.0
 */

public class Submission extends Idable<Integer> {

    private String name;
    private StatusTypeSubmission status;
    private String abstractUrl;
    private String fullPaperUrl;
    private Boolean isPaid;
    private ArrayList<Integer> reviews;
    private ArrayList<Integer> topics;
    private ArrayList<Integer> tags;
    private Integer idConference;


    public Submission(Integer id,
                      String name,
                      StatusTypeSubmission status,
                      String abstractUrl,
                      String fullPaperUrl,
                      Boolean isPaid,
                      ArrayList<Integer> reviewers,
                      ArrayList<Integer> topics,
                      ArrayList<Integer> tags,
                      Integer idConference) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.abstractUrl = abstractUrl;
        this.fullPaperUrl = fullPaperUrl;
        this.isPaid = isPaid;
        this.reviews = reviewers;
        this.topics = topics;
        this.tags = tags;
        this.idConference = idConference;
    }

    public Submission(String name,
                      StatusTypeSubmission status,
                      String abstractUrl,
                      String fullPaperUrl,
                      Boolean isPaid,
                      ArrayList<Integer> reviewers,
                      ArrayList<Integer> topics,
                      ArrayList<Integer> tags,
                      Integer idConference) {
        this(0, name, status, abstractUrl, fullPaperUrl, isPaid, reviewers, topics, tags, idConference);
    }

    /**
     * Effect: Return the name of the conference.
     * @return [String] : returns the name.
     */
    public String getName() {
        return name;
    }

    /**
     * Effect: Return the status.
     * @return [StatusTYpeSubmission] : returns the status.
     */
    public StatusTypeSubmission getStatus() {
        return status;
    }

    /**
     * Effect: Return the url abstract paper.
     * @return [String] : returns the url abstract paper.
     */
    public String getAbstractUrl() {
        return abstractUrl;
    }

    /**
     * Effect: Return the url full paper.
     * @return [String] : returns the url full paper.
     */
    public String getFullPaperUrl() {
        return fullPaperUrl;
    }

    /**
     * Effect: Return whether the submission was paid
     * @return [Boolean] : returns True if it was paid, false otherwise.
     */
    public Boolean getPaid() {
        return isPaid;
    }

    /**
     * Effect: Return the reviews list.
     * @return [ArrayList<Integer>] : returns the reviews list.
     */
    public ArrayList<Integer> getReviews() {
        return reviews;
    }

    /**
     * Effect: Return the topics list.
     * @return [ArrayList<Integer>] : returns the topics list.
     */
    public ArrayList<Integer> getTopics() {
        return topics;
    }

    /**
     * Effect: Return the tags list.
     * @return [ArrayList<Integer>] : returns the tags list.
     */
    public ArrayList<Integer> getTags() {
        return tags;
    }

    /**
     * Effect: Return the conference id.
     * @return [Integer] : returns the conference id.
     */
    public Integer getIdConference() {
        return idConference;
    }

    /**
     * Effect: Add a new review.
     * @param review : [Review]  the value of the new review.
     */
    public void addReview(Review review){
        this.reviews.add(review.getId());
    }

    /**
     * Effect: remove a review.
     * @param review : [Review]  the value of the review.
     */
    public void removeReview(Review review){
        this.reviews.remove(review.getId());
    }

    /**
     * Effect: Add a new tag.
     * @param tag : [Tag]  the value of the new tag.
     */
    public void addTag(Tag tag){
        this.tags.add(tag.getId());
    }

    /**
     * Effect: Remove a tag.
     * @param tag : [Tag]  the value of the tag.
     */
    public void removeTag(Tag tag){
        this.tags.remove(tag.getId());
    }

    /**
     * Effect: Add a new topic.
     * @param topic : [Topic]  the value of the new topic.
     */
    public void addTopic(Topic topic) {
        this.topics.add(topic.getId());
    }

    /**
     * Effect: Remove a topic.
     * @param topic : [Topic]  the value of the topic.
     */
    public void removeTopic(Topic topic) {
        this.topics.remove(topic.getId());
    }

}
