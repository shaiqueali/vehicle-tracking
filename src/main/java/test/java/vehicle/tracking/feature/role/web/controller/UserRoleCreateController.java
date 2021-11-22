package test.java.vehicle.tracking.feature.role.web.controller;

import test.java.vehicle.tracking.feature.role.web.dto.UserRoleCreateRequest;
import test.java.vehicle.tracking.feature.role.web.dto.UserRoleCreateResponse;
import test.java.vehicle.tracking.feature.role.web.facade.UserRoleCreateFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/roles")
@Api(tags = "Role", value = "/")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserRoleCreateController {

    UserRoleCreateFacade userRoleCreateFacade;

    @ApiOperation(value = "User Role", notes = "This api is used for create user role")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserRoleCreateResponse> signUpUser(@Valid @RequestBody final UserRoleCreateRequest request) {
        return new ResponseEntity<>(userRoleCreateFacade.createUserRole(request), HttpStatus.CREATED);
    }

}
