package domain;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Luiza on 6/5/2017.
 */
public class AuthorSubmissionEntityTest {
    @Test
    public void testEquals()
    {
        AuthorSubmissionEntity authorSubmissionEntity = new AuthorSubmissionEntity();
        AuthorSubmissionEntity authorSubmissionEntity2 = new AuthorSubmissionEntity();
        assertTrue(authorSubmissionEntity.equals(authorSubmissionEntity2));
        authorSubmissionEntity.setId(3);
        assertFalse(authorSubmissionEntity.equals(authorSubmissionEntity2));
    }

}