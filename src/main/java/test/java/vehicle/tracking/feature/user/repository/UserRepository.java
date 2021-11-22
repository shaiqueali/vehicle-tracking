package test.java.vehicle.tracking.feature.user.repository;

import test.java.vehicle.tracking.feature.user.repository.entity.User;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, String>, QuerydslPredicateExecutor<User> {
}
