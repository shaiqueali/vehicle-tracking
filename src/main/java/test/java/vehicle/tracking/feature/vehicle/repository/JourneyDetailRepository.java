package test.java.vehicle.tracking.feature.vehicle.repository;

import test.java.vehicle.tracking.feature.vehicle.repository.entity.JourneyDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface JourneyDetailRepository extends JpaRepository<JourneyDetail, Integer> {

    Optional<JourneyDetail> findFirstByJourneyIdOrderByIdDesc(final Integer journeyId);

    List<JourneyDetail> findAllByJourneyIdOrderByIdDesc(final Integer journeyId);

    List<JourneyDetail> findAllByJourneyIdAndCreatedDateGreaterThanEqualAndLastUpdatedDateLessThanEqual(final Integer journeyId, final Date createdTimestamp, final Date lastUpdatedTimestamp);
}