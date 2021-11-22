package test.java.vehicle.tracking.security.controller.request;

import test.java.vehicle.tracking.constant.UserType;
import test.java.vehicle.tracking.feature.role.web.dto.UserRoleCreateRequest;
import test.java.vehicle.tracking.validator.annotation.Enum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Map;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@ApiModel(value = "UserSignUpRequest", description = "UserSignUpRequest contains request fields for signUp user")
public class UserSignUpRequest {

    @NotEmpty
    @Enum(enumClass = UserType.class, message = "invalid userType, values must be (CLIENT OR VENDOR)")
    @ApiModelProperty(name = "userType", example = "CLIENT OR VENDOR")
    String userType;

    @NotEmpty
    @ApiModelProperty(name = "username", example = "testAB")
    String username;

    @NotEmpty
    @ApiModelProperty(name = "firstName", example = "testAB")
    String firstName;

    @NotEmpty
    @ApiModelProperty(name = "lastName", example = "testAB")
    String lastName;

    @Email
    @NotEmpty
    @ApiModelProperty(name = "email", example = "testAB@gmail.com")
    String email;

    @Size(min = 9, max = 11)
    @Digits(fraction = 0, integer = 11)
    @ApiModelProperty(name = "phone", example = "34343343413")
    String mobile;

    @NotEmpty
    @ApiModelProperty(name = "password", example = "1234")
    String password;

    @ApiModelProperty(name = "additionalAttributes")
    Map<String, Object> additionalAttributes;

    @ApiModelProperty(name = "roles", example = "[{\"name\":\"admin\"}]")
    List<@Valid UserRoleCreateRequest> roles;

}
