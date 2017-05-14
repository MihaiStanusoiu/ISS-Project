package validator;

import domain.TopicEntity;

import java.util.List;

/**
 * Name:         Validator behaviour for TopicEntity
 * Effect:       Validates a topic
 * Date:         06/05/2017
 * Tested:       False
 *
 * @author Teodorescu Vlad
 * @version 1.0
 */
@SuppressWarnings("all")
public class ValidatorRepositoryBehaviourTopicEntity extends ValidatorRepositoryBehaviour<TopicEntity> {
    /**
     * @param object : the topic to validate
     * @return [List<String>] : list of error messages found
     */

    @Override
    public List<String> check(TopicEntity object) {
        if (object.getId() == null) {
            accumulator.add("Topic id is null.");
        }
        if (object.getSubmissionTopics() == null) {
            accumulator.add("Topic submissionTopics is null.");
        }
        if(object.getWord() != null) {
            if (object.getWord().equals("")) {
                accumulator.add("Topic word is empty.");
            }
        }
        else{
            accumulator.add("Topic word is null.");
        }
        return accumulator;
    }
}
