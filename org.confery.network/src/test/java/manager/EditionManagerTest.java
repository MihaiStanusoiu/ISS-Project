package manager;

import domain.EditionEntity;
import model.EditionModel;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import transfarable.Edition;
import translator.EditionTranslator;

import static org.junit.Assert.assertEquals;

/**
 * Created by Mike on 5/30/2017.
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(SignUpManager.class)
public class EditionManagerTest {

    private EditionModel editionModel;
    private EditionManager editionManager;
    private Edition edition;
    private EditionEntity editionEntity;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        editionEntity = new EditionEntity();
        edition = EditionTranslator.translate(editionEntity);
        editionModel = PowerMockito.mock(EditionModel.class);
        editionManager = new EditionManager(editionModel);
    }

    @Test
    public void addEdition() throws Exception {
        assertEquals(editionManager.add(edition), (Integer) 1);
    }

}
