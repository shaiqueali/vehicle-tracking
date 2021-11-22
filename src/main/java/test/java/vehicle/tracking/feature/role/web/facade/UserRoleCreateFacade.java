package test.java.vehicle.tracking.feature.role.web.facade;

import test.java.vehicle.tracking.feature.role.web.dto.UserRoleCreateRequest;
import test.java.vehicle.tracking.feature.role.web.dto.UserRoleCreateResponse;

public interface UserRoleCreateFacade {

    UserRoleCreateResponse createUserRole(final UserRoleCreateRequest request);
}
