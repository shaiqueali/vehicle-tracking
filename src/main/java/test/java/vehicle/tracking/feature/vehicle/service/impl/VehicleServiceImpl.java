package test.java.vehicle.tracking.feature.vehicle.service.impl;

import test.java.vehicle.tracking.exception.BusinessServiceException;
import test.java.vehicle.tracking.feature.vehicle.repository.VehicleRepository;
import test.java.vehicle.tracking.feature.vehicle.repository.entity.Vehicle;
import test.java.vehicle.tracking.feature.vehicle.service.VehicleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test.java.vehicle.tracking.exception.constant.ErrorCodeEnum;

import javax.validation.constraints.NotNull;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class VehicleServiceImpl implements VehicleService {

    VehicleRepository vehicleRepository;

    @Override
    public Vehicle getById(@NotNull final Integer id) {
        log.debug("Get vehicle by id [{}]", id);
        return vehicleRepository.findById(id).orElseThrow(() -> new BusinessServiceException(ErrorCodeEnum.ENTITY_NOT_FOUND, "Vehicle does not exists"));
    }

    @Override
    public Vehicle getByIdAndUserId(final Integer id, final String userId) {
        log.debug("Get vehicle by id [{}] and user id [{}]", id, userId);
        return vehicleRepository.findByIdAndUserId(id, userId).orElseThrow(() -> new BusinessServiceException(ErrorCodeEnum.ENTITY_NOT_FOUND, "Vehicle does not exists"));
    }

    @Transactional
    @Override
    public Vehicle save(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    @Transactional
    @Override
    public Vehicle update(Vehicle vehicle) { return vehicleRepository.save(vehicle); }
}
