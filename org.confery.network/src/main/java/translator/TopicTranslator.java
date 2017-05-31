package translator;

import domain.TopicEntity;
import org.jetbrains.annotations.NotNull;
import transfarable.Topic;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class TopicTranslator implements GenericTranslator<TopicEntity, Topic>{

    @Override
    public Topic translate(@NotNull TopicEntity topic) {
        return new Topic(topic.getId(), topic.getWord());
    }

    @Override
    public TopicEntity translate(@NotNull Topic topic) {
        return new TopicEntity(topic.getId(), topic.getWord());
    }

}
