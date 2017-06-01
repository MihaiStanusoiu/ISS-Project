package model;

import database.DatabaseLoaderFactory;
import database.DatabaseLoaderInterface;
import database.DatabaseLoaderType;
import domain.LoginEntity;
import domain.UserEntity;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Vlad on 6/1/2017.
 */
public class LoginModelTest {

    private LoginModel loginModel;
    private UserModel userModel;

    @Before
    public void setUp() throws Exception {
        DatabaseLoaderInterface loader =
                new DatabaseLoaderFactory().getLoader(DatabaseLoaderType.TEST);
        loginModel = new LoginModel(loader);
        userModel = new UserModel(loader);
    }

    @Test
    public void isGettingUserByIp() throws Exception {
        // declarations:
        LoginEntity login = new LoginEntity(1,1, "192.168.0.1");
        // preconditions:
        loginModel.add(login);
        // when:
        // then:
        assertEquals(loginModel.getByIp(login.getIp()), login);
    }

    @Test
    public void isGettingUserBasedOnLoginEntityId() throws Exception{
        // declarations:
        LoginEntity login = new LoginEntity(1,1, "192.168.0.1");
        UserEntity user =  new UserEntity(1,"test", "password");
        // preconditions:
        userModel.add(user);
        // when:
        loginModel.add(login);
        // then:
        assertEquals(loginModel.getById(login.getIp()), user);
    }
}
