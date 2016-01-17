package pl.edu.uam.restapi.storage.resources;

import com.sun.jersey.api.Responses;
import com.wordnik.swagger.annotations.ApiOperation;
import pl.edu.uam.restapi.dokumentacjaibledy.exceptions.ResourceException;
import pl.edu.uam.restapi.storage.database.UserDatabase;
import pl.edu.uam.restapi.storage.model.User;
import pl.edu.uam.restapi.dokumentacjaibledy.exceptions.UserException;

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

public abstract class AbstractUsersResource {

    protected abstract UserDatabase getDatabase();


    @Context
    private UriInfo uriInfo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Get users collection", notes = "Get users collection", response = User.class, responseContainer = "LIST")
    public Collection<User> list() {
        return getDatabase().getUsers();
    }


    @Path("/{userId}")
    @ApiOperation(value = "Get user by id", notes = "[note]Get user by id", response = User.class)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser(@PathParam("userId") String userId) throws Exception {
        User user = getDatabase().getUser(userId);

        if (userId.equals("db")) {
            throw new Exception("Database error");
        }

        if (user == null) {
            throw new UserException("User not found", "Użytkownik nie został znaleziony", "http://docu.pl/errors/user-not-found");
        }

        return user;
    }

    @POST
    @ApiOperation(value = "Create user", notes = "Create user", response = User.class)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(User user) throws Exception {

        if(user.getFirstName().isEmpty()) {
            throw new ResourceException("First name is required", "Imie jest wymagane", "");
        }
        else if (user.getLastName().isEmpty()) {
            throw new ResourceException("Last name is required", "Nazwisko jest wymagane", "");
        }
        else if (user.getLogin().isEmpty()) {
            throw new ResourceException("Login is required", "Login jest wymagany", "");
        }
        else if (user.getEmail().isEmpty()) {
            throw new ResourceException("Email is required", "Email jest wymagany", "");
        }
        else if (user.getPassword().isEmpty()) {
            throw new ResourceException("Password is required", "Haslo jest wymagane", "");
        } else {
            User dbUser = new User(
                        "",
                        user.getFirstName(),
                        user.getLastName(),
                        user.getEmail(),
                        user.getLogin(),
                        user.getPassword()
                );

                User createdUser = getDatabase().createUser(dbUser);

                return Response.created(URI.create(uriInfo.getPath() + "/" + createdUser.getId()))
                        .entity(createdUser).build();

        }
    }

    @Path("/{userId}")
    @PUT
    @ApiOperation(value = "Update user", notes = "Create user", response = User.class)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(@PathParam("userId") String userId, User user) throws Exception {

        if (user.getFirstName().isEmpty()) {
            throw new ResourceException("First name is required", "Imie jest wymagane", "");
        } else if (user.getLastName().isEmpty()) {
            throw new ResourceException("Last name is required", "Nazwisko jest wymagane", "");
        } else if (user.getLogin().isEmpty()) {
            throw new ResourceException("Login is required", "Login jest wymagany", "");
        } else if (user.getEmail().isEmpty()) {
            throw new ResourceException("Email is required", "Email jest wymagany", "");
        } else if (user.getPassword().isEmpty()) {
            throw new ResourceException("Password is required", "Haslo jest wymagane", "");
        } else {
            User dbUser = new User(
                    "",
                    user.getFirstName(),
                    user.getLastName(),
                    user.getEmail(),
                    user.getLogin(),
                    user.getPassword()
            );

            User updatedUser = getDatabase().updateUser(userId, dbUser);

            return Response.ok().entity(updatedUser).build();
        }
    }
    @DELETE
    @Path("/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Delete user", notes = "Delete user", response = User.class)
    public Response deleteUser(@PathParam("userId") final String userId) {

        User deletedUser = getDatabase().deleteUser(userId);

        return Response.status(Response.Status.ACCEPTED).entity(deletedUser).build();
    }
}
