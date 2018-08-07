package zone.fryd.sdk.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

/**
 * This class represents a user.
 *
 * @author Kristoffer Pöpperling, fryd
 */
@Getter @Setter @ToString
public class User {

    /**
     * User id
     */
    private String id;

    private String username;

    /**
     * Firstname of the user, only filled if allowed by the user
     */
    private String firstname;

    /**
     * Lastname of the user, only filled if allowed by the user
     */
    private String lastname;

    /**
     * Email of the user, only filled if allowed by the user
     */
    private String email;

    /**
     * Birthday of the user, only filled if allowed by the user
     */
    private LocalDate birthday;

    /**
     * Gender of the user
     */
    private String sex;

    /**
     * Address of the user, only filled if allowed by the user
     */
    private Address address;

    private Integer xp;

    /**
     * Id of the avatar picture.
     * To get the picture URL use this pattern
     * http://www.fryd.zone/images/user/:username/:avatarpic_id
     */
    @JsonProperty("avatarpic_id")
    private String avatarPicId;

    private Integer level;

    /**
     * List of all progress the user has made in you location / fryd Spot
     */
    private List<Progress> progress;

}
