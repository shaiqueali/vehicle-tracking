package test.java.vehicle.tracking.feature.role.web.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotEmpty;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@ApiModel(value = "UserRoleCreateRequest", description = "UserRoleCreateRequest contains request fields for creating user role")
public class UserRoleCreateRequest {

    @NotEmpty
    @ApiModelProperty(name = "name", example = "ADMIN")
    String name;
}
