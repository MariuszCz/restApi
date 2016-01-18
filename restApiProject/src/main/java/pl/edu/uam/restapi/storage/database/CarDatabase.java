package pl.edu.uam.restapi.storage.database;

import pl.edu.uam.restapi.storage.model.Auction;
import pl.edu.uam.restapi.storage.model.Car;

import java.util.Collection;

public interface CarDatabase {
    Car getCar(String id);

    Car updateCar(String id, Car car);

    Car createCar(Car user);

    Car deleteCar(String id);

    Collection<Car> getCarsByQueryParams(int minPrice, int maxPrice) throws Exception;

    Collection<Car> getCars();
}

