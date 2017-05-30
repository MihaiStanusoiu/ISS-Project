package model;

import database.DatabaseLoaderInterface;
import domain.*;
import exception.ModelException;
import exception.SystemException;
import protocol.SubmissionProtocol;
import repository.RepositoryEntity;
import repository.RepositoryInterface;

import static utils.Conditional.basedOn;

/**
 * Tested: True
 *
 * @author Alexandru Stoica
 * @version 1.0
 */


public class SubmissionModel extends Model<SubmissionEntity, Integer>
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
    public SubmissionEntity addTagTo(SubmissionEntity submission, TagEntity tag) throws SystemException {
        basedOn(submission.getTags().stream().noneMatch(item -> item.getId().equals(tag.getId())))
                .orThrow(new ModelException("The Tag Already Exists In The Submission!"));
        SubmissionTagEntity submissionTag = new SubmissionTagEntity(submission, tag);
        repositorySubmissionTag.add(submissionTag);
        return getElementById(submission.getId());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SubmissionEntity addTopicTo(SubmissionEntity submission, TopicEntity topic) throws SystemException {
        basedOn(submission.getTopics().stream().noneMatch(item -> item.getId().equals(topic.getId())))
                .orThrow(new ModelException("The Topic Already Exists In The Submission!"));
        SubmissionTopicEntity submissionTopic = new SubmissionTopicEntity(submission, topic);
        repositorySubmissionTopic.add(submissionTopic);
        return getElementById(submission.getId());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SubmissionEntity addAuthorTo(SubmissionEntity submission, UserEntity author) throws SystemException {
        basedOn(submission.getAuthors().stream().noneMatch(item -> item.getId().equals(author.getId())))
                .orThrow(new ModelException("The Author Already Exists In The Submission!"));
        AuthorSubmissionEntity authorSubmission = new AuthorSubmissionEntity(submission, author);
        repositorySubmissionAuthor.add(authorSubmission);
        return getElementById(submission.getId());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SubmissionEntity addOwnerTo(SubmissionEntity submission, UserEntity owner) throws SystemException {
        basedOn(submission.getOwner() == null).orThrow(new ModelException("Owner Already Set!"));
        AuthorSubmissionEntity authorSubmission = new AuthorSubmissionEntity(submission, owner, Boolean.TRUE);
        repositorySubmissionAuthor.add(authorSubmission);
        return getElementById(submission.getId());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SubmissionEntity addReviewerTo(SubmissionEntity submission, EditionMemberEntity member) throws SystemException {
        basedOn(submission.getReviewers().stream().noneMatch(item -> item.getId().equals(member.getId())))
                .orThrow(new ModelException("The Reviewer Already Exists In The Submission!"));
        ReviewerEntity reviewer = new ReviewerEntity(submission, member);
        repositoryReviewer.add(reviewer);
        return getElementById(submission.getId());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SubmissionEntity removeAuthorFrom(SubmissionEntity submission, UserEntity author) throws SystemException {
        repositorySubmissionAuthor.delete(submission.getAuthors().stream()
                .findFirst().filter(item -> item.getId().equals(author.getId()))
                .orElseThrow(() -> new ModelException("404 Author Not Found!")).getId());
        return getElementById(submission.getId());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SubmissionEntity removeTagFrom(SubmissionEntity submission, TagEntity tag) throws SystemException {
        repositorySubmissionTag.delete(submission.getTags().stream()
            .findFirst().filter(item -> item.getId().equals(tag.getId()))
            .orElseThrow(() -> new ModelException("404 Tag Not Found!")).getId());
        return getElementById(submission.getId());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SubmissionEntity removeTopicFrom(SubmissionEntity submission, TopicEntity topic) throws SystemException {
        repositorySubmissionTopic.delete(submission.getTopics().stream()
                .findFirst().filter(item -> item.getId().equals(topic.getId()))
                .orElseThrow(() -> new ModelException("404 Topic Not Found!")).getId());
        return getElementById(submission.getId());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SubmissionEntity removeReviewerFrom(SubmissionEntity submission, EditionMemberEntity member) throws SystemException {
        repositoryReviewer.delete(submission.getReviewerEntities().stream()
                .findFirst().filter(item -> item.getId().equals(member.getId()))
                .orElseThrow(() -> new ModelException("404 Reviewer Not Found!")).getId());
        return getElementById(submission.getId());
    }

}
