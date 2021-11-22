package test.java.vehicle.tracking.feature.user.web.query.controller;

import com.querydsl.core.types.Predicate;
import test.java.vehicle.tracking.feature.user.repository.entity.QUser;
import test.java.vehicle.tracking.feature.user.repository.entity.User;
import test.java.vehicle.tracking.feature.user.web.query.dto.UserQueryDetailResponse;
import test.java.vehicle.tracking.feature.user.web.query.facade.UserQueryFacade;
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
@RequestMapping("/api/v1/users")
@Api(tags = "User", value = "/")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserQueryController {

    UserQueryFacade userQueryFacade;

    @ApiOperation(value = "User query", notes = "This api is used for query user by id or name")
    @GetMapping(value = "/idorname/{q}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserQueryDetailResponse> queryUserByIdOrName(@PathVariable("q") final String q) {
        return new ResponseEntity<>(userQueryFacade.findUser(QUser.user.username.eq(q).or(QUser.user.userId.eq(q)).or(QUser.user.email.eq(q))), HttpStatus.OK);
    }

    @ApiOperation(value = "User all query", notes = "This api is used for query all users")
    @GetMapping(value = "/query", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<UserQueryDetailResponse>> queryAllUsers(@QuerydslPredicate(root = User.class) final Predicate predicate, final Pageable pageable) {
        return new ResponseEntity<>(userQueryFacade.findAllUsers(predicate, pageable), HttpStatus.OK);
    }
}
