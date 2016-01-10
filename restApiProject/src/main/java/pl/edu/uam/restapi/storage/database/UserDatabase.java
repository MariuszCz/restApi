package pl.edu.uam.restapi.storage.database;

import pl.edu.uam.restapi.storage.model.User;

import java.util.Collection;

public interface UserDatabase {
    User getUser(String id);

    User updateUser(String id, User user);

    User createUser(User user);

    User deleteUser(String id);

    Collection<User> getUsers();
}
