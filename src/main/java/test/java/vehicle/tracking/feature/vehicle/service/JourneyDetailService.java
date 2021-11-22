package test.java.vehicle.tracking.feature.vehicle.service;

import test.java.vehicle.tracking.feature.vehicle.repository.entity.JourneyDetail;

import java.util.Date;
import java.util.List;

public interface JourneyDetailService {

    JourneyDetail getById(final Integer id);

    JourneyDetail save(final JourneyDetail journeyEntity);

    JourneyDetail update(final JourneyDetail journeyEntity);

    List<JourneyDetail> getAllJourneyDetailsByJourneyId(final Integer journeyId);

    JourneyDetail getCurrentPositionOfVehicleJourney(final Integer journeyId);

    List<JourneyDetail> getAllJourneyDetailsByStartAndEndDate(final Integer journeyId, final Date startDate, final Date endDate);
}
