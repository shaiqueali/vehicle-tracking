package test.java.vehicle.tracking.feature.user.service;

import test.java.vehicle.tracking.feature.user.repository.entity.User;

public interface UserCreateService {

    User save(final User user);
}
