package translator;

import domain.TagEntity;
import transferable.Tag;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class TagTranslator {

    public static Tag translate(TagEntity tag) {
        return new Tag(tag.getId(), tag.getWord());
    }

    public static TagEntity translate(Tag tag) {
        return new TagEntity(tag.getId(), tag.getWord());
    }

}
