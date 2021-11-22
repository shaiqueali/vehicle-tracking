package test.java.vehicle.tracking.feature.vehicle.facade;

import test.java.vehicle.tracking.feature.vehicle.controller.request.JourneyDetailRequest;
import test.java.vehicle.tracking.feature.vehicle.controller.request.SearchJourneyDetailRequest;
import test.java.vehicle.tracking.feature.vehicle.controller.response.JourneyDetailGetResponse;

import java.util.List;

public interface JourneyDetailFacade {

    JourneyDetailGetResponse getById(final Integer id);

    Integer create(final JourneyDetailRequest journeyDetailRequest);

    Integer update(final Integer id, final JourneyDetailRequest journeyDetailRequest);

    JourneyDetailGetResponse getCurrentPositionOfVehicleJourney(final Integer journeyId);

    List<JourneyDetailGetResponse> searchJourneyDetailsByStartAndEndDate(final Integer journeyId, final SearchJourneyDetailRequest request);
}
