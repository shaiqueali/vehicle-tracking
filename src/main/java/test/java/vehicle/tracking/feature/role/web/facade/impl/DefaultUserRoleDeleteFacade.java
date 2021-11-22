package test.java.vehicle.tracking.feature.role.web.facade.impl;

import test.java.vehicle.tracking.feature.role.service.UserRoleDeleteService;
import test.java.vehicle.tracking.feature.role.web.facade.UserRoleDeleteFacade;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DefaultUserRoleDeleteFacade implements UserRoleDeleteFacade {

    UserRoleDeleteService userRoleDeleteService;

    @Override
    public void deleteUserRoleById(final String roleId) {
        log.trace("Start deleting user role with roleId [{}]", roleId);
        userRoleDeleteService.delete(roleId);
        log.trace("End deleting user role with roleId [{}]", roleId);
    }
}
