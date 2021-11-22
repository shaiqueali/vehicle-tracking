package test.java.vehicle.tracking.feature.vehicle.facade.impl;

import test.java.vehicle.tracking.constant.JourneyStatus;
import test.java.vehicle.tracking.feature.vehicle.controller.request.JourneyRequest;
import test.java.vehicle.tracking.feature.vehicle.controller.response.JourneyDetailGetResponse;
import test.java.vehicle.tracking.feature.vehicle.controller.response.JourneyGetResponse;
import test.java.vehicle.tracking.feature.vehicle.facade.JourneyFacade;
import test.java.vehicle.tracking.feature.vehicle.repository.entity.Journey;
import test.java.vehicle.tracking.feature.vehicle.repository.entity.JourneyDetail;
import test.java.vehicle.tracking.feature.vehicle.repository.entity.Vehicle;
import test.java.vehicle.tracking.feature.vehicle.service.JourneyService;
import test.java.vehicle.tracking.feature.vehicle.service.VehicleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class JourneyFacadeImpl implements JourneyFacade {

    JourneyService journeyService;

    VehicleService vehicleService;

    @Override
    public JourneyGetResponse getById(@NotNull final Integer id) {
        final Journey journey = journeyService.getById(id);
        log.info("Found Journey [{}]", id);
        return buildJourneyGetResponse(journey);
    }

    @Override
    public Integer create(JourneyRequest journeyRequest) {
        log.debug("Journey create request [{}]", journeyRequest);
        final Vehicle vehicle = vehicleService.getById(journeyRequest.getVehicleId());
        log.info("Found Vehicle [{}]", vehicle.getId());
        final Journey journey = journeyService.save(buildJourneyEntity(journeyRequest, vehicle));
        log.info("Journey created with id [{}]", journey.getId());
        return journey.getId();
    }

    @Override
    public Integer update(@NotNull final Integer id, final JourneyRequest journeyRequest) {
        log.debug("Journey update request [{}]", journeyRequest);
        final Journey journey = journeyService.getById(id);
        log.info("Found Journey [{}]", journey.getId());
        final Vehicle vehicle = vehicleService.getById(journeyRequest.getVehicleId());
        log.info("Found Vehicle [{}]", vehicle.getId());
        final Journey savedJourney = journeyService.save(buildJourneyEntity(journeyRequest, journey, vehicle));
        log.info("Journey updated with id [{}]", savedJourney.getId());
        return savedJourney.getId();
    }

    @Override
    public List<JourneyGetResponse> getAllByVehicleId(@NotNull final Integer vehicleId) {
        List<Journey> journeys = journeyService.getAllByVehicleId(vehicleId);
        if (!journeys.isEmpty())
            log.info("Journeys [{}] found by vehicle id [{}]", journeys.size(), vehicleId);
        return journeys.stream().map(this::buildJourneyGetResponse).collect(Collectors.toList());
    }

    @Override
    public void updateStatus(@NotNull final Integer id, final String status) {
        final Journey journey = journeyService.getById(id);
        log.info("Found Journey [{}]", journey.getId());
        journeyService.updateStatus(journey.getId(), status);
    }

    private JourneyGetResponse buildJourneyGetResponse(final Journey entity) {
        JourneyGetResponse response = new JourneyGetResponse();
        response.setId(entity.getId());
        response.setVehicleId(entity.getVehicleId());
        response.setStart(entity.getStart());
        response.setDestination(entity.getDestination());
        response.setStatus(entity.getStatus());
        response.setCreatedDate(entity.getCreatedDate());
        response.setLastUpdatedDate(entity.getLastUpdatedDate());
        response.setJourneyDetails(entity.getJourneyDetails().stream().map(this::buildJourneyDetailGetResponse).collect(Collectors.toList()));
        return response;
    }

    private JourneyDetailGetResponse buildJourneyDetailGetResponse(final JourneyDetail entity) {
        JourneyDetailGetResponse response = new JourneyDetailGetResponse();
        response.setId(entity.getId());
        response.setJourneyId(entity.getJourneyId());
        response.setLatitude(entity.getLatitude());
        response.setLongitude(entity.getLongitude());
        response.setCreatedDate(entity.getCreatedDate());
        response.setLastUpdatedDate(entity.getLastUpdatedDate());
        return response;
    }

    private Journey buildJourneyEntity(final JourneyRequest request, final Vehicle vehicle) {
        Journey entity = new Journey();
        entity.setVehicleId(vehicle.getId());
        entity.setStart(request.getStart());
        entity.setDestination(request.getDestination());
        entity.setStatus(JourneyStatus.SCHEDULED.name());
        entity.setIsDeleted(Boolean.FALSE);
        return entity;
    }

    private Journey buildJourneyEntity(final JourneyRequest request, final Journey entity, final Vehicle vehicle) {
        entity.setVehicleId(vehicle.getId());
        entity.setStart(request.getStart());
        entity.setDestination(request.getDestination());
        return entity;
    }
}
