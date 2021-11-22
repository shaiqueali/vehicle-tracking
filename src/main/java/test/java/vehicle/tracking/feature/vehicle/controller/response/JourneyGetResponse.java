package test.java.vehicle.tracking.feature.vehicle.controller.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@ApiModel(value = "JourneyGetResponse", description = "JourneyGetResponse contains response fields for Journey get by journey id")
public class JourneyGetResponse {

    @ApiModelProperty(name = "id", example = "1")
    Integer id;

    @ApiModelProperty(name = "vehicleId", example = "1")
    Integer vehicleId;

    @ApiModelProperty(name = "start", example = "-44 56.9816, 116 32.8192")
    String start;

    @ApiModelProperty(name = "destination", example = "-44 56.9816, 116 32.8192")
    String destination;

    @ApiModelProperty(name = "destination", example = "SCHEDULED, INPROGRESS, COMPLETED, FAILED")
    String status;

    @ApiModelProperty(name = "createdDate", example = "2021-10-01T07:09:58")
    Date createdDate;

    @ApiModelProperty(name = "lastUpdatedDate", example = "2021-10-01T07:09:58")
    Date lastUpdatedDate;

    @ApiModelProperty(name = "journeyDetails", example = "List")
    List<JourneyDetailGetResponse> journeyDetails;
}
