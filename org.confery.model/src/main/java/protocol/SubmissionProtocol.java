package protocol;

import domain.*;
import exception.SystemException;

import java.util.List;

/**
 * Tested: True
 *
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface SubmissionProtocol extends ModelInterface<SubmissionEntity, Integer> {

    /**
     * Adds a tag to a target submission.
     *
     * @param submission The target submission
     * @param tag        The added tag
     */
    void addTagTo(SubmissionEntity submission, TagEntity tag) throws SystemException;

    /**
     * Adds a topic to a target submission.
     *
     * @param submission The target submission
     * @param topic      The added tag
     */
    void addTopicTo(SubmissionEntity submission, TopicEntity topic) throws SystemException;

    /**
     * @param submission The target submission
     * @param author     The added author
     */
    void addAuthorTo(SubmissionEntity submission, UserEntity author) throws SystemException;

    /**
     * @param submission The target submission
     * @param owner      The added owner
     */
    void addOwnerTo(SubmissionEntity submission, UserEntity owner) throws SystemException;

    /**
     * @param submission The target submission
     * @param member     Member of the edition.
     */
    void addReviewerTo(SubmissionEntity submission, EditionMemberEntity member) throws SystemException;

    /**
     * @param submission The target submission
     * @param owner      The new owner
     */
    void changeOwnerOf(SubmissionEntity submission, UserEntity owner) throws SystemException;

    /**
     * @param submission The target submission
     * @param author     The author we need to remove
     * @return The author removed
     */
    UserEntity removeAuthorFrom(SubmissionEntity submission, UserEntity author) throws SystemException;

    /**
     * @param submission The target submission
     * @param tag        The tag we want to remove
     * @return The tag removed
     */
    TagEntity removeTagFrom(SubmissionEntity submission, TagEntity tag) throws SystemException;

    /**
     * @param submission The target submission
     * @param topic      The topic we want to remove
     * @return The topic removed
     */
    TopicEntity removeTopicFrom(SubmissionEntity submission, TopicEntity topic) throws SystemException;

    /**
     * @param submission The target submission
     * @param member     The reviewer we want to remove [member of the edition]
     * @return The reviewer removed
     */
    EditionMemberEntity removeReviewerFrom(SubmissionEntity submission, EditionMemberEntity member) throws SystemException;

    /**
     * Returns all the tags from the target submission.
     *
     * @param submission The target submission
     * @return All the tags from the target submission
     */
    List<TagEntity> getTagsForm(SubmissionEntity submission) throws SystemException;

    /**
     * Returns all the topics from the target submission.
     *
     * @param submission The target submission
     * @return All the topics from the target submission
     */
    List<TopicEntity> getTopicsFrom(SubmissionEntity submission) throws SystemException;

    /**
     * Returns all the reviewers from the target submission.
     *
     * @param submission The target submission
     * @return All the reviewers from the target submission
     */
    List<EditionMemberEntity> getReviewersFrom(SubmissionEntity submission) throws SystemException;

    /**
     * Returns all the authors from the target submission.
     *
     * @param submission The target submission
     * @return All the authors from the target submission
     */
    List<UserEntity> getAuthorsFrom(SubmissionEntity submission) throws SystemException;

    /**
     * Returns the owner of the target submission.
     *
     * @param submission The target submission
     * @return The owner of the target submission
     */
    UserEntity getOwnerFrom(SubmissionEntity submission) throws SystemException;
}
