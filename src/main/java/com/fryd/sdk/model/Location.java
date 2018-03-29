package com.fryd.sdk.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Kristoffer Pöpperling, fryd
 */
@Getter @Setter @ToString
public class Location {

    private String id;

    private String name;

    private String description;

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

    private LocalDateTime created;

    @JsonProperty("owner_id")
    private String ownerId;

    private String status;

    @JsonProperty("iconpic_id")
    private String iconpicId;

    private List<String> pictures;

}
