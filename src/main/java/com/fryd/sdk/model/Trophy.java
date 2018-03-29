package com.fryd.sdk.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author Kristoffer Pöpperling, fryd
 */
@Getter @Setter @ToString
public class Trophy {

    private String id;

    private String name;

    private String description;

    private String type;

    @JsonProperty("author_id")
    private String authorId;

    @JsonProperty("list_id")
    private String listId;

    @JsonProperty("location_id")
    private String locationId;

    @JsonProperty("iconpic_id")
    private String iconpicId;

    private Integer points;

    private Integer money;

    private Boolean hidden;

    private Integer order;

    private String difficulty;

    @JsonProperty("author_name")
    private String authorName;

    @JsonProperty("list_name")
    private String listName;

    private Float rarity;

    @Getter @Setter
    public static class Trophies {
        private List<Trophy> trophys;
    }
}
