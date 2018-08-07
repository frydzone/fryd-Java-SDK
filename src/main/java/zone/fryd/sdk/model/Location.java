package zone.fryd.sdk.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

/**
 * This class represents a location (also called fryd Spot)
 *
 * @author Kristoffer Pöpperling, fryd
 */
@Getter @Setter @ToString
public class Location {

    private String id;

    private String name;

    private String description;

    /**
     * Type of location, can be ONLINE or LOCAL
     */
    private String type;

    private String homepage;

    @JsonProperty("age_range")
    private String ageRange;

    private String email;

    @JsonProperty("opening_time")
    private OpeningTimes openingTime;

    private Address address;

    @JsonProperty("trophylist_id")
    private String trophylistId;

    /**
     * Creation date of the location in fryd
     */
    private LocalDateTime created;

    /**
     * User Id of the owner
     */
    @JsonProperty("owner_id")
    private String ownerId;

    /**
     * Current status of the location, can be WIP, IN_REVIEW, REVIEWED, TEST, ACTIVE or INACTIVE
     */
    private String status;

    /**
     * Id of the icon picture.
     * To get the picture URL use this pattern
     * http://www.fryd.zone/images/location/:location_id/:pic_id
     */
    @JsonProperty("iconpic_id")
    private String iconpicId;

    /**
     * A list of all picture ids attached to this location
     */
    private List<String> pictures;

}
