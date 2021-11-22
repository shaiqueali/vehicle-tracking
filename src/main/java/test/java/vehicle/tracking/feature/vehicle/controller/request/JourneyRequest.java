package test.java.vehicle.tracking.feature.vehicle.controller.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@ApiModel(value = "JourneyRequest", description = "JourneyRequest contains request fields for create and update Journey")
public class JourneyRequest {

    @NotNull
    @ApiModelProperty(name = "vehicleId", example = "1")
    Integer vehicleId;

    @NotEmpty
    @ApiModelProperty(name = "start", example = "41.40338, 2.17403")
    String start;

    @NotEmpty
    @ApiModelProperty(name = "destination", example = "41.40338, 2.17403")
    String destination;
}
