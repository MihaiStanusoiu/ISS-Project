package manager;

import domain.SubmissionEntity;
import exception.ModelException;
import model.SubmissionModel;
import notification.NotificationCenter;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.rmi.RemoteException;

import static org.junit.Assert.assertEquals;

/**
 * @author Teodorescu Vlad
 * @version 1.0
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(SubmissionManager.class)
public class SubmissionManagerTest {
    private SubmissionModel model;
    private SubmissionManager manager;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        NotificationCenter center = PowerMockito.mock(NotificationCenter.class);
        model = PowerMockito.mock(SubmissionModel.class);
        manager = new SubmissionManager(model,center);
    }

    @Test
    public void isAddingEdition() throws Exception {
        // declarations:
        SubmissionEntity submission =  new SubmissionEntity("Test", "REVIEWED");
        // when:
        PowerMockito.doReturn(1).when(model, "add", submission);
        // then:
        assertEquals((long)manager.add(submission),1L);
    }

    @Test
    public void isNotAddingEdition() throws Exception {
        // expect:
        expectedException.expect(RemoteException.class);
        // declarations:
        SubmissionEntity submission =  new SubmissionEntity(null, "REVIEWED");
        // when:
        PowerMockito.doThrow(new ModelException("Submission's name is NULL!")).when(model, "add", submission);
        // then: [test exceptions]
        manager.add(submission);
    }

    @Test
    public void isDeletingEdition() throws Exception {
        // declarations:
        SubmissionEntity submission =  new SubmissionEntity("Test", "REVIEWED");
        // when:
        PowerMockito.doReturn(submission).when(model, "delete", submission);
        // then:
        assertEquals(manager.delete(submission), submission);
    }

    @Test
    public void isNotDeletingEdition() throws Exception {
        // expect:
        expectedException.expect(RemoteException.class);
        // declarations:
        SubmissionEntity submission = new SubmissionEntity("Test", "REVIEWED");
        // when:
        PowerMockito.doThrow(new ModelException("Submission does not exist in the database.")).when(model, "delete", submission);
        // then: [test exceptions]
        manager.delete(submission);
    }

    @Test
    public void isUpdatingEdition() throws Exception {
        // declarations:
        SubmissionEntity submission =  new SubmissionEntity("Test", "REVIEWED");
        SubmissionEntity update =   new SubmissionEntity("Update", "REVIEWED");
        // when:
        PowerMockito.doNothing().when(model, "update", submission, update);
        // then:
        manager.update(submission, update);
    }

    @Test
    public void isNotUpdatingEdition() throws Exception {
        // expect:
        expectedException.expect(RemoteException.class);
        // declarations:
        SubmissionEntity submission = new SubmissionEntity("Test", "REVIEWED");
        SubmissionEntity update = new SubmissionEntity("Update", "REVIEWED");
        // when:
        PowerMockito.doThrow(new ModelException("Update failed.")).when(model, "update", submission, update);
        // then:
        manager.update(submission, update);
    }

    @Test
    public void isGettingById() throws Exception {
        // declarations:
        SubmissionEntity submission =  new SubmissionEntity("Test", "REVIEWED");
        // when:
        PowerMockito.doReturn(submission).when(model, "getElementById", 1);
        // then:
        assertEquals(manager.getElementById(1), submission);
    }

    @Test
    public void isNotGettingById() throws Exception {
        // expect:
        expectedException.expect(RemoteException.class);
        // declarations:
        // when:
        PowerMockito.doThrow(new ModelException("Get By id Failed")).when(model, "getElementById", 1);
        // then:
        manager.getElementById(1);
    }
}

