package model;

import database.DatabaseLoader;
import domain.UserEntity;
import exception.RepositoryException;
import repository.RepositoryEntity;

import java.util.List;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class UserModel implements UserProtocol {

    private RepositoryEntity<UserEntity, Integer> repository;

    public UserModel(DatabaseLoader loader) {
        repository = new RepositoryEntity<>(UserEntity.class, loader);
    }

    public List<UserEntity> getUsers() throws RepositoryException {
        return repository.getAll();
    }

    @Override
    public Integer addUser(UserEntity user) throws RepositoryException {
        return repository.add(user);
    }

    @Override
    public UserEntity getUserById(Integer id) throws RepositoryException {
        return repository.getElementById(id);
    }

}
