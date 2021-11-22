package test.java.vehicle.tracking.security.controller.response;

import test.java.vehicle.tracking.feature.role.web.dto.UserRoleDetailResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Value;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Map;

@Value
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@ApiModel(value = "UserLoginResponse", description = "UserLoginResponse contains response fields for login vendor or client")
public class UserLoginResponse {

    @ApiModelProperty(name = "id", example = "02a4c890-f2e9-440d-a55f-9148bce0f0c1")
    String userId;

    @ApiModelProperty(name = "username", example = "testAB")
    String username;

    @ApiModelProperty(name = "firstName", example = "testAB")
    String firstName;

    @ApiModelProperty(name = "email", example = "testAB@gmail.com")
    String email;

    @ApiModelProperty(name = "accessToken", example = "02a4c890-f2e9-440d-a55f-9148bce0f0c1")
    String accessToken;

    @ApiModelProperty(name = "tokenType", example = "Bearer")
    String tokenType;

    @ApiModelProperty(name = "userType", example = "Vendor or Client")
    String userType;

    @ApiModelProperty(name = "roles", example = "[{\"name\":\"admin\"}]")
    List<UserRoleDetailResponse> roles;


    @ApiModelProperty(name = "lastName", example = "testAB")
    String lastName;

    @ApiModelProperty(name = "phone", example = "34343343413")
    String mobile;

    @ApiModelProperty(name = "additionalAttributes")
    Map<String, Object> additionalAttributes;

}
