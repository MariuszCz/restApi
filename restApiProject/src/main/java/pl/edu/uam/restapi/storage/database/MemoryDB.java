package pl.edu.uam.restapi.storage.database;

import pl.edu.uam.restapi.storage.model.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MemoryDB implements UserDatabase {
    private static Map<String, User> users = new HashMap<String, User>() {{
        put("0", new User("0", "Mieszko", "Pierwszy","miecho@gmai.com","miecho","Ndue39r"));
        put("1", new User("1", "Bolesław", "Chrobry","bolek@gmail.com","bolek","U89f8we9f"));
        put("2", new User("2", "Kazimierz", "Wielki","kaziu@gmail.com","kaziu","Qkadn34ni"));
    }};

    @Override
    public User getUser(String id) {
        return users.get(id);
    }

    @Override
    public User updateUser(String id, User user) {
        User existedUser = users.get(id);
        if (existedUser != null) {
            existedUser.setFirstName(user.getFirstName());
            existedUser.setLastName(user.getLastName());
            existedUser.setEmail(user.getEmail());
            existedUser.setLogin(user.getLogin());
            existedUser.setPassword(user.getPassword());
            users.put(id, existedUser);
            return existedUser;
        }
        return null;
    }

    @Override
    public User createUser(User user) {
        String id = Integer.toString(users.size());
        User value = new User(id, user.getFirstName(), user.getLastName(), user.getEmail(), user.getLogin(), user.getPassword());
        users.put(id, value);
        return value;
    }

    @Override
    public Collection<User> getUsers() {
        return users.values();
    }

    @Override
    public User deleteUser(String sid) {
        users.remove(sid);
        return null;
    }
}
