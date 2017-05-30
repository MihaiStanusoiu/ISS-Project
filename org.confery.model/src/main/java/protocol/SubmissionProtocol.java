package protocol;

import domain.*;
import exception.SystemException;

/**
 * Tested: True
 *
 * @author Alexandru Stoica
 * @version 1.0
 */


public interface SubmissionProtocol extends ModelInterface<SubmissionEntity, Integer> {

    /**
     * Adds a tag to a submission.
     *
     * @param submission The target submission [already in the database]
     * @param tag The target tag [already in the database]
     * @return The submission after we added the tag.
     * @throws SystemException If the tag already exists in the submission.
     */
    SubmissionEntity addTagTo(SubmissionEntity submission, TagEntity tag) throws SystemException;

    /**
     * Adds a topic to submission.
     *
     * @param submission The target submission [already in the database]
     * @param topic The target topic [already in the database]
     * @return The submission after we added the topic.
     * @throws SystemException If the topic already exists in the submission.
     */
    SubmissionEntity addTopicTo(SubmissionEntity submission, TopicEntity topic) throws SystemException;

    /**
     * Adds an author to a submission.
     *
     * @param submission The target submission [already in the database]
     * @param author The target author [already int he database]
     * @return The submission after we added the author.
     * @throws SystemException If the author is already in the submission.
     */
    SubmissionEntity addAuthorTo(SubmissionEntity submission, UserEntity author) throws SystemException;

    /**
     * Adds an owner to a submission.
     *
     * @param submission The target submission [already in the database]
     * @param owner The target owner [already in the database]
     * @return The submission after we added the owner
     * @throws SystemException If the owner is already set
     */
    SubmissionEntity addOwnerTo(SubmissionEntity submission, UserEntity owner) throws SystemException;

    /**
     * Adds a reviewer to submission
     *
     * @param submission The target submission [already in the database]
     * @param member The target member [already in the database]
     * @return The submission after we added the reviewer
     * @throws SystemException If the reviewer is already a part of the submission
     */
    SubmissionEntity addReviewerTo(SubmissionEntity submission, EditionMemberEntity member) throws SystemException;

    /**
     * Removes an author from a submission.
     *
     * @param submission The target submission [already in the database]
     * @param author The target author [already in the database]
     * @return The submission after we remove the author.
     * @throws SystemException If the author is not a part of the submission.
     */
    SubmissionEntity removeAuthorFrom(SubmissionEntity submission, UserEntity author) throws SystemException;

    /**
     * Removes an tag from a submission.
     *
     * @param submission The target submission [already in the database]
     * @param tag The target tag [already in the database]
     * @return The submission after we remove the tag.
     * @throws SystemException If the tag is not a part of the submission.
     */
    SubmissionEntity removeTagFrom(SubmissionEntity submission, TagEntity tag) throws SystemException;

    /**
     * Removes an topic from a submission.
     *
     * @param submission The target submission [already in the database]
     * @param topic The target topic [already in the database]
     * @return The submission after we remove the topic.
     * @throws SystemException If the topic is not a part of the submission.
            */
    SubmissionEntity removeTopicFrom(SubmissionEntity submission, TopicEntity topic) throws SystemException;

    /**
     * Removes an member from a submission.
     *
     * @param submission The target submission [already in the database]
     * @param member The target member [already in the database]
     * @return The submission after we remove the member.
     * @throws SystemException If the member is not a part of the submission.
     */
    SubmissionEntity removeReviewerFrom(SubmissionEntity submission, EditionMemberEntity member) throws SystemException;

}
