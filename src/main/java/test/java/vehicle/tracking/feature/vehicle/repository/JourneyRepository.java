package test.java.vehicle.tracking.feature.vehicle.repository;

import test.java.vehicle.tracking.feature.vehicle.repository.entity.Journey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface JourneyRepository extends JpaRepository<Journey, Integer> {

    List<Journey> getAllByVehicleId(final Integer vehicleId);

    @Modifying
    @Query("UPDATE Journey j SET j.status = :status, j.lastUpdatedDate = :lastUpdatedDate WHERE j.id = :id")
    void updateStatus(@Param("id") final Integer id,
                      @Param("status") final String status,
                      @Param("lastUpdatedDate") final Date lastUpdatedDate);

}