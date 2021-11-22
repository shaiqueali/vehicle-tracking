package test.java.vehicle.tracking.feature.vehicle.controller.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@ApiModel(value = "VehicleResponse", description = "VehicleResponse contains response fields for create and update vehicle")
public class VehicleResponse {

    @ApiModelProperty(name = "id", example = "1")
    @NonNull
    Integer id;
}
