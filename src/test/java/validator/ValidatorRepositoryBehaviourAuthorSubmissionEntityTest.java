package validator;
import domain.AuthorSubmissionEntity;
import org.junit.Assert;
import org.junit.Test;
import repository.RepositoryException;

import java.util.Date;

/**
 * Name:         ValidatorRepositoryAuthorSubmissionEntityTest
 * Effect:       Test for author submission validator
 * Date:         06/05/2017
 *
 * @author Teodorescu Vlad
 * @version 1.0
 */
public class ValidatorRepositoryBehaviourAuthorSubmissionEntityTest {
    @Test
    public void validate() {
        AuthorSubmissionEntity validAuthorSubmission = new AuthorSubmissionEntity(1,true, "www.google.ro");
        try {
            ValidatorRepository<AuthorSubmissionEntity> validator = new ValidatorRepository<>(AuthorSubmissionEntity.class);
            Assert.assertTrue(validator.validate(validAuthorSubmission));
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void validateFailed() {
        AuthorSubmissionEntity invalidAuthorSubmission = new AuthorSubmissionEntity(null,false,"www.google.ro");
        try {
            ValidatorRepository<AuthorSubmissionEntity> validator = new ValidatorRepository<>(AuthorSubmissionEntity.class);
            validator.validate(invalidAuthorSubmission);
            Assert.fail();
        } catch (RepositoryException e) {
            Assert.assertTrue(e.getMessage().contains ("AuthorSubmission id is null."));
        }

    }
}
