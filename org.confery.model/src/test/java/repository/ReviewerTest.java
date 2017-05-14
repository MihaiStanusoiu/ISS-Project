package repository;

import database.DatabaseLoaderFactory;
import database.DatabaseLoaderInterface;
import database.DatabaseLoaderType;
import domain.EditionMemberEntity;
import domain.ReviewerEntity;
import domain.SubmissionEntity;
import exception.RepositoryException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Set;


/**
 * Name:         {ClassName}
 * Effect:       {ClassEffect}
 * Date:         08/05/2017
 * @author       Tiron Andreea- Ecaterina
 * @version      1.0
 */
public class ReviewerTest {
    private RepositoryInterface<SubmissionEntity, Integer> repositorySubmission;
    private RepositoryInterface<EditionMemberEntity, Integer> repositoryEditionMember;
    private RepositoryInterface<ReviewerEntity, Integer> repositoryReview;
    private DatabaseLoaderInterface loader;

    @Before
    public void setUp() throws Exception {
        loader = new DatabaseLoaderFactory().getLoader(DatabaseLoaderType.TEST);
        repositoryReview = new RepositoryEntity<>(ReviewerEntity.class, loader);
        repositorySubmission = new RepositoryEntity<>(SubmissionEntity.class, loader);
        repositoryEditionMember = new RepositoryEntity<>(EditionMemberEntity.class, loader);
    }

    @After
    public void tearDown() throws Exception {
        loader.getFactory().close();
    }

    @Test
    @Transactional
    public void add() throws Exception {
        ReviewerEntity review = new ReviewerEntity("response", "status", "qualifier", "recommendationUrl");
        try {
            repositoryReview.add(review);
            Integer idReview = review.getId();
            Assert.assertTrue(
                    idReview.equals(1) &&
                review.getResponse().equals("response") &&
                review.getStatus().equals("status") &&
                review.getQualifier().equals("qualifier") &&
                review.getRecommendationUrl().equals("recommendationUrl")
            );
        } catch (RepositoryException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }

    @Test
    public void update() throws Exception {
        ReviewerEntity review = new ReviewerEntity("response", "status", "qualifier", "recommendationUrl");
        ReviewerEntity update = new ReviewerEntity("response2", "status2", "qualifier2", "recommendationUrl");
        try {
            repositoryReview.add(review);
            repositoryReview.update(review, update);
            ReviewerEntity result = repositoryReview.getElementById(review.getId());
            Assert.assertTrue(result.getResponse().equals("response2") && result.getQualifier().equals("qualifier2"));
        } catch (RepositoryException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }

    @Test
    public void delete() throws Exception {
        ReviewerEntity review = new ReviewerEntity("response", "status", "qualifier", "recommendationUrl");
        try {
            repositoryReview.add(review);

            // This test is here only to make sure that we have something in repository in order to delete.
            Assert.assertTrue(review.getRecommendationUrl().equals(repositoryReview.getElementById(1).getRecommendationUrl()));
            repositoryReview.delete(review.getId());
            Assert.assertTrue(repositoryReview.getAll().isEmpty());
        } catch (RepositoryException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }

    @Test
    public void getAll() throws Exception {
        ReviewerEntity review = new ReviewerEntity("response", "status", "qualifier", "recommendationUrl");
        ReviewerEntity test = new ReviewerEntity("response3", "status3", "qualifier3", "recommendationUrl");
        try {
            repositoryReview.add(review);
            repositoryReview.add(test);
            ArrayList<ReviewerEntity> result = new ArrayList<>(repositoryReview.getAll());
            Assert.assertTrue(result.get(0).getId().equals(review.getId()) &&
                    result.get(1).getId().equals(test.getId()));
        } catch (RepositoryException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }

    @Test
    public void getElementById() throws Exception {
        ReviewerEntity review = new ReviewerEntity("response", "status", "qualifier", "recommendationUrl");
        ReviewerEntity test = new ReviewerEntity("response4", "status4", "qualifier4", "recommendationUrl");
        try {
            repositoryReview.add(review);
            repositoryReview.add(test);
            ReviewerEntity result = repositoryReview.getElementById(1);
            Assert.assertTrue(result.getId().equals(review.getId()) &&
                    result.getQualifier().equals(review.getQualifier()));
        } catch (RepositoryException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }

    @Test
    public void getAllFromEditionMember() throws Exception {
        ReviewerEntity review1 = new ReviewerEntity("response1", "status1", "qualifier1", "recommendationUrl");
        ReviewerEntity review2 = new ReviewerEntity("response2", "status2", "qualifier2", "recommendationUrl");
        ReviewerEntity review3 = new ReviewerEntity("response3", "status3", "qualifier3", "recommendationUrl");

        EditionMemberEntity member = new EditionMemberEntity();
        try{
            repositoryEditionMember.add(member);
            review1.setIdEditionMember(repositoryEditionMember.getElementById(1));
            review2.setIdEditionMember(repositoryEditionMember.getElementById(1));
            review3.setIdEditionMember(repositoryEditionMember.getElementById(1));
            repositoryReview.add(review1);
            repositoryReview.add(review2);
            repositoryReview.add(review3);
            EditionMemberEntity edition0 = repositoryEditionMember.getElementById(1);
            Set<ReviewerEntity> reviews = edition0.getReviewers();
            Assert.assertTrue(reviews.size() == 3);
        }catch (RepositoryException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }

    @Test
    public void getAllFromSubmission() throws Exception {
        ReviewerEntity review1 = new ReviewerEntity("response1", "status1", "qualifier1", "recommendationUrl");
        ReviewerEntity review2 = new ReviewerEntity("response2", "status2", "qualifier2", "recommendationUrl");
        SubmissionEntity submission = new SubmissionEntity("thesis", "status", "abstractUrl", "fullPaperUrl");
        try{
            repositorySubmission.add(submission);
            review1.setSubmission(repositorySubmission.getElementById(1));
            review2.setSubmission(repositorySubmission.getElementById(1));
            repositoryReview.add(review1);
            repositoryReview.add(review2);
            SubmissionEntity submission0 = repositorySubmission.getElementById(1);
            Set<ReviewerEntity> reviews = submission0.getReviewers();
            Assert.assertTrue(reviews.size() == 2);
        }catch (RepositoryException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }
}