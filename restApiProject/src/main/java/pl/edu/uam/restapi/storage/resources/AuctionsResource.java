package pl.edu.uam.restapi.storage.resources;

import com.wordnik.swagger.annotations.ApiOperation;
import pl.edu.uam.restapi.storage.database.AuctionDatabase;
import pl.edu.uam.restapi.dokumentacjaibledy.exceptions.UserException;
import pl.edu.uam.restapi.storage.model.Auction;
import pl.edu.uam.restapi.storage.model.Car;

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

public abstract class AuctionsResource {

    protected abstract AuctionDatabase getDatabase();


    @Context
    private UriInfo uriInfo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Get auctions collection", notes = "Get auctions collection", response = Auction.class, responseContainer = "LIST")
    public Collection<Auction> list() {
        return getDatabase().getAuctions();
    }


    @Path("/{auctionId}")
    @ApiOperation(value = "Get auction by id", notes = "[note]Get auction by id", response = Auction.class)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Auction getAuction(@PathParam("auctionId") String auctionId) throws Exception {
        Auction auction = getDatabase().getAuction(auctionId);

        if (auctionId.equals("db")) {
            throw new Exception("Database error");
        }

        if (auction == null) {
            throw new UserException("Auction not found", "Auckcja nie została znaleziona", "http://docu.pl/errors/user-not-found");
        }

        return auction;
    }

    @POST
    @ApiOperation(value = "Create auction", notes = "Create auction", response = Auction.class)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createAuction(Auction auction) {
        Auction dbAuction = new Auction(
                "",
                auction.getUserId(),
                auction.getCarId(),
                auction.getTitle(),
                auction.getDescription(),
                auction.getStartDate(),
                auction.getEndDate()
        );

        Auction createdAuction = getDatabase().createAuction(dbAuction);

        return Response.created(URI.create(uriInfo.getPath() + "/" + createdAuction.getId())).entity(createdAuction).build();
    }

    @Path("/{auctionId}")
    @PUT
    @ApiOperation(value = "Update auction", notes = "Create auction", response = Auction.class)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateAuction(@PathParam("auctionId") String auctionId, Auction auction) {

        Auction dbAuction = new Auction(
                "",
                auction.getUserId(),
                auction.getCarId(),
                auction.getTitle(),
                auction.getDescription(),
                auction.getStartDate(),
                auction.getEndDate()
        );

        Auction updatedAuction = getDatabase().updateAuction(auctionId, dbAuction);

        return Response.ok().entity(updatedAuction).build();
    }

    @DELETE
    @Path("/{auctionId}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Delete auction", notes = "Delete auction", response = Car.class)
    public Response deleteAuction(@PathParam("auctionId") final String auctionId) {

        Auction deletedAuction = getDatabase().deleteAuction(auctionId);

        return Response.ok().entity(deletedAuction).build();
    }
}
