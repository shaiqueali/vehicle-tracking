package test.java.vehicle.tracking.feature.vehicle.service.impl;

import test.java.vehicle.tracking.exception.BusinessServiceException;
import test.java.vehicle.tracking.feature.vehicle.repository.JourneyRepository;
import test.java.vehicle.tracking.feature.vehicle.repository.entity.Journey;
import test.java.vehicle.tracking.feature.vehicle.service.JourneyService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import static test.java.vehicle.tracking.exception.constant.ErrorCodeEnum.ENTITY_NOT_FOUND;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class JourneyServiceImpl implements JourneyService {

    JourneyRepository journeyRepository;

    @Override
    public Journey getById(final Integer id) {
        log.debug("Get Journey by id [{}]", id);
        return journeyRepository.findById(id).orElseThrow(() -> new BusinessServiceException(ENTITY_NOT_FOUND, "Journey does not exists"));
    }

    @Override
    public List<Journey> getAllByVehicleId(final Integer vehicleId) {
        log.debug("Get all journeys by vehicle id [{}]", vehicleId);
        return journeyRepository.getAllByVehicleId(vehicleId);
    }

    @Transactional
    @Override
    public Journey save(final Journey entity) {
        return journeyRepository.save(entity);
    }

    @Transactional
    @Override
    public Journey update(final Journey entity) {
        return journeyRepository.save(entity);
    }

    @Transactional
    @Override
    public void updateStatus(final Integer id, final String status) {
        log.debug("Updating status [{}] of journey by id [{}]", status, id);
        Date now = Date.from(Instant.now());
        journeyRepository.updateStatus(id, status, now);
        log.info("Journey status [{}] update by id [{}]", status, id);
    }
}
