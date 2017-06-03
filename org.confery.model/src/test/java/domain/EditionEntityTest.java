package domain;

import database.DatabaseLoaderFactory;
import database.DatabaseLoaderInterface;
import database.DatabaseLoaderType;
import model.EditionModel;
import model.UserModel;
import org.junit.Before;
import org.junit.Test;
import protocol.EditionProtocol;
import protocol.UserProtocol;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class EditionEntityTest {

    @Before
    public void setUp() throws Exception {
        DatabaseLoaderInterface loader = new DatabaseLoaderFactory().getLoader(DatabaseLoaderType.TEST);
        EditionProtocol editionModel = new EditionModel(loader);
        UserProtocol userModel = new UserModel(loader);
    }

    @Test
    public void isGettingPcMembers() throws Exception {
        // declarations:
        // TODO:
    }
}