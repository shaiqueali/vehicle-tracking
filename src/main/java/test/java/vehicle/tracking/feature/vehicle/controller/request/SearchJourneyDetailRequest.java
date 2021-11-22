package test.java.vehicle.tracking.feature.vehicle.controller.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@ApiModel(value = "SearchJourneyDetailRequest", description = "SearchJourneyDetailRequest contains request fields for search Journey detail")
public class SearchJourneyDetailRequest {

    @NotNull
    @ApiModelProperty(name = "startDate", example = "2021-04-28T00:00:00Z")
    Date startDate;

    @NotNull
    @ApiModelProperty(name = "endDate", example = "2021-04-28T00:00:00Z")
    Date endDate;
}
