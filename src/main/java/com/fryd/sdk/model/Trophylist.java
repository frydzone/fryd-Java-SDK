package com.fryd.sdk.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * @author Kristoffer Pöpperling, fryd
 */
@Getter @Setter @ToString
public class Trophylist {

    private String _id;

    private String name;

    private String description;

    private String type;

    @JsonProperty("iconpic_id")
    private String iconpicId;

    @JsonSerialize(using = DateSerializer.class)
    private Date start;

    @JsonSerialize(using = DateSerializer.class)
    private Date end;

    @Getter @Setter
    public static class Trophylists {
        private List<Trophylist> trophylists;
    }

}
