package com.fryd.sdk.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @author Kristoffer Pöpperling, fryd
 */
@Getter @Setter @ToString
public class Progress {

    private String id;

    @JsonProperty("list_id")
    private String listId;

    @JsonProperty("trophy_id")
    private String trophyId;

    @JsonProperty("last_updated")
    private LocalDateTime lastUpdated;

    private Integer points;

    private Boolean achieved;

    @JsonProperty("location_id")
    private String locationId;

    @JsonProperty("location_name")
    private String locationName;

    private String name;

}
