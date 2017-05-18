package model;

import checker.Checker;
import checker.UniqueChecker;
import database.DatabaseLoaderInterface;
import domain.TopicEntity;
import exception.SystemException;
import protocol.TopicProtocol;

import java.util.stream.Collectors;

import static utils.Try.runFunction;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class TopicModel extends Model<TopicEntity, Integer> implements TopicProtocol {


    public TopicModel(DatabaseLoaderInterface loader) {
        super(TopicEntity.class, loader);
    }

    /**
     * Adds a topic only if the word inside is unique in our system.
     * @param element The target topic
     * @return The topic's id
     * @throws SystemException If the word exists in our systems
     */
    @Override
    public Integer add(TopicEntity element) throws SystemException {
        Checker<String> checker = new UniqueChecker<>(this.getAll()
                .stream().map(TopicEntity::getWord).collect(Collectors.toList()));
        runFunction(checker::check, element.getWord()).orThrow(exception -> exception);
        return super.add(element);
    }
}
