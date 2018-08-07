package zone.fryd.sdk.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * This is the basic response you can get from an API request.
 * If everything works out ok, you can get what you asked for from
 * the frydDataType.
 * If not you should a least get a message containing some indication of what went wrong.
 *
 * @author Kristoffer Pöpperling, fryd
 */
@Getter @Setter @ToString
public class APIResponse<T> {

    private T frydDataType;

    /**
     * Can be INFO or ERROR depending on the message
     */
    private String type;

    private String message;

    /**
     * Unique id to the fryd request you send. Provide in case of errors to the fryd developers.
     */
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
