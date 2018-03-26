package com.fryd.sdk.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Kristoffer Pöpperling, fryd
 */
@Getter @Setter @ToString
public class APIResponse<T> {

    private T frydDataType;

    private String type;

    private String message;

    private String uid;

    public APIResponse() {
    }

    public APIResponse(T frydDataType, String type, String message, String uid) {
        this.frydDataType = frydDataType;
        this.type = type;
        this.message = message;
        this.uid = uid;
    }

}
