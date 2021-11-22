package test.java.vehicle.tracking.feature.vehicle.controller.request;

import test.java.vehicle.tracking.constant.JourneyStatus;
import test.java.vehicle.tracking.validator.annotation.Enum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotEmpty;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@ApiModel(value = "JourneyStatusRequest", description = "JourneyStatusRequest contains request fields for update Journey status")

public class JourneyStatusRequest {

    @NotEmpty
    @Enum(enumClass = JourneyStatus.class, message = "invalid status, values must be (SCHEDULED, INPROGRESS, COMPLETED, FAILED)")
    @ApiModelProperty(name = "status", example = "CLIENT OR VENDOR")
    String status;
}
