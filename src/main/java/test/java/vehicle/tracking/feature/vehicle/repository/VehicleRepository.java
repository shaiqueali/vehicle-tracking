package test.java.vehicle.tracking.feature.vehicle.repository;

import test.java.vehicle.tracking.feature.vehicle.repository.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {

    Optional<Vehicle> findByIdAndUserId(final Integer id, final String userId);

}
