package pl.edu.uam.restapi.storage.entity;

import com.google.common.base.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PostLoad;
import javax.persistence.Table;

@Entity
@Table(name = "cars")
@NamedQueries({
        @NamedQuery(name = "cars.findAll", query = "SELECT u FROM CarEntity u"),
        @NamedQuery(name = "cars.findByParams", query = "SELECT u FROM CarEntity u WHERE price >= :minPrice  AND price <= :maxPrice " )
})

public class CarEntity {
    private static final Logger LOGGER = LoggerFactory.getLogger(CarEntity.class);

    // auto-generated
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "make")
    private String make;

    @Column(name = "model")
    private String model;

    @Column(name = "year")
    private String year;

    @Column(name = "engine")
    private String engine;

    @Column(name = "fuelType")
    private String fuelType;

    @Column(name = "colour")
    private String colour;

    @Column(name = "vinNumber")
    private String vinNumber;

    @Column(name = "price")
    private int price;


    //Lifecycle methods -- Pre/PostLoad, Pre/PostPersist...
    @PostLoad
    private void postLoad() {
        LOGGER.info("postLoad: {}", toString());
    }

    public CarEntity() {
    }

    public CarEntity(String make, String model, String year, String engine, String fuelType, String colour, String vinNumber, int price) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.engine = engine;
        this.fuelType = fuelType;
        this.colour = colour;
        this.vinNumber = vinNumber;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getVinNumber() {
        return vinNumber;
    }

    public void setVinNumber(String vinNumber) {
        this.vinNumber = vinNumber;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", id)
                .add("make", make)
                .add("model", model)
                .add("year", year)
                .add("engine", engine)
                .add("fuelType", fuelType)
                .add("colour",colour)
                .add("vinNumber",vinNumber)
                .add("price",price)
                .toString();
    }
}
