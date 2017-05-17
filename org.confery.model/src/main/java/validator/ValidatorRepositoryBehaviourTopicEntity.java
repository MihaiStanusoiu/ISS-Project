package validator;

import domain.TopicEntity;

import java.util.List;
import java.util.Objects;

import static utils.Conditional.basedOn;

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
        basedOn(Objects.isNull(object))
                .runTrue(accumulator::add, "Topic is NULL!");
        basedOn(Objects.isNull(object.getId()))
                .runTrue(accumulator::add, "Topic;s id is NULL!");
        basedOn(Objects.isNull(object.getSubmissionTopics()))
                .runTrue(accumulator::add, "Topic's submission topics is NULL!");
        basedOn(Objects.isNull(object.getWord()) || object.getWord().equals(""))
                .runTrue(accumulator::add, "Topic's word is INVALID!");
        return accumulator;
    }
}
