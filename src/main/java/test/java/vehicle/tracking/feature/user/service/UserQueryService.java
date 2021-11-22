package test.java.vehicle.tracking.feature.user.service;

import com.querydsl.core.types.Predicate;
import test.java.vehicle.tracking.exception.UserAuthServiceException;
import test.java.vehicle.tracking.feature.user.repository.entity.User;
import io.vavr.control.Either;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserQueryService {

    Page<User> findAll(final Predicate predicate, final Pageable pageable);

    Either<UserAuthServiceException, User> findOne(final Predicate predicate);

}
