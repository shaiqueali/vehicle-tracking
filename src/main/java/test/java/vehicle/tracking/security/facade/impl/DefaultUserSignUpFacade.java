package test.java.vehicle.tracking.security.facade.impl;

import com.google.common.collect.Lists;
import test.java.vehicle.tracking.feature.role.repository.entity.QUserRole;
import test.java.vehicle.tracking.feature.role.repository.entity.UserRole;
import test.java.vehicle.tracking.feature.role.service.UserRoleQueryService;
import test.java.vehicle.tracking.feature.user.repository.entity.User;
import test.java.vehicle.tracking.feature.user.service.UserCreateService;
import test.java.vehicle.tracking.security.controller.request.UserSignUpRequest;
import test.java.vehicle.tracking.security.controller.response.UserSignUpResponse;
import test.java.vehicle.tracking.security.facade.UserSignUpFacade;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.apache.commons.collections4.ListUtils.emptyIfNull;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DefaultUserSignUpFacade implements UserSignUpFacade {

    ModelMapper modelMapper;

    UserCreateService userCreateService;

    UserRoleQueryService userRoleQueryService;

    @Override
    public UserSignUpResponse signUpUser(final UserSignUpRequest request) {
        log.trace("Start sign up user with request [{}]", request);
        final User savedUser = userCreateService.save(buildUser(request));
        final UserSignUpResponse response = modelMapper.map(savedUser, UserSignUpResponse.class);
        log.trace("End sign up user with userId [{}]", response.getUserId());
        return response;
    }

    private User buildUser(final UserSignUpRequest request) {
        final User user = modelMapper.map(request, User.class);
        user.setRoles(getUserRoles(request));
        return user;
    }

    private List<UserRole> getUserRoles(final UserSignUpRequest request) {
        final List<UserRole> response = Lists.newArrayList();
        emptyIfNull(request.getRoles()).forEach(userRole -> response.add(userRoleQueryService.findOne(QUserRole.userRole.name.eq(userRole.getName().toUpperCase())).getOrElseThrow(ex -> ex)));
        return response;
    }
}

