package test.java.vehicle.tracking.feature.vehicle.controller.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@ApiModel(value = "VehicleRequest", description = "VehicleRequest contains request fields for create and update vehicle")
public class VehicleRequest {

    @NotEmpty
    @ApiModelProperty(name = "make", example = "Audi")
    String make;

    @NotEmpty
    @ApiModelProperty(name = "model", example = "Car")
    String model;

    @NotEmpty
    @ApiModelProperty(name = "registrationNumber", example = "ABC-123")
    String registrationNumber;

    @Range(min=1900, max=2100)
    @ApiModelProperty(name = "year", example = "2022")
    Integer year;

    @ApiModelProperty(name = "year", example = "white")
    String color;
}
