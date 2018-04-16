package com.fryd.sdk.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * This class represents an address used by a location or user.
 *
 * @author Kristoffer Pöpperling, fryd
 */
@Getter @Setter @ToString
public class Address {

    private String street;

    private String nr;

    @JsonProperty("postal_code")
    private Integer postalCode;

    private String city;

}
