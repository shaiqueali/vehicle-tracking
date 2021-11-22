package test.java.vehicle.tracking.feature.vehicle.service;

import test.java.vehicle.tracking.feature.vehicle.repository.entity.Journey;

import java.util.List;

public interface JourneyService {

    Journey getById(final Integer id);

    List<Journey> getAllByVehicleId(final Integer vehicleId);

    Journey save(final Journey journey);

    Journey update(final Journey journey);

    void updateStatus(final Integer id, final String status);

}
