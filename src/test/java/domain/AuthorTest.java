package domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class AuthorTest {
    private Author author;

    @Before
    public void setUp() throws Exception {
        author = new Author(1, 1, 1, 1, false);
    }

    @Test
    public void getOwner() throws Exception {
        assertTrue(author.isOwner().equals(false));
    }

    @Test
    public void setOwner() throws Exception {
        author.setOwnage(true);
        assertTrue(author.isOwner().equals(true));
    }

    @Test
    public void getType() throws Exception {
        assertTrue(author.getType() == UserType.AUTHOR);
    }

    @Test
    public void getPermissions() throws Exception {
        assertTrue(author.getPermissions().contains(Permission.UPLOAD_SUBMISSION));
    }

}