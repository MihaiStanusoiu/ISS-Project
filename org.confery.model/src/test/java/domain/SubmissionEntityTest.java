package domain;

import database.DatabaseLoaderFactory;
import database.DatabaseLoaderInterface;
import database.DatabaseLoaderType;
import model.SubmissionModel;
import model.UserModel;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class SubmissionEntityTest {

    private SubmissionModel submissionModel;
    private UserModel userModel;

    @Before
    public void setUp() throws Exception {
        DatabaseLoaderInterface loader =
                new DatabaseLoaderFactory().getLoader(DatabaseLoaderType.TEST);
        submissionModel = new SubmissionModel(loader);
        userModel = new UserModel(loader);
    }

    @Test
    public void getReviewers() throws Exception {
        // TODO
    }

    @Test
    public void getAuthors() throws Exception {
        // TODO
    }

    @Test
    public void getTopics() throws Exception {
        // TODO
    }

    @Test
    public void getTags() throws Exception {
        // TODO
    }

    @Test
    public void getBindingReviewers() throws Exception {
        // TODO
    }

    @Test
    public void getAllowedReviewers() throws Exception {
        // TODO
    }

    @Test
    public void getRejectedReviewers() throws Exception {
        // TODO
    }

    @Test
    public void getResult() throws Exception {
        // TODO
    }

}