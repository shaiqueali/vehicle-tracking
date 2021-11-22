package test.java.vehicle.tracking.feature.vehicle.controller.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@ApiModel(value = "VehicleGetResponse", description = "VehicleGetResponse contains response fields for vehicle get by vehicle id")
public class VehicleGetResponse {

    @ApiModelProperty(name = "id", example = "1")
    Integer id;

    @ApiModelProperty(name = "userId", example = "d73aae91-4737-475a-b7f7-58af3513fd31")
    String userId;

    @ApiModelProperty(name = "registrationNumber", example = "ABC-123")
    String registrationNumber;

    @ApiModelProperty(name = "createdDate", example = "2021-10-01T07:09:58")
    Date createdDate;

    @ApiModelProperty(name = "lastUpdatedDate", example = "2021-10-01T07:09:58")
    Date lastUpdatedDate;

}
