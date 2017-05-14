package model;

import domain.UserEntity;
import exception.RepositoryException;

import java.util.List;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface UserProtocol {

    List<UserEntity> getUsers() throws RepositoryException;
    Integer addUser(UserEntity user) throws RepositoryException;
    UserEntity getUserById(Integer id) throws RepositoryException;

}
