package test.java.vehicle.tracking.feature.role.web.controller;

import test.java.vehicle.tracking.feature.role.web.facade.UserRoleDeleteFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/roles")
@Api(tags = "Role", value = "/")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserRoleDeleteController {

    UserRoleDeleteFacade userRoleDeleteFacade;

    @ApiOperation(value = "User Role", notes = "This api is used for delete user role by id")
    @DeleteMapping(value = "/{roleId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> queryUserRoleByIdOrName(@PathVariable("roleId") final String roleId) {
        userRoleDeleteFacade.deleteUserRoleById(roleId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
