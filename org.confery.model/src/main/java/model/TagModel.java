package model;

import database.DatabaseLoaderInterface;
import domain.TagEntity;
import protocol.TagProtocol;

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

    public TagEntity getTagByWord(String word) {
        return this.getAll().stream()
                .filter(tag -> tag.getWord().equals(word))
                .findFirst().orElse(new TagEntity("none"));
    }

}
