package manager;

import domain.EditionEntity;
import exception.ModelException;
import model.EditionModel;
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
import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * @author Teodorescu Vlad
 * @version 1.0
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(EditionManager.class)
public class EditionManagerTest {

    private EditionModel model;
    private EditionManager manager;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        NotificationCenter center = PowerMockito.mock(NotificationCenter.class);
        model = PowerMockito.mock(EditionModel.class);
        manager = new EditionManager(model,center);
    }

    @Test
    public void isAddingEdition() throws Exception {
        // declarations:
        Date date = new Date();
        EditionEntity edition = new EditionEntity(date, date, "location", "bio", date, date, date, date);
        // when:
        PowerMockito.doReturn(1).when(model, "add", edition);
        // then:
        assertEquals((long)manager.add(edition),1L);
    }

    @Test
    public void isNotAddingEdition() throws Exception {
        // expect:
        expectedException.expect(RemoteException.class);
        // declarations:
        Date date = new Date();
        EditionEntity edition = new EditionEntity(date, date, "location", null, date, date, date, date);
        // when:
        PowerMockito.doThrow(new ModelException("Edition's bio is NULL!")).when(model, "add", edition);
        // then: [test exceptions]
        manager.add(edition);
    }

    @Test
    public void isDeletingEdition() throws Exception {
        // declarations:
        Date date = new Date();
        EditionEntity edition = new EditionEntity(date, date, "location", "bio", date, date, date, date);
        // when:
        PowerMockito.doReturn(edition).when(model, "delete", edition);
        // then:
        assertEquals(manager.delete(edition), edition);
    }

    @Test
    public void isNotDeletingEdition() throws Exception {
        // expect:
        expectedException.expect(RemoteException.class);
        // declarations:
        Date date = new Date();
        EditionEntity edition = new EditionEntity(date, date, "location", "bio", date, date, date, date);
        // when:
        PowerMockito.doThrow(new ModelException("Edition does not exist in the database.")).when(model, "delete", edition);
        // then: [test exceptions]
        manager.delete(edition);
    }

    @Test
    public void isUpdatingEdition() throws Exception {
        // declarations:
        Date date = new Date();
        EditionEntity edition = new EditionEntity(date, date, "location", "bio", date, date, date, date);
        EditionEntity update = new EditionEntity(date, date, "new location", "bio", date, date, date, date);
        // when:
        PowerMockito.doNothing().when(model, "update", edition, update);
        // then:
        manager.update(edition, update);
    }

    @Test
    public void isNotUpdatingEdition() throws Exception {
        // expect:
        expectedException.expect(RemoteException.class);
        // declarations:
        Date date = new Date();
        EditionEntity edition = new EditionEntity(date, date, "location", "bio", date, date, date, date);
        EditionEntity update = new EditionEntity(date, date, "new location", "bio", date, date, date, date);
        // when:
        PowerMockito.doThrow(new ModelException("Update failed.")).when(model, "update", edition, update);
        // then:
        manager.update(edition, update);
    }

    @Test
    public void isGettingById() throws Exception {
        // declarations:
        Date date = new Date();
        EditionEntity edition = new EditionEntity(date, date, "location", "bio", date, date, date, date);
        // when:
        PowerMockito.doReturn(edition).when(model, "getElementById", 1);
        // then:
        assertEquals(manager.getElementById(1), edition);
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
