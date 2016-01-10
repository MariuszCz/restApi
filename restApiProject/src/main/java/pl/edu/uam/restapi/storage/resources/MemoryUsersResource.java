package pl.edu.uam.restapi.storage.resources;

import com.wordnik.swagger.annotations.Api;
import pl.edu.uam.restapi.storage.database.MemoryDB;
import pl.edu.uam.restapi.storage.database.UserDatabase;

import javax.ws.rs.Path;

@Path("/users")
@Api(value = "/users", description = "Operations about users using static java array")
public class MemoryUsersResource extends AbstractUsersResource {

    private static final UserDatabase database = new MemoryDB();

    @Override
    protected UserDatabase getDatabase() {
        return database;
    }

}
