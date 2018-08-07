package zone.fryd.sdk.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * This class represents a trophy.
 *
 * @author Kristoffer Pöpperling, fryd
 */
@Getter @Setter @ToString
public class Trophy {

    private String id;

    private String name;

    private String description;

    /**
     * Type of trophy, can be INTERNAL or GENERATED
     */
    private String type;

    /**
     * User Id of the creator of the trophy
     */
    @JsonProperty("author_id")
    private String authorId;

    /**
     * Id of the list this trophy belongs to
     */
    @JsonProperty("list_id")
    private String listId;

    /**
     * Id of the location / fryd Spot this trophy belongs to
     */
    @JsonProperty("location_id")
    private String locationId;

    /**
     * Id of the icon picture.
     * To get the picture URL use this pattern
     * http://www.fryd.zone/images/trophy/:trophy_id/:pic_id
     */
    @JsonProperty("iconpic_id")
    private String iconpicId;

    /**
     * XP the user gains if he achieves this trophy
     */
    private Integer points;

    /**
     * Irrelevant... Not used yet
     */
    private Integer money;

    /**
     * True if the trophy is hidden. Meaning that the user can only read
     * the name and description if he achieves it
     */
    private Boolean hidden;

    /**
     * Sort order of the trophy in its list
     */
    private Integer order;

    /**
     * Difficulty of the trophy, can be EASY, MEDIUM or HARD
     */
    private String difficulty;

    @JsonProperty("author_name")
    private String authorName;

    @JsonProperty("list_name")
    private String listName;

    /**
     * The rarity of how often this trophy has been achieved by all user
     * participating in the same trophylist.
     */
    private Float rarity;

    @Getter @Setter
    public static class Trophies {
        private List<Trophy> trophys;
    }
}
