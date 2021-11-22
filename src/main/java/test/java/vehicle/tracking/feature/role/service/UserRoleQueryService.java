package test.java.vehicle.tracking.feature.role.service;

import com.querydsl.core.types.Predicate;
import test.java.vehicle.tracking.exception.UserAuthServiceException;
import test.java.vehicle.tracking.feature.role.repository.entity.UserRole;
import io.vavr.control.Either;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserRoleQueryService {

    Page<UserRole> findAll(final Predicate predicate, final Pageable pageable);

    Either<UserAuthServiceException, UserRole> findOne(final Predicate predicate);
}
