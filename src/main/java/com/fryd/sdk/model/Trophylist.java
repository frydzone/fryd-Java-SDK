package com.fryd.sdk.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Kristoffer Pöpperling, fryd
 */
@Getter @Setter @ToString
public class Trophylist {

    private String id;

    private String name;

    private String description;

    private String type;

    @JsonProperty("iconpic_id")
    private String iconpicId;

    private LocalDateTime start;

    private LocalDateTime end;

    @Getter @Setter
    public static class Trophylists {
        private List<Trophylist> trophylists;
    }

}
