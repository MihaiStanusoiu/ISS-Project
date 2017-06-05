package model;

import database.DatabaseLoaderInterface;
import domain.TopicEntity;
import protocol.TopicProtocol;

/**
 * Tested: True
 *
 * @author Alexandru Stoica
 * @version 1.0
 */


public class TopicModel extends Model<TopicEntity, Integer> implements TopicProtocol {

    public TopicModel(DatabaseLoaderInterface loader) {
        super(TopicEntity.class, loader);
    }

    private TopicEntity getTopicByWord(String word) {
        return getAll().stream()
                .filter(topic -> topic.getWord().equals(word))
                .findFirst().orElse(new TopicEntity("none"));
    }

}
