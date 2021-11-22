package test.java.vehicle.tracking.feature.vehicle.facade.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import test.java.vehicle.tracking.feature.user.repository.entity.User;
import test.java.vehicle.tracking.feature.vehicle.controller.request.VehicleRequest;
import test.java.vehicle.tracking.feature.vehicle.controller.response.VehicleGetResponse;
import test.java.vehicle.tracking.feature.vehicle.controller.response.VehicleResponse;
import test.java.vehicle.tracking.feature.vehicle.facade.VehicleFacade;
import test.java.vehicle.tracking.feature.vehicle.repository.entity.Vehicle;
import test.java.vehicle.tracking.feature.vehicle.service.VehicleService;

import javax.validation.constraints.NotNull;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class VehicleFacadeImpl implements VehicleFacade {

    VehicleService vehicleService;

    @Override
    public VehicleGetResponse getById(@NotNull final Integer id) {
        final Vehicle vehicle = vehicleService.getById(id);
        log.info("Found vehicle [{}]", id);
        return buildVehicleGetResponse(vehicle);
    }

    @Override
    public VehicleResponse create(final VehicleRequest vehicleRequest) {
        log.debug("Vehicle create request [{}]", vehicleRequest);
        final Vehicle vehicle = vehicleService.save(buildVehicleEntity(vehicleRequest));
        log.info("Vehicle created with id [{}]", vehicle.getId());
        return new VehicleResponse(vehicle.getId());
    }

    @Override
    public VehicleResponse update(@NotNull final Integer id, final VehicleRequest vehicleRequest) {
        log.debug("Vehicle update request [{}] with id [{}]", vehicleRequest, id);
        final User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        Vehicle vehicle = vehicleService.getByIdAndUserId(id, user.getUserId());
        final Vehicle savedVehicle = vehicleService.update(buildVehicleEntity(vehicle, vehicleRequest));
        log.info("Vehicle updated with id [{}]", savedVehicle.getId());
        return new VehicleResponse(savedVehicle.getId());
    }

    private VehicleGetResponse buildVehicleGetResponse(final Vehicle vehicle) {
        VehicleGetResponse response = new VehicleGetResponse();
        response.setId(vehicle.getId());
        response.setUserId(vehicle.getUserId());
        response.setRegistrationNumber(vehicle.getRegistrationNumber());
        response.setCreatedDate(vehicle.getCreatedDate());
        response.setLastUpdatedDate(vehicle.getLastUpdatedDate());
        return response;
    }

    private Vehicle buildVehicleEntity(final VehicleRequest request) {
        Vehicle entity = new Vehicle();
        entity.setMake(request.getMake());
        entity.setModel(request.getModel());
        entity.setRegistrationNumber(request.getRegistrationNumber());
        entity.setColor(request.getColor());
        entity.setYear(request.getYear());
        entity.setIsDeleted(Boolean.FALSE);
        return entity;
    }

    private Vehicle buildVehicleEntity(final Vehicle entity, final VehicleRequest request) {
        entity.setMake(request.getMake());
        entity.setModel(request.getModel());
        entity.setRegistrationNumber(request.getRegistrationNumber());
        entity.setColor(request.getColor());
        entity.setYear(request.getYear());
        entity.setUserId("1");
        return entity;
    }
}
