package pl.edu.uam.restapi.storage.resources;

import com.wordnik.swagger.annotations.Api;
import pl.edu.uam.restapi.storage.database.AuctionDatabase;
import pl.edu.uam.restapi.storage.database.MysqlDB;

import javax.ws.rs.Path;

@Path("/mysql/auctions")
@Api(value = "/mysql/auctions", description = "Operations about auctions using mysql")
public class MysqlAuctionsResource extends AuctionsResource {

    private static final AuctionDatabase database = new MysqlDB();

    @Override
    protected AuctionDatabase getDatabase() {
        return database;
    }

}
