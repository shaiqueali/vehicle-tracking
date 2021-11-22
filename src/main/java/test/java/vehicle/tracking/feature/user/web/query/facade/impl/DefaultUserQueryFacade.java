package test.java.vehicle.tracking.feature.user.web.query.facade.impl;


import com.querydsl.core.types.Predicate;
import test.java.vehicle.tracking.feature.user.repository.entity.User;
import test.java.vehicle.tracking.feature.user.service.UserQueryService;
import test.java.vehicle.tracking.feature.user.web.query.dto.UserQueryDetailResponse;
import test.java.vehicle.tracking.feature.user.web.query.facade.UserQueryFacade;
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
public class DefaultUserQueryFacade implements UserQueryFacade {

    ModelMapper modelMapper;

    UserQueryService userQueryService;

    @Override
    public UserQueryDetailResponse findUser(final Predicate predicate) {
        log.trace("Start querying user with predicate [{}]", predicate);
        final User user = userQueryService.findOne(predicate).getOrElseThrow(ex -> ex);
        log.trace("End querying user with userId [{}]", user.getUserId());
        return modelMapper.map(user, UserQueryDetailResponse.class);
    }

    @Override
    public Page<UserQueryDetailResponse> findAllUsers(final Predicate predicate, final Pageable pageable) {
        log.trace("Start querying user with predicate [{}]", predicate);
        final Page<UserQueryDetailResponse> users = userQueryService.findAll(predicate, pageable).map(user -> modelMapper.map(user, UserQueryDetailResponse.class));
        log.trace("End querying all users with no or records [{}]", users.getSize());
        return users;
    }
}
