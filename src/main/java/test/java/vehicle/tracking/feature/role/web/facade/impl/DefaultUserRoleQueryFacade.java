package test.java.vehicle.tracking.feature.role.web.facade.impl;

import com.querydsl.core.types.Predicate;
import test.java.vehicle.tracking.feature.role.service.UserRoleQueryService;
import test.java.vehicle.tracking.feature.role.web.dto.UserRoleDetailResponse;
import test.java.vehicle.tracking.feature.role.web.facade.UserRoleQueryFacade;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DefaultUserRoleQueryFacade implements UserRoleQueryFacade {

    ModelMapper modelMapper;

    UserRoleQueryService userRoleQueryService;

    @Override
    public UserRoleDetailResponse queryUserRole(final Predicate predicate) {
        log.trace("Start querying user role with predicate [{}]", predicate);
        final UserRoleDetailResponse response = modelMapper.map(userRoleQueryService.findOne(predicate).getOrElseThrow(ex -> ex), UserRoleDetailResponse.class);
        log.trace("Start querying user role with response [{}]", response);
        return response;
    }

    @Override
    public Page<UserRoleDetailResponse> queryAllUserRoles(final Predicate predicate, final Pageable pageable) {
        log.trace("Start querying all user role with predicate [{}] and pageable [{}]", predicate, pageable);
        final Page<UserRoleDetailResponse> response = userRoleQueryService.findAll(predicate, pageable).map(userRole -> modelMapper.map(userRole, UserRoleDetailResponse.class));
        log.trace("Start querying all user role with no of records [{}]", response.getSize());
        return response;
    }
}
