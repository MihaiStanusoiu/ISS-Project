package validator;

import domain.*;
import exception.SystemException;

/**
 * Name:         Validator behaviour factory
 * Effect:       Creates the right validation behaviour for a given object
 * Date:         06/05/2017
 * Tested:       True
 *
 * @author Tanasie Luiza Maria
 * @version 1.0
 */
@SuppressWarnings("all")
public class ValidatorRepositoryBehaviourFactory {

    /**
     * @param type : class of the given object
     * @return [ValidatorRepositoryBehaviour] : returns specific validation behaviour
     * @throws SystemException : when behaviour can't be retrieved for given class
     */
    public static ValidatorRepositoryBehaviour getBehaviour(Class type) {
        if (type.equals(UserEntity.class)){
            return new ValidatorRepositoryBehaviourUserEntity();
        }
        else if (type.equals(EditionEntity.class)) {
            return new ValidatorRepositoryBehaviourEditionEntity();
        }
        else if (type.equals(AuthorSubmissionEntity.class)) {
            return new ValidatorRepositoryBehaviourAuthorSubmissionEntity();
        }
        else if (type.equals(ConferenceEntity.class)) {
            return new ValidatorRepositoryBehaviourConferenceEntity();
        }
        else if (type.equals(ConfigurationEditionMemberEntity.class)) {
            return new ValidatorRepositoryBehaviourConfigurationEditionMemberEntity();
        }
        else if (type.equals(ConfigurationSessionMemberEntity.class)) {
            return new ValidatorRepositoryBehaviourConfigurationSessionMemberEntity();
        }
        else if (type.equals(EditionMemberEntity.class)) {
            return new ValidatorRepositoryBehaviourEditionMemberEntity();
        }
        else if (type.equals(NotificationEntity.class)) {
            return new ValidatorRepositoryBehaviourNotificationEntity();
        }
        else if (type.equals(ReviewerEntity.class)) {
            return new ValidatorRepositoryBehaviourReviewerEntity();
        }
        else if (type.equals(SessionEntity.class)) {
            return new ValidatorRepositoryBehaviourSessionEntity();
        }
        else if (type.equals(SubmissionEntity.class)) {
            return new ValidatorRepositoryBehaviourSubmissionEntity();
        }
        else if (type.equals(SessionMemberEntity.class)) {
            return new ValidatorRepositoryBehaviourSessionMemberEntity();
        }
        else if (type.equals(SubmissionTagEntity.class)) {
            return new ValidatorRepositoryBehaviourSubmissionTagEntity();
        }
        else if (type.equals(SubmissionTopicEntity.class)) {
            return new ValidatorRepositoryBehaviourSubmissionTopicEntity();
        }
        else if (type.equals(TagEntity.class)) {
            return new ValidatorRepositoryBehaviourTagEntity();
        }
        else if (type.equals(TopicEntity.class)) {
            return new ValidatorRepositoryBehaviourTopicEntity();
        }
        else{
            throw new RuntimeException("Cannot get behaviour for given class type");
        }
    }
}


