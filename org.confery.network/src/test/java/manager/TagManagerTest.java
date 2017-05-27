package manager;

import domain.TagEntity;
import exception.ModelException;
import model.SubmissionModel;
import model.TagModel;
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
@PrepareForTest(TagManager.class)
public class TagManagerTest {
    private TagModel model;
    private TagManager manager;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        NotificationCenter center = PowerMockito.mock(NotificationCenter.class);
        model = PowerMockito.mock(TagModel.class);
        manager = new TagManager(model,center);
    }

    @Test
    public void isAddingEdition() throws Exception {
        // declarations:
        TagEntity tag =  new TagEntity("test");
        // when:
        PowerMockito.doReturn(1).when(model, "add", tag);
        // then:
        assertEquals((long)manager.add(tag),1L);
    }

    @Test
    public void isNotAddingEdition() throws Exception {
        // expect:
        expectedException.expect(RemoteException.class);
        // declarations:
        TagEntity tag =  new TagEntity(null);
        // when:
        PowerMockito.doThrow(new ModelException("Tag's word is NULL!")).when(model, "add", tag);
        // then: [test exceptions]
        manager.add(tag);
    }

    @Test
    public void isDeletingEdition() throws Exception {
        // declarations:
        TagEntity tag =  new TagEntity("test");
        // when:
        PowerMockito.doReturn(tag).when(model, "delete", tag);
        // then:
        assertEquals(manager.delete(tag), tag);
    }

    @Test
    public void isNotDeletingEdition() throws Exception {
        // expect:
        expectedException.expect(RemoteException.class);
        // declarations:
        TagEntity tag = new TagEntity("test");
        // when:
        PowerMockito.doThrow(new ModelException("Tag does not exist in the database.")).when(model, "delete", tag);
        // then: [test exceptions]
        manager.delete(tag);
    }

    @Test
    public void isUpdatingEdition() throws Exception {
        // declarations:
        TagEntity tag =  new TagEntity("test");
        TagEntity update =   new TagEntity("updated");
        // when:
        PowerMockito.doNothing().when(model, "update", tag, update);
        // then:
        manager.update(tag, update);
    }

    @Test
    public void isNotUpdatingEdition() throws Exception {
        // expect:
        expectedException.expect(RemoteException.class);
        // declarations:
        TagEntity tag = new TagEntity("test");
        TagEntity update = new TagEntity("updated");
        // when:
        PowerMockito.doThrow(new ModelException("Update failed.")).when(model, "update", tag, update);
        // then:
        manager.update(tag, update);
    }

    @Test
    public void isGettingById() throws Exception {
        // declarations:
        TagEntity tag =  new TagEntity("test");
        // when:
        PowerMockito.doReturn(tag).when(model, "getElementById", 1);
        // then:
        assertEquals(manager.getElementById(1), tag);
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


