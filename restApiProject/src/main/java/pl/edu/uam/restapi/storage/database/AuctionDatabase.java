package pl.edu.uam.restapi.storage.database;

import pl.edu.uam.restapi.storage.model.Auction;

import java.util.Collection;

public interface AuctionDatabase {
    Auction getAuction(String id);

    Auction updateAuction(String id, Auction auction);

    Auction createAuction(Auction auction);

    Auction deleteAuction(String id);

    Auction getAuctionsByQueryParams(String mark, String model, String userId);

    Collection<Auction> getAuctions();
}

