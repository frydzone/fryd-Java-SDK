package zone.fryd.sdk.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * This class represents the opening times used by a location.
 *
 * @author Kristoffer Pöpperling, fryd
 */
@Getter @Setter @ToString
public class OpeningTimes {

    @JsonProperty("start_date")
    private String startDate;

    @JsonProperty("end_date")
    private String endDate;

    @JsonProperty("start_time")
    private String startTime;

    @JsonProperty("end_time")
    private String endTime;

    private String addition;

}
