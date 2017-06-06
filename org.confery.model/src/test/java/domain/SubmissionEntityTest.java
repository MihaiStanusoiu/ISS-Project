package domain;

import database.DatabaseLoaderFactory;
import database.DatabaseLoaderInterface;
import database.DatabaseLoaderType;
import model.EditionModel;
import model.SubmissionModel;
import model.UserModel;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Luiza on 6/5/2017.
 */
public class SubmissionEntityTest {

    private SubmissionModel submissionModel;
    private UserModel userModel;
    private EditionModel editionModel;

    @Before
    public void setUp() throws Exception {
        DatabaseLoaderInterface loader =
                new DatabaseLoaderFactory().getLoader(DatabaseLoaderType.TEST);
        submissionModel = new SubmissionModel(loader);
        userModel = new UserModel(loader);
        editionModel = new EditionModel(loader);
    }

    @Test
    public void isCreatingSubmissionEntity() throws Exception {
            SubmissionEntity submission = new SubmissionEntity("Paper", "abstract.com", "full.com") ;
            submissionModel.add(submission);
            SubmissionEntity foundSubmission = submissionModel.getElementById(1);
            assertEquals(foundSubmission.getName(),"Paper");
        }

}
