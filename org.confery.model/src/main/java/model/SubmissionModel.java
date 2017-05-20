package model;

import database.DatabaseLoaderInterface;
import domain.*;
import exception.ModelException;
import exception.SystemException;
import protocol.SubmissionProtocol;
import repository.RepositoryEntity;
import repository.RepositoryInterface;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Tested: True
 *
 * @author Alexandru Stoica
 * @version 1.0
 */

public class SubmissionModel
        extends Model<SubmissionEntity, Integer>
        implements SubmissionProtocol {

    private RepositoryInterface<SubmissionTagEntity, Integer> repositorySubmissionTag;
    private RepositoryInterface<SubmissionTopicEntity, Integer> repositorySubmissionTopic;
    private RepositoryInterface<AuthorSubmissionEntity, Integer> repositorySubmissionAuthor;
    private RepositoryInterface<ReviewerEntity, Integer> repositoryReviewer;

    public SubmissionModel(DatabaseLoaderInterface loader) {
        super(SubmissionEntity.class, loader);
        repositorySubmissionTag = new RepositoryEntity<>(SubmissionTagEntity.class, loader);
        repositorySubmissionTopic = new RepositoryEntity<>(SubmissionTopicEntity.class, loader);
        repositorySubmissionAuthor = new RepositoryEntity<>(AuthorSubmissionEntity.class, loader);
        repositoryReviewer = new RepositoryEntity<>(ReviewerEntity.class, loader);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addTagTo(SubmissionEntity submission, TagEntity tag) throws SystemException {
        SubmissionTagEntity submissionTag = new SubmissionTagEntity(submission, tag);
        repositorySubmissionTag.add(submissionTag);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addTopicTo(SubmissionEntity submission, TopicEntity topic) throws SystemException {
        SubmissionTopicEntity submissionTopic = new SubmissionTopicEntity(submission, topic);
        repositorySubmissionTopic.add(submissionTopic);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addAuthorTo(SubmissionEntity submission, UserEntity author) throws SystemException {
        AuthorSubmissionEntity authorSubmission = new AuthorSubmissionEntity(submission, author);
        repositorySubmissionAuthor.add(authorSubmission);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addOwnerTo(SubmissionEntity submission, UserEntity owner) throws SystemException {
        AuthorSubmissionEntity authorSubmission = new AuthorSubmissionEntity(submission, owner, Boolean.TRUE);
        repositorySubmissionAuthor.add(authorSubmission);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addReviewerTo(SubmissionEntity submission, EditionMemberEntity member) throws SystemException {
        ReviewerEntity reviewerSubmission = new ReviewerEntity(submission, member);
        repositoryReviewer.add(reviewerSubmission);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void changeOwnerOf(SubmissionEntity submission, UserEntity owner) throws SystemException {
        repositorySubmissionAuthor.update(repositorySubmissionAuthor.getAll().stream()
                        .filter(author -> author.getSubmission().equals(submission)
                                && author.getOwner().equals(Boolean.TRUE))
                        .findFirst().orElseThrow(() -> new ModelException("404 Owner Not Found!")),
                new AuthorSubmissionEntity(submission, owner, Boolean.TRUE));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserEntity removeAuthorFrom(SubmissionEntity submission, UserEntity author) throws SystemException {
        return repositorySubmissionAuthor.delete(repositorySubmissionAuthor.getAll().stream()
                .filter(item -> item.getSubmission().equals(submission) &&
                        item.getAuthor().equals(author))
                .findFirst().orElseThrow(() -> new ModelException("404 Author Not Found!"))
                .getId()).getAuthor();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TagEntity removeTagFrom(SubmissionEntity submission, TagEntity tag) throws SystemException {
        return repositorySubmissionTag.delete(repositorySubmissionTag.getAll().stream()
                .filter(item -> item.getSubmission().equals(submission) && item.getTag().equals(tag))
                .findFirst().orElseThrow(() -> new ModelException("404 Tag Not Found!")).getId()).getTag();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TopicEntity removeTopicFrom(SubmissionEntity submission, TopicEntity topic) throws SystemException {
        return repositorySubmissionTopic.delete(repositorySubmissionTopic.getAll().stream()
                .filter(item -> item.getSubmission().equals(submission) && item.getTopic().equals(topic))
                .findFirst().orElseThrow(() -> new ModelException("404 Topic Not Found!")).getId()).getTopic();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EditionMemberEntity removeReviewerFrom(SubmissionEntity submission, EditionMemberEntity member) throws SystemException {
        return repositoryReviewer.delete(repositoryReviewer.getAll().stream()
                .filter(item -> item.getSubmission().equals(submission) && item.getMember().equals(member))
                .findFirst().orElseThrow(() -> new ModelException("404 Reviewer Not Found!")).getId()).getMember();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<TagEntity> getTagsForm(SubmissionEntity submission) throws SystemException {
        return repositorySubmissionTag.getAll().stream()
                .filter(tag -> tag.getSubmission().equals(submission))
                .map(SubmissionTagEntity::getTag)
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<TopicEntity> getTopicsFrom(SubmissionEntity submission) throws SystemException {
        return repositorySubmissionTopic.getAll().stream()
                .filter(topic -> topic.getSubmission().equals(submission))
                .map(SubmissionTopicEntity::getTopic)
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<EditionMemberEntity> getReviewersFrom(SubmissionEntity submission) throws SystemException {
        return repositoryReviewer.getAll().stream()
                .filter(reviewer -> reviewer.getSubmission().equals(submission))
                .map(ReviewerEntity::getMember)
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UserEntity> getAuthorsFrom(SubmissionEntity submission) throws SystemException {
        return repositorySubmissionAuthor.getAll().stream()
                .filter(author -> author.getSubmission().equals(submission))
                .map(AuthorSubmissionEntity::getAuthor)
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserEntity getOwnerFrom(SubmissionEntity submission) throws SystemException {
        return repositorySubmissionAuthor.getAll().stream()
                .filter(author -> author.getSubmission().equals(submission) &&
                        author.getOwner().equals(Boolean.TRUE))
                .map(AuthorSubmissionEntity::getAuthor)
                .findFirst().orElseThrow(() -> new ModelException("404 Owner Not Found!"));
    }

}
