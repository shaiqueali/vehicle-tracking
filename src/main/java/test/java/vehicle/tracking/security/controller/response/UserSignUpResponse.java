package test.java.vehicle.tracking.security.controller.response;

import test.java.vehicle.tracking.feature.role.web.dto.UserRoleDetailResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@ApiModel(value = "UserSignUpResponse", description = "UserSignUpResponse contains response fields for signUp user")
public class UserSignUpResponse {

    @ApiModelProperty(name = "userId", example = "d73aae91-4737-475a-b7f7-58af3513fd31")
    String userId;

    @ApiModelProperty(name = "userType", example = "CLIENT OR VENDOR")
    String userType;

    @ApiModelProperty(name = "username", example = "testAB")
    String username;

    @ApiModelProperty(name = "firstName", example = "testAB")
    String firstName;

    @ApiModelProperty(name = "lastName", example = "testAB")
    String lastName;

    @ApiModelProperty(name = "email", example = "testAB@gmail.com")
    String email;

    @ApiModelProperty(name = "phone", example = "34343343413")
    String mobile;

    @ApiModelProperty(name = "additionalAttributes")
    Map<String, Object> additionalAttributes;

    @ApiModelProperty(name = "roles", example = "[{\"name\":\"admin\"}]")
    List<UserRoleDetailResponse> roles = new ArrayList<>();

}
