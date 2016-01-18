package pl.edu.uam.restapi.storage.model;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@ApiModel(value = "Car")
@XmlRootElement(name = "cars")
public class Car {
    private String id;
    private String make;
    private String model;
    private String year;
    private String engine;
    private String fuelType;
    private String colour;
    private String vinNumber;
    private int price;


    public Car() {
    }

    public Car(String id, String make, String model, String year, String engine,
               String fuelType, String colour, String vinNumber, int price) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.year = year;
        this.engine = engine;
        this.fuelType = fuelType;
        this.colour = colour;
        this.vinNumber = vinNumber;
        this.price = price;
    }
    @XmlAttribute
    @ApiModelProperty(value = "Car id", required = true)
    public String getId() {
        return id;
    }

    @XmlAttribute
    @ApiModelProperty(value = "Car make name", required = true)
    public String getMake() {
        return make;
    }

    @XmlAttribute
    @ApiModelProperty(value = "Car model name", required = true)
    public String getModel() {
        return model;
    }

    @XmlAttribute
    @ApiModelProperty(value = "Production year", required = true)
    public String getYear() {
        return year;
    }

    @XmlAttribute
    @ApiModelProperty(value = "Engine type", required = true)
    public String getEngine() {
        return engine;
    }

    @XmlAttribute
    @ApiModelProperty(value = "Fuel type", required = true)
    public String getFuelType() {
        return fuelType;
    }

    @XmlAttribute
    @ApiModelProperty(value = "Car colour", required = true)
    public String getColour() {
        return colour;
    }

    @XmlAttribute
    @ApiModelProperty(value = "VIN number", required = true)
    public String getVinNumber() {
        return vinNumber;
    }

    @XmlAttribute
    @ApiModelProperty(value = "Car price", required = true)
    public int getPrice() {
        return price;
    }

}

