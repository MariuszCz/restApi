package pl.edu.uam.restapi.storage.resources;

import com.wordnik.swagger.annotations.Api;
import pl.edu.uam.restapi.storage.database.MysqlDB;
import pl.edu.uam.restapi.storage.database.UserDatabase;

import javax.ws.rs.Path;

@Path("/mysql/users")
@Api(value = "/mysql/users", description = "Operations about users using mysql")
public class MysqlUsersResource extends AbstractUsersResource {

    private static final UserDatabase database = new MysqlDB();

    @Override
    protected UserDatabase getDatabase() {
        return database;
    }

}
