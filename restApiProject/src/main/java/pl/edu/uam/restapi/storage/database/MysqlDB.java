package pl.edu.uam.restapi.storage.database;

import com.google.common.collect.Lists;
import pl.edu.uam.restapi.storage.entity.AuctionEntity;
import pl.edu.uam.restapi.storage.entity.CarEntity;
import pl.edu.uam.restapi.storage.entity.UserEntity;
import pl.edu.uam.restapi.storage.model.Auction;
import pl.edu.uam.restapi.storage.model.Car;
import pl.edu.uam.restapi.storage.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MysqlDB implements UserDatabase, CarDatabase, AuctionDatabase {

    private static final String HOST = "localhost";
    private static final int PORT = 3306;
    private static final String DATABASE = "api";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "qwerty";

    private static EntityManager entityManager;

    public static EntityManager getEntityManager() {
        if (entityManager == null) {
            String dbUrl = "jdbc:mysql://" + HOST + ':' + PORT + "/" + DATABASE;

            Map<String, String> properties = new HashMap<String, String>();

            properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
            properties.put("javax.persistence.jdbc.driver", "com.mysql.jdbc.Driver");
            properties.put("hibernate.connection.url", dbUrl);
            properties.put("hibernate.connection.username", USER_NAME);
            properties.put("hibernate.connection.password", PASSWORD);
            properties.put("hibernate.show_sql", "true");
            properties.put("hibernate.format_sql", "true");

            properties.put("hibernate.temp.use_jdbc_metadata_defaults", "false"); //PERFORMANCE TIP!
            properties.put("hibernate.hbm2ddl.auto", "update"); //update schema for entities (create tables if not exists)

            EntityManagerFactory emf = Persistence.createEntityManagerFactory("myUnit", properties);
            entityManager = emf.createEntityManager();
        }

        return entityManager;
    }

/// ************USERS*****************

    @Override
    public User getUser(String sid) {
        Long id = getId(sid);

        UserEntity userEntity = getEntityManager()
                .find(UserEntity.class, id);

        if (userEntity != null) {
            return buildUserResponse(userEntity);
        }

        return null;
    }

    @Override
    public User updateUser(String sid, User user) {
        Long id = getId(sid);

        UserEntity userEntity = getEntityManager()
                .find(UserEntity.class, id);

        if (userEntity != null) {

            userEntity.setFirstName(user.getFirstName());
            userEntity.setLastName(user.getLastName());
            userEntity.setEmail(user.getEmail());
            userEntity.setLogin(user.getLogin());
            userEntity.setPassword(user.getPassword());

            try {
                getEntityManager().getTransaction().begin();

                // Operations that modify the database should come here.
                getEntityManager().persist(userEntity);

                getEntityManager().getTransaction().commit();
            } finally {
                if (getEntityManager().getTransaction().isActive()) {
                    getEntityManager().getTransaction().rollback();
                }
            }

            return buildUserResponse(userEntity);
        }

        return null;
    }

    @Override
    public User createUser(final User user) {
        UserEntity entity = buildUserEntity(user);

        try {
            getEntityManager().getTransaction().begin();

            // Operations that modify the database should come here.
            getEntityManager().persist(entity);

            getEntityManager().getTransaction().commit();
        } finally {
            if (getEntityManager().getTransaction().isActive()) {
                getEntityManager().getTransaction().rollback();
            }
        }

        return new User(String.valueOf(entity.getId()), entity.getFirstName(), entity.getLastName(), entity.getEmail(),
                entity.getLogin(), entity.getPassword());
    }

    @Override
    public Collection<User> getUsers() {
        Query query = getEntityManager().createNamedQuery("users.findAll");
        List<UserEntity> resultList = query.getResultList();

        List<User> list = Collections.emptyList();

        if (resultList != null && !resultList.isEmpty()) {
            list = Lists.newArrayListWithCapacity(resultList.size());

            for (UserEntity user : resultList) {
                list.add(buildUserResponse(user));
            }
        }

        return list;
    }

    private Long getId(String sid) {
        try {
            return Long.valueOf(sid);
        } catch (NumberFormatException e) {

        }
        return null;
    }

    public User deleteUser(String sid){
        Long id = getId(sid);

        UserEntity userEntity = getEntityManager()
                .find(UserEntity.class, id);

        if (userEntity != null) {
            try {
                getEntityManager().getTransaction().begin();

                // Operations that modify the database should come here.
                getEntityManager().remove(userEntity);

                getEntityManager().getTransaction().commit();
            } finally {
                if (getEntityManager().getTransaction().isActive()) {
                    getEntityManager().getTransaction().rollback();
                }
            }

            return buildUserResponse(userEntity);
        }
        return null;
    }


    private User buildUserResponse(UserEntity userEntity) {
        return new User(userEntity.getId().toString(), userEntity.getFirstName(), userEntity.getLastName(), userEntity.getEmail(),
                userEntity.getLogin(), userEntity.getPassword());
    }

    private UserEntity buildUserEntity(User user) {
        return new UserEntity(user.getFirstName(), user.getLastName(), user.getEmail(), user.getLogin(), user.getPassword());
    }

///// ******** CARS ****************


    @Override
    public Car getCar(String sid) {
        Long id = getId(sid);

        CarEntity carEntity = getEntityManager()
                .find(CarEntity.class, id);

        if (carEntity != null) {
            return buildCarResponse(carEntity);
        }

        return null;
    }

    @Override
    public Car updateCar(String sid, Car car) {
        Long id = getId(sid);

        CarEntity carEntity = getEntityManager()
                .find(CarEntity.class, id);

        if (carEntity != null) {

            carEntity.setMake(car.getMake());
            carEntity.setModel(car.getModel());
            carEntity.setYear(car.getYear());
            carEntity.setEngine(car.getEngine());
            carEntity.setFuelType(car.getFuelType());
            carEntity.setColour(car.getColour());
            carEntity.setVinNumber(car.getVinNumber());
            carEntity.setPrice(car.getPrice());

            try {
                getEntityManager().getTransaction().begin();

                // Operations that modify the database should come here.
                getEntityManager().persist(carEntity);

                getEntityManager().getTransaction().commit();
            } finally {
                if (getEntityManager().getTransaction().isActive()) {
                    getEntityManager().getTransaction().rollback();
                }
            }

            return buildCarResponse(carEntity);
        }

        return null;
    }

    @Override
    public Car createCar(final Car car) {
        CarEntity entity = buildCarEntity(car);

        try {
            getEntityManager().getTransaction().begin();

            // Operations that modify the database should come here.
            getEntityManager().persist(entity);

            getEntityManager().getTransaction().commit();
        } finally {
            if (getEntityManager().getTransaction().isActive()) {
                getEntityManager().getTransaction().rollback();
            }
        }

        return new Car(String.valueOf(entity.getId()),entity.getMake(), entity.getModel(), entity.getYear(), entity.getEngine(),
                entity.getFuelType(), entity.getColour(),
                entity.getVinNumber(), entity.getPrice());
    }

    @Override
    public Collection<Car> getCars() {
        Query query = getEntityManager().createNamedQuery("cars.findAll");
        List<CarEntity> resultList = query.getResultList();

        List<Car> list = Collections.emptyList();

        if (resultList != null && !resultList.isEmpty()) {
            list = Lists.newArrayListWithCapacity(resultList.size());

            for (CarEntity car : resultList) {
                list.add(buildCarResponse(car));
            }
        }

        return list;
    }

    private Car buildCarResponse(CarEntity carEntity) {
        return new Car(carEntity.getId().toString(), carEntity.getMake(),carEntity.getModel(),carEntity.getYear(),
                carEntity.getEngine(),carEntity.getFuelType(),carEntity.getColour(),carEntity.getVinNumber(),carEntity.getPrice());
    }

    private CarEntity buildCarEntity(Car car) {
        return new CarEntity(car.getMake(), car.getModel(), car.getYear(), car.getEngine(),
                car.getFuelType(), car.getColour(),
                car.getVinNumber(), car.getPrice());
    }


    public Car deleteCar(String sid){
        Long id = getId(sid);

        CarEntity carEntity = getEntityManager()
                .find(CarEntity.class, id);

        if (carEntity != null) {
            try {
                getEntityManager().getTransaction().begin();

                // Operations that modify the database should come here.
                getEntityManager().remove(carEntity);

                getEntityManager().getTransaction().commit();
            } finally {
                if (getEntityManager().getTransaction().isActive()) {
                    getEntityManager().getTransaction().rollback();
                }
            }

            return buildCarResponse(carEntity);
        }
        return null;
    }
    ///// ******** AUCTIONS ****************


    @Override
    public Auction getAuction(String sid) {
        Long id = getId(sid);

        AuctionEntity auctionEntity = getEntityManager()
                .find(AuctionEntity.class, id);

        if (auctionEntity != null) {
            return buildAuctionResponse(auctionEntity);
        }

        return null;
    }

    @Override
    public Auction updateAuction(String sid, Auction auction) {
        Long id = getId(sid);

        AuctionEntity auctionEntity = getEntityManager()
                .find(AuctionEntity.class, id);

        if (auctionEntity != null) {

            auctionEntity.setUserId(auction.getUserId());
            auctionEntity.setCarId(auction.getCarId());
            auctionEntity.setTitle(auction.getTitle());
            auctionEntity.setDescription(auction.getDescription());
            auctionEntity.setStartDate(auction.getStartDate());
            auctionEntity.setEndDate(auction.getStartDate());

            try {
                getEntityManager().getTransaction().begin();

                // Operations that modify the database should come here.
                getEntityManager().persist(auctionEntity);

                getEntityManager().getTransaction().commit();
            } finally {
                if (getEntityManager().getTransaction().isActive()) {
                    getEntityManager().getTransaction().rollback();
                }
            }

            return buildAuctionResponse(auctionEntity);
        }

        return null;
    }

    @Override
    public Auction createAuction(final Auction auction) {
        AuctionEntity entity = buildAuctionEntity(auction);

        try {
            getEntityManager().getTransaction().begin();

            // Operations that modify the database should come here.
            getEntityManager().persist(entity);

            getEntityManager().getTransaction().commit();
        } finally {
            if (getEntityManager().getTransaction().isActive()) {
                getEntityManager().getTransaction().rollback();
            }
        }

        return new Auction(String.valueOf(entity.getId()),entity.getUserId(), entity.getCarId(), entity.getTitle(), entity.getDescription(),
                entity.getStartDate(), entity.getEndDate());
    }

    @Override
    public Collection<Auction> getAuctions() {
        Query query = getEntityManager().createNamedQuery("auctions.findAll");
        List<AuctionEntity> resultList = query.getResultList();

        List<Auction> list = Collections.emptyList();

        if (resultList != null && !resultList.isEmpty()) {
            list = Lists.newArrayListWithCapacity(resultList.size());

            for (AuctionEntity auction : resultList) {
                list.add(buildAuctionResponse(auction));
            }
        }

        return list;
    }

    public Auction deleteAuction(String sid){
        Long id = getId(sid);

        AuctionEntity auctionEntity = getEntityManager()
                .find(AuctionEntity.class, id);

        if (auctionEntity != null) {
            try {
                getEntityManager().getTransaction().begin();

                // Operations that modify the database should come here.
                getEntityManager().remove(auctionEntity);

                getEntityManager().getTransaction().commit();
            } finally {
                if (getEntityManager().getTransaction().isActive()) {
                    getEntityManager().getTransaction().rollback();
                }
            }

            return buildAuctionResponse(auctionEntity);
        }
        return null;
    }



    private Auction buildAuctionResponse(AuctionEntity auctionEntity) {
        return new Auction(auctionEntity.getId().toString(), auctionEntity.getUserId(), auctionEntity.getCarId(), auctionEntity.getTitle(),
                auctionEntity.getDescription(), auctionEntity.getStartDate(), auctionEntity.getEndDate());
    }

    private AuctionEntity buildAuctionEntity(Auction auction) {
        return new AuctionEntity(auction.getUserId(),auction.getCarId(),auction.getTitle(),auction.getDescription(),
                auction.getStartDate(),auction.getEndDate());
    }
}
