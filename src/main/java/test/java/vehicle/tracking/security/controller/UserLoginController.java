package test.java.vehicle.tracking.security.controller;

import test.java.vehicle.tracking.security.controller.request.UserLoginRequest;
import test.java.vehicle.tracking.security.controller.response.UserLoginResponse;
import test.java.vehicle.tracking.security.facade.UserLoginFacade;
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
@RequestMapping("/api/v1/auth")
@Api(tags = "Authentication", value = "/")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserLoginController {

    UserLoginFacade userLoginService;

    @ApiOperation(value = "AuthClient or User login", notes = "This api is used for login client or vendor")
    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserLoginResponse> loginUser(@Valid @RequestBody final UserLoginRequest request) {
        return new ResponseEntity<>(userLoginService.loginUser(request), HttpStatus.OK);
    }
}
