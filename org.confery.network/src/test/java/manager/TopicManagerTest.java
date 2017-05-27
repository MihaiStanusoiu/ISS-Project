package manager;

import domain.TopicEntity;
import exception.ModelException;
import model.TopicModel;
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
@PrepareForTest(TopicManager.class)
public class TopicManagerTest {
    private TopicModel model;
    private TopicManager manager;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        NotificationCenter center = PowerMockito.mock(NotificationCenter.class);
        model = PowerMockito.mock(TopicModel.class);
        manager = new TopicManager(model,center);
    }

    @Test
    public void isAddingEdition() throws Exception {
        // declarations:
        TopicEntity topic =  new TopicEntity("test");
        // when:
        PowerMockito.doReturn(1).when(model, "add", topic);
        // then:
        assertEquals((long)manager.add(topic),1L);
    }

    @Test
    public void isNotAddingEdition() throws Exception {
        // expect:
        expectedException.expect(RemoteException.class);
        // declarations:
        TopicEntity topic =  new TopicEntity(null);
        // when:
        PowerMockito.doThrow(new ModelException("Topic's word is NULL!")).when(model, "add", topic);
        // then: [test exceptions]
        manager.add(topic);
    }

    @Test
    public void isDeletingEdition() throws Exception {
        // declarations:
        TopicEntity topic =  new TopicEntity("test");
        // when:
        PowerMockito.doReturn(topic).when(model, "delete", topic);
        // then:
        assertEquals(manager.delete(topic), topic);
    }

    @Test
    public void isNotDeletingEdition() throws Exception {
        // expect:
        expectedException.expect(RemoteException.class);
        // declarations:
        TopicEntity topic = new TopicEntity("test");
        // when:
        PowerMockito.doThrow(new ModelException("Topic does not exist in the database.")).when(model, "delete", topic);
        // then: [test exceptions]
        manager.delete(topic);
    }

    @Test
    public void isUpdatingEdition() throws Exception {
        // declarations:
        TopicEntity topic =  new TopicEntity("test");
        TopicEntity update =   new TopicEntity("updated");
        // when:
        PowerMockito.doNothing().when(model, "update", topic, update);
        // then:
        manager.update(topic, update);
    }

    @Test
    public void isNotUpdatingEdition() throws Exception {
        // expect:
        expectedException.expect(RemoteException.class);
        // declarations:
        TopicEntity topic = new TopicEntity("test");
        TopicEntity update = new TopicEntity("updated");
        // when:
        PowerMockito.doThrow(new ModelException("Update failed.")).when(model, "update", topic, update);
        // then:
        manager.update(topic, update);
    }

    @Test
    public void isGettingById() throws Exception {
        // declarations:
        TopicEntity topic =  new TopicEntity("test");
        // when:
        PowerMockito.doReturn(topic).when(model, "getElementById", 1);
        // then:
        assertEquals(manager.getElementById(1), topic);
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



