package pl.edu.uam.restapi.storage.resources;

import com.wordnik.swagger.annotations.Api;
import pl.edu.uam.restapi.storage.database.CarDatabase;
import pl.edu.uam.restapi.storage.database.MysqlDB;

import javax.ws.rs.Path;

@Path("/mysql/cars")
@Api(value = "/mysql/cars", description = "Operations about cars using mysql")
public class MysqlCarsResource extends CarsResource {

    private static final CarDatabase database = new MysqlDB();

    @Override
    protected CarDatabase getDatabase() {
        return database;
    }

}
