package service;

import domain.UserEntity;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface CollectionService {

    LoginService getLoginService();

    SignUpService getSignUpService();

    UserEntity getActiveUser();

    void setActiveUser(UserEntity activeUser);

}
