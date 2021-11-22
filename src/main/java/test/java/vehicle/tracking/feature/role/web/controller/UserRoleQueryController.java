package test.java.vehicle.tracking.feature.role.web.controller;

import com.querydsl.core.types.Predicate;
import test.java.vehicle.tracking.feature.role.repository.entity.QUserRole;
import test.java.vehicle.tracking.feature.role.repository.entity.UserRole;
import test.java.vehicle.tracking.feature.role.web.dto.UserRoleDetailResponse;
import test.java.vehicle.tracking.feature.role.web.facade.UserRoleQueryFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/roles")
@Api(tags = "Role", value = "/")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserRoleQueryController {

    UserRoleQueryFacade userRoleQueryFacade;

    @ApiOperation(value = "User Role", notes = "This api is used for query user role by id or name")
    @GetMapping(value = "/idorname/{q}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserRoleDetailResponse> queryUserRoleByIdOrName(@PathVariable("q") final String q) {
        return new ResponseEntity<>(userRoleQueryFacade.queryUserRole(QUserRole.userRole.roleId.eq(q).or(QUserRole.userRole.name.eq(q))), HttpStatus.OK);
    }

    @ApiOperation(value = "User Role", notes = "This api is used for query all user role")
    @GetMapping(value = "/query", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<UserRoleDetailResponse>> queryAllUserRoles(@QuerydslPredicate(root = UserRole.class) final Predicate predicate, final Pageable pageable) {
        return new ResponseEntity<>(userRoleQueryFacade.queryAllUserRoles(predicate, pageable), HttpStatus.OK);
    }
}
