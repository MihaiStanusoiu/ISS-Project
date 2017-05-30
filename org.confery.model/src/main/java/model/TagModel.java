package model;

import checker.Checker;
import checker.UniqueChecker;
import database.DatabaseLoaderInterface;
import domain.TagEntity;
import exception.SystemException;
import protocol.TagProtocol;

import java.util.stream.Collectors;

import static utils.Try.runFunction;

/**
 * Tested: True
 *
 * @author Alexandru Stoica
 * @version 1.0
 */


public class TagModel extends Model<TagEntity, Integer> implements TagProtocol {

    public TagModel(DatabaseLoaderInterface loader) {
        super(TagEntity.class, loader);
    }

    /**
     * Adds a tag only if the word inside is unique in our system.
     *
     * @param element The target tag
     * @return The tag's id
     * @throws SystemException If the word exists in our systems
     */
    @Override
    public Integer add(TagEntity element) throws SystemException {
        Checker<String> checker = new UniqueChecker<>(this.getAll()
                .stream().map(TagEntity::getWord).collect(Collectors.toList()));
        runFunction(checker::check, element.getWord()).orThrow(exception -> exception);
        return super.add(element);
    }
}
