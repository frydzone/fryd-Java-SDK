package com.fryd.sdk.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Kristoffer Pöpperling, fryd
 */
@Getter @Setter @ToString
public class User {

    private String id;

    private String username;

    private String firstname;

    private String lastname;

    private String email;

    private LocalDate birthday;

    private Address address;

    private Integer xp;

    @JsonProperty("avatarpic_id")
    private String avatarPicId;

    private Integer level;

    private List<Progress> progress;

}
