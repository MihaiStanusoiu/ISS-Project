package translator;

import domain.TopicEntity;
import transfarable.Topic;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */
public class TopicTranslator {

    public static Topic translate(TopicEntity topic) {
        return new Topic(topic.getId(), topic.getWord());
    }

    public static TopicEntity translate(Topic topic) {
        return new TopicEntity(topic.getId(), topic.getWord());
    }

}
