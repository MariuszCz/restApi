package pl.edu.uam.restapi.storage.model;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Auction")
public class Auction {
    private String id;
    private String userId;
    private String carId;
    private String title;
    private String description;
    private String startDate;
    private String endDate;


    public Auction() {
    }

    public Auction(String id, String userId, String carId, String title, String description,
               String startDate, String endDate) {
        this.id = id;
        this.userId = userId;
        this.carId = carId;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @ApiModelProperty(value = "Auction id", required = true)
    public String getId() {
        return id;
    }

    @ApiModelProperty(value = "User id", required = true)
    public String getUserId() {
        return userId;
    }

    @ApiModelProperty(value = "Car id", required = true)
    public String getCarId() {
        return carId;
    }

    @ApiModelProperty(value = "Auction title", required = true)
    public String getTitle() {
        return title;
    }

    @ApiModelProperty(value = "Auction description", required = true)
    public String getDescription() {
        return description;
    }

    @ApiModelProperty(value = "Start date", required = true)
    public String getStartDate() { return startDate;  }

    @ApiModelProperty(value = "End date", required = true)
    public String getEndDate() { return endDate;  }

}
