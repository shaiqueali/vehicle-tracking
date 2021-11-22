package test.java.vehicle.tracking.feature.vehicle.controller.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@ApiModel(value = "JourneyDetailGetResponse", description = "JourneyDetailGetResponse contains response fields for Journey")
public class JourneyDetailGetResponse {

    @ApiModelProperty(name = "id", example = "1")
    Integer id;

    @ApiModelProperty(name = "journeyId", example = "1")
    Integer journeyId;

    @ApiModelProperty(name = "latitude", example = "-44 56.9816")
    Double latitude;

    @ApiModelProperty(name = "longitude", example = "-44 56.9816")
    Double longitude;

    @ApiModelProperty(name = "createdDate", example = "2021-10-01T07:09:58")
    Date createdDate;

    @ApiModelProperty(name = "lastUpdatedDate", example = "2021-10-01T07:09:58")
    Date lastUpdatedDate;
}
