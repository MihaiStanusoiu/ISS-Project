package validator;

import domain.*;
import exception.RepositoryException;
import org.junit.Assert;
import org.junit.Test;

/**
 * Name:         ValidatorRepositoryBehaviourFactoryTest
 * Effect:       Test for behaviour factory
 * Date:         05/06/2017
 *
 * @author Tanasie Luiza Maria
 * @version 1.0
 */
@SuppressWarnings("all")
public class ValidatorRepositoryBehaviourFactoryTest {
    @Test
    public void getBehaviourUser(){
        ValidatorRepositoryBehaviour behaviourUser = null;
        try {
            behaviourUser = new ValidatorRepositoryBehaviourFactory().getBehaviour(UserEntity.class);
            Assert.assertTrue(behaviourUser.getClass().equals(ValidatorRepositoryBehaviourUserEntity.class));
        } catch (RepositoryException e) {
            Assert.assertTrue(e.getMessage().contains("Cannot get behaviour for given class type"));
        }
    }

    @Test
    public void getBehaviourEdition() {
        ValidatorRepositoryBehaviour behaviourEdition = null;
        try {
            behaviourEdition = new ValidatorRepositoryBehaviourFactory().getBehaviour(EditionEntity.class);
            Assert.assertTrue(behaviourEdition.getClass().equals(ValidatorRepositoryBehaviourEditionEntity.class));
        } catch (RepositoryException e) {
            Assert.assertTrue(e.getMessage().contains("Cannot get behaviour for given class type"));
        }
    }

    @Test
    public void getBehaviourAuthorSubmission() {
        ValidatorRepositoryBehaviour behaviour = null;
        try {
            behaviour = new ValidatorRepositoryBehaviourFactory().getBehaviour(AuthorSubmissionEntity.class);
            Assert.assertTrue(behaviour.getClass().equals(ValidatorRepositoryBehaviourAuthorSubmissionEntity.class));
        } catch (RepositoryException e) {
            Assert.assertTrue(e.getMessage().contains("Cannot get behaviour for given class type"));
        }
    }

    @Test
    public void getBehaviourConferenceEntity() {
        ValidatorRepositoryBehaviour behaviour = null;
        try {
            behaviour = new ValidatorRepositoryBehaviourFactory().getBehaviour(ConferenceEntity.class);
            Assert.assertTrue(behaviour.getClass().equals(ValidatorRepositoryBehaviourConferenceEntity.class));
        } catch (RepositoryException e) {
            Assert.assertTrue(e.getMessage().contains("Cannot get behaviour for given class type"));
        }
    }

    @Test
    public void getBehaviourConfigurationEditionMemberEntity() {
        ValidatorRepositoryBehaviour behaviour = null;
        try {
            behaviour = new ValidatorRepositoryBehaviourFactory().getBehaviour(ConfigurationEditionMemberEntity.class);
            Assert.assertTrue(behaviour.getClass().equals(ValidatorRepositoryBehaviourConfigurationEditionMemberEntity.class));
        } catch (RepositoryException e) {
            Assert.assertTrue(e.getMessage().contains("Cannot get behaviour for given class type"));
        }
    }

    @Test
    public void getBehaviourConfigurationSessionMemberEntityy() {
        ValidatorRepositoryBehaviour behaviour = null;
        try {
            behaviour = new ValidatorRepositoryBehaviourFactory().getBehaviour(ConfigurationSessionMemberEntity.class);
            Assert.assertTrue(behaviour.getClass().equals(ValidatorRepositoryBehaviourConfigurationSessionMemberEntity.class));
        } catch (RepositoryException e) {
            Assert.assertTrue(e.getMessage().contains("Cannot get behaviour for given class type"));
        }
    }

    @Test
    public void getBehaviourEditionMemberEntity() {
        ValidatorRepositoryBehaviour behaviour = null;
        try {
            behaviour = new ValidatorRepositoryBehaviourFactory().getBehaviour(EditionMemberEntity.class);
            Assert.assertTrue(behaviour.getClass().equals(ValidatorRepositoryBehaviourEditionMemberEntity.class));
        } catch (RepositoryException e) {

        }
    }

    @Test
    public void getBehaviourNotificationEntity() {
        ValidatorRepositoryBehaviour behaviour = null;
        try {
            behaviour = new ValidatorRepositoryBehaviourFactory().getBehaviour(NotificationEntity.class);
            Assert.assertTrue(behaviour.getClass().equals(ValidatorRepositoryBehaviourNotificationEntity.class));
        } catch (RepositoryException e) {
            Assert.assertTrue(e.getMessage().contains("Cannot get behaviour for given class type"));
        }
    }

    @Test
    public void getBehaviourReviewerEntity() {
        ValidatorRepositoryBehaviour behaviour = null;
        try {
            behaviour = new ValidatorRepositoryBehaviourFactory().getBehaviour(ReviewerEntity.class);
            Assert.assertTrue(behaviour.getClass().equals(ValidatorRepositoryBehaviourReviewerEntity.class));
        } catch (RepositoryException e) {
            Assert.assertTrue(e.getMessage().contains("Cannot get behaviour for given class type"));
        }
    }

    @Test
    public void getBehaviourSessionEntity() {
        ValidatorRepositoryBehaviour behaviour = null;
        try {
            behaviour = new ValidatorRepositoryBehaviourFactory().getBehaviour(SessionEntity.class);
            Assert.assertTrue(behaviour.getClass().equals(ValidatorRepositoryBehaviourSessionEntity.class));
        } catch (RepositoryException e) {
            Assert.assertTrue(e.getMessage().contains("Cannot get behaviour for given class type"));
        }
    }

    @Test
    public void getBehaviourSessionMemberEntity() {
        ValidatorRepositoryBehaviour behaviour = null;
        try {
            behaviour = new ValidatorRepositoryBehaviourFactory().getBehaviour(SessionMemberEntity.class);
            Assert.assertTrue(behaviour.getClass().equals(ValidatorRepositoryBehaviourSessionMemberEntity.class));
        } catch (RepositoryException e) {
            Assert.assertTrue(e.getMessage().contains("Cannot get behaviour for given class type"));
        }
    }

    @Test
    public void getBehaviourSubmissionEntity() {
        ValidatorRepositoryBehaviour behaviour = null;
        try {
            behaviour = new ValidatorRepositoryBehaviourFactory().getBehaviour(SubmissionEntity.class);
            Assert.assertTrue(behaviour.getClass().equals(ValidatorRepositoryBehaviourSubmissionEntity.class));
        } catch (RepositoryException e) {
            Assert.assertTrue(e.getMessage().contains("Cannot get behaviour for given class type"));
        }
    }

    @Test
    public void getBehaviourSubmissionTagEntity() {
        ValidatorRepositoryBehaviour behaviour = null;
        try {
            behaviour = new ValidatorRepositoryBehaviourFactory().getBehaviour(SubmissionTagEntity.class);
            Assert.assertTrue(behaviour.getClass().equals(ValidatorRepositoryBehaviourSubmissionTagEntity.class));
        } catch (RepositoryException e) {
            Assert.assertTrue(e.getMessage().contains("Cannot get behaviour for given class type"));
        }
    }

    @Test
    public void getBehaviourSubmissionTopicEntity() {
        ValidatorRepositoryBehaviour behaviour = null;
        try {
            behaviour = new ValidatorRepositoryBehaviourFactory().getBehaviour(SubmissionTopicEntity.class);
            Assert.assertTrue(behaviour.getClass().equals(ValidatorRepositoryBehaviourSubmissionTopicEntity.class));
        } catch (RepositoryException e) {
            Assert.assertTrue(e.getMessage().contains("Cannot get behaviour for given class type"));
        }
    }

    @Test
    public void getBehaviourTagEntity() {
        ValidatorRepositoryBehaviour behaviour = null;
        try {
            behaviour = new ValidatorRepositoryBehaviourFactory().getBehaviour(TagEntity.class);
            Assert.assertTrue(behaviour.getClass().equals(ValidatorRepositoryBehaviourTagEntity.class));
        } catch (RepositoryException e) {
            Assert.assertTrue(e.getMessage().contains("Cannot get behaviour for given class type"));
        }
    }

    @Test
    public void getBehaviourTopicEntity() {
        ValidatorRepositoryBehaviour behaviour = null;
        try {
            behaviour = new ValidatorRepositoryBehaviourFactory().getBehaviour(TopicEntity.class);
            Assert.assertTrue(behaviour.getClass().equals(ValidatorRepositoryBehaviourTopicEntity.class));
        } catch (RepositoryException e) {
            Assert.assertTrue(e.getMessage().contains("Cannot get behaviour for given class type"));
        }
    }

    @Test
    public void getBehaviourFailed(){

        ValidatorRepositoryBehaviour behaviour = null;
        try {
            behaviour = new ValidatorRepositoryBehaviourFactory().getBehaviour(Object.class);
            Assert.fail();
        } catch (RepositoryException e) {
            Assert.assertTrue(e.getMessage().contains("Cannot get behaviour for given class type"));
        }
    }
}
