package utils;

import domain.ResponseEntityType;
import domain.TagEntity;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static utils.Comparator.checkClass;
import static utils.Comparator.checkObjects;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class ComparatorTest {

    @Test
    public void isComparingObjects() throws Exception {
        System.out.print(ResponseEntityType.ALLOWED_TO_REVIEW.toString());
        TagEntity tag = new TagEntity("test");
        TagEntity test = new TagEntity("test");
        assertTrue(checkObjects((left, right) -> left.getWord().equals(right.getWord()),
                tag, checkClass(test, tag.getClass())));
    }

}