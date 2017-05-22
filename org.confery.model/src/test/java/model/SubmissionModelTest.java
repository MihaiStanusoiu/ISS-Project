package model;

import database.DatabaseLoaderFactory;
import database.DatabaseLoaderInterface;
import database.DatabaseLoaderType;
import domain.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class SubmissionModelTest {

    private SubmissionModel submissionModel;
    private TagModel tagModel;
    private TopicModel topicModel;
    private UserModel userModel;
    private EditionModel editionModel;

    @Before
    public void setUp() throws Exception {
        DatabaseLoaderInterface loader = new DatabaseLoaderFactory().getLoader(DatabaseLoaderType.TEST);
        submissionModel = new SubmissionModel(loader);
        tagModel = new TagModel(loader);
        topicModel = new TopicModel(loader);
        userModel = new UserModel(loader);
        editionModel = new EditionModel(loader);
    }

    @Test
    public void isAddingTagToSubmission() throws Exception {
        // declarations:
        SubmissionEntity submission = new SubmissionEntity("Test", "REVIEWED");
        TagEntity tag = new TagEntity("TagTest");
        // preconditions:
        submissionModel.add(submission);
        tagModel.add(tag);
        // when:
        submission = submissionModel.addTagTo(submission, tag);
        // then:
        assertTrue(submission.getTags().stream()
                .anyMatch(item -> item.getId().equals(tag.getId())));
    }

    @Test
    public void isAddingTopicToSubmission() throws Exception {
        // declarations:
        SubmissionEntity submission = new SubmissionEntity("Test", "REVIEWED");
        TopicEntity topic = new TopicEntity("Test");
        // preconditions:
        submissionModel.add(submission);
        topicModel.add(topic);
        // when:
        submission = submissionModel.addTopicTo(submission, topic);
        // then:
        assertTrue(submission.getTopics().stream()
                .anyMatch(item -> item.getId().equals(topic.getId())));
    }

    @Test
    public void isAddingAuthorToSubmission() throws Exception {
        // declarations:
        SubmissionEntity submission = new SubmissionEntity("Test", "REVIEWED");
        UserEntity user = new UserEntity("Test", "password");
        // preconditions:
        submissionModel.add(submission);
        userModel.add(user);
        // when:
        submission = submissionModel.addAuthorTo(submission, user);
        // then:
        assertTrue(submission.getAuthors().stream()
                .anyMatch(item -> item.getId().equals(user.getId())));
    }

    @Test
    public void isAddingOwnerToSubmission() throws Exception {
        // declarations:
        SubmissionEntity submission = new SubmissionEntity("Test", "REVIEWED");
        UserEntity user = new UserEntity("Test", "password");
        // preconditions:
        submissionModel.add(submission);
        userModel.add(user);
        // when:
        submission = submissionModel.addOwnerTo(submission, user);
        // then:
        assertTrue(submission.getOwner().equals(user));
    }

    @Test
    public void isAddingReviewerToSubmission() throws Exception {
        // declarations:
        UserEntity user = new UserEntity("Test", "password");
        EditionEntity edition =  new EditionEntity("New York");
        SubmissionEntity submission = new SubmissionEntity("Test", "REVIEWED");
        // preconditions:
        userModel.add(user);
        submissionModel.add(submission);
        editionModel.add(edition);
        edition = editionModel.addMemberTo(edition, user, MemberRole.EDITION_PC_MEMBER);
        EditionMemberEntity member = edition.getPcMemberEntities().get(0);
        // when:
        submission = submissionModel.addReviewerTo(submission, member);
        // then:
        assertTrue(submission.getReviewers().stream()
                .anyMatch(item -> item.getId().equals(user.getId())));
    }

    @Test
    public void isRemovingAuthorFromSubmission() throws Exception {
        // declarations:
        SubmissionEntity submission = new SubmissionEntity("Test", "REVIEWED");
        UserEntity user = new UserEntity("Test", "password");
        // preconditions:
        submissionModel.add(submission);
        userModel.add(user);
        // when:
        submission = submissionModel.addAuthorTo(submission, user);
        // then:
        assertEquals(submission.getAuthors().size(), 1);
        // when:
        submission = submissionModel.removeAuthorFrom(submission, user);
        // then:
        assertEquals(submission.getAuthors().size(), 0);
    }

    @Test
    public void isRemovingTagFromSubmission() throws Exception {
        // declarations:
        SubmissionEntity submission = new SubmissionEntity("Test", "REVIEWED");
        TagEntity tag = new TagEntity("TagTest");
        // preconditions:
        submissionModel.add(submission);
        tagModel.add(tag);
        // when:
        submission = submissionModel.addTagTo(submission, tag);
        // then:
        assertEquals(submission.getTags().size(), 1);
        // when:
        submission = submissionModel.removeTagFrom(submission, tag);
        // then:
        assertEquals(submission.getTags().size(), 0);
    }

    @Test
    public void isRemovingTopicFromSubmission() throws Exception {
        // declarations:
        SubmissionEntity submission = new SubmissionEntity("Test", "REVIEWED");
        TopicEntity topic = new TopicEntity("Test");
        // preconditions:
        submissionModel.add(submission);
        topicModel.add(topic);
        // when:
        submission = submissionModel.addTopicTo(submission, topic);
        // then:
        assertEquals(submission.getTopics().size(), 1);
        // when:
        submission = submissionModel.removeTopicFrom(submission, topic);
        // then:
        assertEquals(submission.getTopics().size(), 0);
    }

    @Test
    public void isRemovingReviewerFromSubmission() throws Exception {
        // declarations:
        UserEntity user = new UserEntity("Test", "password");
        EditionEntity edition =  new EditionEntity("New York");
        SubmissionEntity submission = new SubmissionEntity("Test", "REVIEWED");
        // preconditions:
        userModel.add(user);
        submissionModel.add(submission);
        editionModel.add(edition);
        edition = editionModel.addMemberTo(edition, user, MemberRole.EDITION_PC_MEMBER);
        EditionMemberEntity member = edition.getPcMemberEntities().get(0);
        // when:
        submission = submissionModel.addReviewerTo(submission, member);
        // then:
        assertEquals(submission.getReviewers().size(), 1);
        // when:
        submission = submissionModel.removeReviewerFrom(submission, member);
        // then:
        assertEquals(submission.getReviewers().size(), 0);
    }

}