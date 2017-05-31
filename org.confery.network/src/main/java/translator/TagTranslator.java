package translator;

import domain.TagEntity;
import org.jetbrains.annotations.NotNull;
import transfarable.Tag;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class TagTranslator implements GenericTranslator<TagEntity, Tag> {

    @Override
    public Tag translate(@NotNull TagEntity tag) {
        return new Tag(tag.getId(), tag.getWord());
    }

    @Override
    public TagEntity translate(@NotNull Tag tag) {
        return new TagEntity(tag.getId(), tag.getWord());
    }

}
