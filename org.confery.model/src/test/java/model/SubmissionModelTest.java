package model;

import database.DatabaseLoaderFactory;
import database.DatabaseLoaderInterface;
import database.DatabaseLoaderType;
import domain.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import repository.RepositoryEntity;
import repository.RepositoryInterface;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class SubmissionModelTest {

    private UserModel userModel;
    private SubmissionModel submissionModel;
    private TagModel tagModel;
    private TopicModel topicModel;
    private EditionModel editionModel;
    private RepositoryInterface<EditionMemberEntity, Integer> repositoryMember;
    private RepositoryInterface<ConfigurationEditionMemberEntity, Integer> configurations;

    @Before
    public void setUp() throws Exception {
        DatabaseLoaderInterface loader = new DatabaseLoaderFactory().getLoader(DatabaseLoaderType.TEST);
        userModel = new UserModel(loader);
        submissionModel = new SubmissionModel(loader);
        tagModel = new TagModel(loader);
        topicModel = new TopicModel(loader);
        editionModel = new EditionModel(loader);
        repositoryMember = new RepositoryEntity<>(EditionMemberEntity.class, loader);
        configurations = new RepositoryEntity<>(ConfigurationEditionMemberEntity.class, loader);
    }

    @Test
    public void isAddingTagToSubmission() throws Exception {
        TagEntity tag = new TagEntity(1, "test");
        SubmissionEntity submission = new SubmissionEntity("Test", "abstract", "full-paper");
        submissionModel.add(submission);
        tagModel.add(tag);
        submissionModel.addTagTo(submission, tag);
        Assert.assertTrue(submissionModel.getElementById(submission.getId()).getSubmissionTags()
                .stream().findFirst().get().getTag().equals(tag));
    }

    @Test
    public void isAddingTopicToSubmission() throws Exception {
    }

    @Test
    public void isAddingAuthorToSubmission() throws Exception {
    }

    @Test
    public void isAddingOwnerToSubmission() throws Exception {
    }

    @Test
    public void isAddingReviewerToSubmission() throws Exception {
    }

    @Test
    public void isChangingOwnerOfSubmission() throws Exception {
    }

    @Test
    public void isRemovingAuthorFromSubmission() throws Exception {
    }

    @Test
    public void isRemovingTagFromSubmission() throws Exception {
    }

    @Test
    public void isRemovingTopicFromSubmission() throws Exception {
    }

    @Test
    public void isRemovingReviewerFromSubmission() throws Exception {
    }


    @Test
    public void isGettingAuthors() throws Exception {
        SubmissionEntity submission = new SubmissionEntity("Test", "abstract", "full-paper");
        UserEntity author = new UserEntity(1, "username", "password");
        userModel.add(author);
        submissionModel.add(submission);
        submissionModel.addAuthorTo(submission, author);
        Assert.assertTrue(submissionModel.getAuthorsFrom(submissionModel
                .getElementById(submission.getId())).get(0).equals(author));
    }

    @Test
    public void isGettingTags() throws Exception {
        SubmissionEntity submission = new SubmissionEntity("Test", "abstract", "full-paper");
        TagEntity tag = new TagEntity("test");
        tagModel.add(tag);
        submissionModel.add(submission);
        submissionModel.addTagTo(submission, tag);
        Assert.assertTrue(submissionModel.getTagsForm(submissionModel
                .getElementById(submission.getId())).get(0).equals(tag));

    }

    @Test
    public void isGettingTopics() throws Exception {
        SubmissionEntity submission = new SubmissionEntity("Test", "abstract", "full-paper");
        TopicEntity topic = new TopicEntity("test");
        topicModel.add(topic);
        submissionModel.add(submission);
        submissionModel.addTopicTo(submission, topic);
        Assert.assertTrue(submissionModel.getTopicsFrom(submissionModel
                .getElementById(submission.getId())).get(0).equals(topic));

    }

    @Test
    public void isGettingReviewers() throws Exception {
        SubmissionEntity submission = new SubmissionEntity("Test", "abstract", "full-paper");
        UserEntity user = new UserEntity(1, "username", "password");
        EditionEntity edition = new EditionEntity();
        EditionMemberEntity member = new EditionMemberEntity(1, user, edition,
                new ConfigurationEditionFactory(configurations).getConfiguration(MemberRole.EDITION_CHAIR));
        userModel.add(user);
        editionModel.add(edition);
        repositoryMember.add(member);
        submissionModel.add(submission);
//        submissionModel.addReviewerTo(submission, member);
//        Assert.assertTrue(submissionModel.getReviewersFrom(submissionModel
//                .getElementById(submission.getId())).get(0).equals(member));

    }

    @Test
    public void isGettingOwner() throws Exception {

    }
}