package test.java.vehicle.tracking.feature.role.repository;

import test.java.vehicle.tracking.feature.role.repository.entity.UserRole;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRoleRepository extends PagingAndSortingRepository<UserRole, String>, QuerydslPredicateExecutor<UserRole> {
}
