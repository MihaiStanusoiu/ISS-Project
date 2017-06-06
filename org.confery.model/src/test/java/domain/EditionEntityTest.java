package domain;

import database.DatabaseLoaderFactory;
import database.DatabaseLoaderInterface;
import database.DatabaseLoaderType;
import model.ConferenceModel;
import model.EditionModel;
import model.UserModel;
import org.junit.Before;
import org.junit.Test;
import protocol.EditionProtocol;
import protocol.UserProtocol;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class EditionEntityTest {

    private ConferenceModel conferenceModel;
    private EditionModel editionModel;
    private UserModel userModel;

    @Before
    public void setUp() throws Exception {
        DatabaseLoaderInterface loader = new DatabaseLoaderFactory().getLoader(DatabaseLoaderType.TEST);
        editionModel = new EditionModel(loader);
        userModel = new UserModel(loader);
        conferenceModel = new ConferenceModel(loader);
    }

    @Test
    public void isGettingPcMembers() throws Exception
    {
        UserEntity userEntity = new UserEntity("test","test");
        UserEntity userEntity2 = new UserEntity("test2","test2");
        ConferenceEntity conference = new ConferenceEntity("Conference in biology");
        EditionEntity edition = new EditionEntity("Paris");
        Integer idConference = conferenceModel.add(conference);
        edition = conferenceModel.addEditionTo(conferenceModel.getElementById(idConference), edition);
        edition = conferenceModel.getElementById(idConference).getLatestEdition();
        edition = editionModel.addMemberTo(edition, userEntity, MemberRole.EDITION_PC_MEMBER);
        edition = editionModel.addMemberTo(edition, userEntity2, MemberRole.EDITION_PC_MEMBER);
        edition = editionModel.getElementById(edition.getId());
        assertEquals(edition.getPcMemberEntities().size(),2);
        assertEquals(edition.getPcMemberEntities().get(0).getUser(),userEntity);
        assertEquals(edition.getPcMemberEntities().get(1).getUser(),userEntity2);
    }
}