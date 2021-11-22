package test.java.vehicle.tracking.feature.vehicle.facade;

import test.java.vehicle.tracking.feature.vehicle.controller.request.JourneyRequest;
import test.java.vehicle.tracking.feature.vehicle.controller.response.JourneyGetResponse;

import java.util.List;

public interface JourneyFacade {

    JourneyGetResponse getById(final Integer id);

    Integer create(final JourneyRequest journeyRequest);

    Integer update(final Integer id, final JourneyRequest journeyRequest);

    List<JourneyGetResponse> getAllByVehicleId(final Integer vehicleId);

    void updateStatus(final Integer id, final String status);
}
