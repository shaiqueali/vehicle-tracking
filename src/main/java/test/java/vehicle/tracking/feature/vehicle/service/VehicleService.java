package test.java.vehicle.tracking.feature.vehicle.service;

import test.java.vehicle.tracking.feature.vehicle.repository.entity.Vehicle;

public interface VehicleService {

    Vehicle getById(final Integer id);

    Vehicle getByIdAndUserId(final Integer id, final String userId);

    Vehicle save(final Vehicle vehicle);

    Vehicle update(final Vehicle vehicle);

}
