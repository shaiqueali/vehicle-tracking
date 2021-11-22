package test.java.vehicle.tracking.feature.role.web.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@ApiModel(value = "UserRoleDetailResponse", description = "UserRoleDetailResponse contains response fields for user role")
public class UserRoleDetailResponse {


    @ApiModelProperty(name = "roleId", example = "d73aae91-4737-475a-b7f7-58af3513fd31")
    String roleId;

    @ApiModelProperty(name = "name", example = "ADMIN")
    String name;
}
