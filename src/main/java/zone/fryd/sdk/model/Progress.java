package zone.fryd.sdk.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * This class represents the progress a user has made for a specific trophy
 *
 * @author Kristoffer Pöpperling, fryd
 */
@Getter @Setter @ToString
public class Progress {

    private String id;

    /**
     * Id of the list containing this trophy
     */
    @JsonProperty("list_id")
    private String listId;

    /**
     * Id of the trophy this progress belongs to
     */
    @JsonProperty("trophy_id")
    private String trophyId;

    /**
     * Date of the last time the progess has been updated
     */
    @JsonProperty("last_updated")
    private LocalDateTime lastUpdated;

    /**
     * XP the user has gained by achieving this trophy
     */
    private Integer points;

    /**
     * Has the trophy been achieved yet.
     */
    private Boolean achieved;

    /**
     * Id of the location / fryd Spot this trophy belongs to
     */
    @JsonProperty("location_id")
    private String locationId;

    @JsonProperty("location_name")
    private String locationName;

    /**
     * Name of the trophy this progress belongs to
     */
    private String name;

    /**
     * Id of the icon picture.
     * To get the picture URL use this pattern
     * http://www.fryd.zone/images/trophy/:trophy_id/:pic_id
     */
    @JsonProperty("iconpic_id")
    private String iconpicId;

    /**
     * If this trophy is a counter trophy, this is how many times it must be triggered to be achieved
     */
    private Integer counter;

    /**
     * If this trophy is a counter trophy, this is how many the user has already triggered it
     */
    @JsonProperty("counter_progress")
    private Integer counterProgress;

}
