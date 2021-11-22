package test.java.vehicle.tracking.feature.vehicle.controller.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@ApiModel(value = "JourneyDetailRequest", description = "JourneyDetailRequest contains request fields for create and update Journey detail")
public class JourneyDetailRequest {

    @ApiModelProperty(name = "journeyId", example = "1")
    Integer journeyId;

    @NotNull
    @ApiModelProperty(name = "latitude", example = "41.40338")
    Double latitude;

    @NotNull
    @ApiModelProperty(name = "longitude", example = "2.17403")
    Double longitude;
}
