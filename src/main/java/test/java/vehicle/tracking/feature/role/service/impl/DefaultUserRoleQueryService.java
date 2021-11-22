package test.java.vehicle.tracking.feature.role.service.impl;

import com.querydsl.core.types.Predicate;
import test.java.vehicle.tracking.exception.UserAuthServiceException;
import test.java.vehicle.tracking.exception.constant.ErrorCodeEnum;
import test.java.vehicle.tracking.feature.role.repository.UserRoleRepository;
import test.java.vehicle.tracking.feature.role.repository.entity.UserRole;
import test.java.vehicle.tracking.feature.role.service.UserRoleQueryService;
import io.vavr.control.Either;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;


@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DefaultUserRoleQueryService implements UserRoleQueryService {

    UserRoleRepository userRoleRepository;

    @Override
    public Page<UserRole> findAll(final Predicate predicate, final Pageable pageable) {
        log.trace("Start querying all user roles with predicate [{}] and pageable [{}]", predicate, pageable);
        final Page<UserRole> userRoles = userRoleRepository.findAll(predicate, pageable);
        log.trace("End querying all user roles with no of records [{}]", userRoles.getSize());
        return userRoles;
    }

    @Override
    public Either<UserAuthServiceException, UserRole> findOne(final Predicate predicate) {
        log.trace("Start querying user roles with predicate [{}]", predicate);
        final Optional<UserRole> optionalUserRole = userRoleRepository.findOne(predicate);
        return Match(optionalUserRole).of(Case($(v -> optionalUserRole.isPresent()), Either.right(optionalUserRole.orElse(null))),
                Case($(), Either.left(new UserAuthServiceException(ErrorCodeEnum.ENTITY_NOT_FOUND, "user_role.error.entity_not_found"))));
    }
}
