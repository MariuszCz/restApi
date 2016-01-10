package pl.edu.uam.restapi.storage.resources;

import com.sun.jersey.api.client.ClientResponse;
import com.wordnik.swagger.annotations.ApiOperation;
import pl.edu.uam.restapi.storage.database.CarDatabase;
import pl.edu.uam.restapi.storage.model.Car;
import pl.edu.uam.restapi.dokumentacjaibledy.exceptions.UserException;
import scala.util.parsing.combinator.testing.Str;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.Collection;

public abstract class CarsResource {

    protected abstract CarDatabase getDatabase();


    @Context
    private UriInfo uriInfo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Get cars collection", notes = "Get cars collection", response = Car.class, responseContainer = "LIST")
    public Collection<Car> list() {
        return getDatabase().getCars();
    }


    @Path("/{carId}")
    @ApiOperation(value = "Get car by id", notes = "[note]Get car by id", response = Car.class)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Car getCar(@PathParam("carId") String carId) throws Exception {
        Car car = getDatabase().getCar(carId);

        if (carId.equals("db")) {
            throw new Exception("Database error");
        }

        if (car == null) {
            throw new UserException("Car not found", "Samochód nie został znaleziony", "http://docu.pl/errors/user-not-found");
        }

        return car;
    }

    @POST
    @ApiOperation(value = "Create car", notes = "Create car", response = Car.class)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCar(Car car) {
        Car dbCar = new Car(
                "",
                car.getMake(),
                car.getModel(),
                car.getYear(),
                car.getEngine(),
                car.getColour(),
                car.getFuelType(),
                car.getVinNumber(),
                car.getPrice()
        );

        Car createdCar = getDatabase().createCar(dbCar);

        return Response.created(URI.create(uriInfo.getPath() + "/" + createdCar.getId())).entity(createdCar).build();
    }

    @Path("/{carId}")
    @PUT
    @ApiOperation(value = "Update car", notes = "Create car", response = Car.class)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCar(@PathParam("carId") String carId, Car car) {

        Car dbCar = new Car(
                "",
                car.getMake(),
                car.getModel(),
                car.getYear(),
                car.getEngine(),
                car.getColour(),
                car.getFuelType(),
                car.getVinNumber(),
                car.getPrice()
        );

        Car updatedCar = getDatabase().updateCar(carId, dbCar);

        return Response.ok().entity(updatedCar).build();
    }

    @DELETE
    @Path("/{carId}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Delete car", notes = "Delete car", response = Car.class)
    public Response deleteCar(@PathParam("carId") final String carId) {

        Car deletedCar = getDatabase().deleteCar(carId);

        return Response.ok().entity(deletedCar).build();
    }
}
