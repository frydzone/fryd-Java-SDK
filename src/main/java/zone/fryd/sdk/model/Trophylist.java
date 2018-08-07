package zone.fryd.sdk.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

/**
 * This class represents a trophylist or event.
 *
 * @author Kristoffer Pöpperling, fryd
 */
@Getter @Setter @ToString
public class Trophylist {

    private String id;

    private String name;

    private String description;

    /**
     * Type of trophylist, can be EVENT or LISTE
     */
    private String type;

    /**
     * Id of the icon picture.
     * To get the picture URL use this pattern
     * http://www.fryd.zone/images/list/:list_id/:pic_id
     */
    @JsonProperty("iconpic_id")
    private String iconpicId;

    /**
     * If this trophylist is an event this is the start date and time
     */
    private LocalDateTime start;

    /**
     * If this trophylist is an event this is the end date and time
     */
    private LocalDateTime end;

    @Getter @Setter
    public static class Trophylists {
        private List<Trophylist> trophylists;
    }

}
