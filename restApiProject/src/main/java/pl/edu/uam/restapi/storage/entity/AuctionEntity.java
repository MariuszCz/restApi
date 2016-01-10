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
@Table(name = "auctions")
@NamedQueries({
        @NamedQuery(name = "auctions.findAll", query = "SELECT u FROM AuctionEntity u")
})
public class AuctionEntity {
    private static final Logger LOGGER = LoggerFactory.getLogger(CarEntity.class);

    // auto-generated
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "userId")
    private String userId;

    @Column(name = "carId")
    private String carId;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "startDate")
    private String startDate;

    @Column(name = "endDate")
    private String endDate;

    //Lifecycle methods -- Pre/PostLoad, Pre/PostPersist...
    @PostLoad
    private void postLoad() {
        LOGGER.info("postLoad: {}", toString());
    }

    public AuctionEntity() {
    }

    public AuctionEntity(String userId, String carId, String title, String description, String startDate, String endDate) {

        this.userId = userId;
        this.carId = carId;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    public Long getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }


    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", id)
                .add("userId", userId)
                .add("carId", carId)
                .add("title", title)
                .add("description", description)
                .add("startDate", startDate)
                .add("endDate", endDate)
                .toString();
    }
}
