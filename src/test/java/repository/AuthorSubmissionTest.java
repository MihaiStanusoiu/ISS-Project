package repository;

import database.DatabaseLoaderFactory;
import database.DatabaseLoaderInterface;
import database.DatabaseLoaderType;
import domain.AuthorSubmissionEntity;
import domain.SubmissionEntity;
import domain.UserEntity;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.transaction.Transactional;
import java.util.ArrayList;

/**
 * Name:         {ClassName}
 * Effect:       {ClassEffect}
 * Date:         12/05/2017
 * @author       Tiron Andreea- Ecaterina
 * @version      1.0
 */
public class AuthorSubmissionTest {
    private RepositoryInterface<SubmissionEntity, Integer> repositorySubmission;
    private RepositoryInterface<AuthorSubmissionEntity, Integer> repositoryAuthorSubmission;
    private RepositoryInterface<UserEntity, Integer> repositoryUser;
    private DatabaseLoaderInterface loader;
    
    @Before
    public void setUp() throws Exception {
        loader = new DatabaseLoaderFactory().getLoader(DatabaseLoaderType.TEST);
        repositoryAuthorSubmission = new RepositoryEntity<>(AuthorSubmissionEntity.class, loader);
        repositorySubmission = new RepositoryEntity<>(SubmissionEntity.class, loader);
        repositoryUser = new RepositoryEntity<>(UserEntity.class, loader);
    }

    @After
    public void tearDown() throws Exception {
        loader.getFactory().close();
    }

    @Test
    @Transactional
    public void add() throws Exception {
        AuthorSubmissionEntity author = new AuthorSubmissionEntity(true, "presentation");
        try {
            Integer idAuthor = repositoryAuthorSubmission.add(author);
            Assert.assertTrue(idAuthor.equals(1) &&
                    author.getOwner().equals(true) &&
                    author.getPresentationUrl().equals("presentation")
            );
        } catch (RepositoryException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }

    @Test
    public void update() throws Exception {
        AuthorSubmissionEntity author = new AuthorSubmissionEntity(true, "presentation");
        AuthorSubmissionEntity update = new AuthorSubmissionEntity(true, "presentation2");
        try {
            repositoryAuthorSubmission.add(author);
            repositoryAuthorSubmission.update(author, update);
            AuthorSubmissionEntity result = repositoryAuthorSubmission.getElementById(author.getId());
            Assert.assertTrue(result.getPresentationUrl().equals("presentation2"));
        } catch (RepositoryException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }

    @Test
    public void delete() throws Exception {
        AuthorSubmissionEntity author = new AuthorSubmissionEntity(true, "presentation");
        try {
            repositoryAuthorSubmission.add(author);

            // This test is here only to make sure that we have something in repository in order to delete.
            Assert.assertTrue(author.getOwner().equals(repositoryAuthorSubmission.getElementById(1).getOwner()));
            repositoryAuthorSubmission.delete(author.getId());
            Assert.assertTrue(repositoryAuthorSubmission.getAll().isEmpty());
        } catch (RepositoryException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }

    @Test
    public void getAll() throws Exception {
        AuthorSubmissionEntity author = new AuthorSubmissionEntity(true, "presentation");
        AuthorSubmissionEntity test = new AuthorSubmissionEntity(true, "presentation");
        try {
            repositoryAuthorSubmission.add(author);
            repositoryAuthorSubmission.add(test);
            ArrayList<AuthorSubmissionEntity> result = new ArrayList<>(repositoryAuthorSubmission.getAll());
            Assert.assertTrue(result.get(0).getId().equals(author.getId()) &&
                    result.get(1).getId().equals(test.getId()));
        } catch (RepositoryException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }

    @Test
    public void getElementById() throws Exception {
        AuthorSubmissionEntity author = new AuthorSubmissionEntity(true, "presentation");
        AuthorSubmissionEntity test = new AuthorSubmissionEntity(false, "presentation2");
        try {
            repositoryAuthorSubmission.add(author);
            repositoryAuthorSubmission.add(test);
            AuthorSubmissionEntity result = repositoryAuthorSubmission.getElementById(1);
            Assert.assertTrue(result.getId().equals(author.getId()) &&
                    result.getPresentationUrl().equals(author.getPresentationUrl()));
        } catch (RepositoryException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }

    @Test
    public void getAllFromUser() throws Exception {
        UserEntity user = new UserEntity("username", "password", "email", "name", "website", "bio", "location");
        AuthorSubmissionEntity author1 = new AuthorSubmissionEntity(false, "presentation1");
        AuthorSubmissionEntity author2 = new AuthorSubmissionEntity(true, "presentation2");
        AuthorSubmissionEntity author3 = new AuthorSubmissionEntity(false, "presentation3");
        try {
            repositoryUser.add(user);
            author1.setUserSubmission(repositoryUser.getElementById(1));
            author2.setUserSubmission(repositoryUser.getElementById(1));
            author3.setUserSubmission(repositoryUser.getElementById(1));
            repositoryAuthorSubmission.add(author1);
            repositoryAuthorSubmission.add(author2);
            repositoryAuthorSubmission.add(author3);
            ArrayList<AuthorSubmissionEntity> submissions = new ArrayList<>(repositoryUser.getElementById(1).getAuthorSubmissions());
            Assert.assertTrue(submissions.size() == 3);
        } catch (RepositoryException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }

    @Test
    public void getAllAuthorsFromSubmission() throws Exception {
        SubmissionEntity submission = new SubmissionEntity("thesis", "status", "abstractUrl", "fullPaperUrl");
        AuthorSubmissionEntity author1 = new AuthorSubmissionEntity(false, "presentation1");
        AuthorSubmissionEntity author2 = new AuthorSubmissionEntity(true, "presentation2");
        AuthorSubmissionEntity author3 = new AuthorSubmissionEntity(false, "presentation3");
        try {
            repositorySubmission.add(submission);
            author1.setSubmissionAuthor(repositorySubmission.getElementById(1));
            author2.setSubmissionAuthor(repositorySubmission.getElementById(1));
            author3.setSubmissionAuthor(repositorySubmission.getElementById(1));
            repositoryAuthorSubmission.add(author1);
            repositoryAuthorSubmission.add(author2);
            repositoryAuthorSubmission.add(author3);
            ArrayList<AuthorSubmissionEntity> submissions0 = new ArrayList<>(repositorySubmission.getElementById(1).getSubmissionAuthors());
            Assert.assertTrue(submissions0.size() == 3);
        } catch (RepositoryException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }
}