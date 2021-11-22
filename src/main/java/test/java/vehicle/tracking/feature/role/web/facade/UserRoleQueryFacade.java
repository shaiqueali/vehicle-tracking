package test.java.vehicle.tracking.feature.role.web.facade;

import com.querydsl.core.types.Predicate;
import test.java.vehicle.tracking.feature.role.web.dto.UserRoleDetailResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserRoleQueryFacade {

    UserRoleDetailResponse queryUserRole(final Predicate predicate);

    Page<UserRoleDetailResponse> queryAllUserRoles(final Predicate predicate, final Pageable pageable);
}
