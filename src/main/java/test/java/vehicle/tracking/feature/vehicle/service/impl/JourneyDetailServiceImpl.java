package test.java.vehicle.tracking.feature.vehicle.service.impl;

import test.java.vehicle.tracking.exception.BusinessServiceException;
import test.java.vehicle.tracking.feature.vehicle.repository.JourneyDetailRepository;
import test.java.vehicle.tracking.feature.vehicle.repository.entity.JourneyDetail;
import test.java.vehicle.tracking.feature.vehicle.service.JourneyDetailService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

import static test.java.vehicle.tracking.exception.constant.ErrorCodeEnum.ENTITY_NOT_FOUND;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class JourneyDetailServiceImpl implements JourneyDetailService {

    JourneyDetailRepository journeyDetailRepository;

    @Override
    public JourneyDetail getById(final Integer id) {
        log.debug("Get Journey by id [{}]", id);
        return journeyDetailRepository.findById(id).orElseThrow(() -> new BusinessServiceException(ENTITY_NOT_FOUND, "Journey detail does not exists"));
    }

    @Override
    public JourneyDetail save(final JourneyDetail journeyDetail) {
        return journeyDetailRepository.save(journeyDetail);
    }

    @Override
    public JourneyDetail update(final JourneyDetail journeyDetail) {
        return journeyDetailRepository.save(journeyDetail);
    }

    @Override
    public List<JourneyDetail> getAllJourneyDetailsByJourneyId(final Integer journeyId) {
        log.debug("Get all journey details by journey id [{}]", journeyId);
        return journeyDetailRepository.findAllByJourneyIdOrderByIdDesc(journeyId);
    }

    @Override
    public JourneyDetail getCurrentPositionOfVehicleJourney(final Integer journeyId) {
        log.debug("Get current position of journey by journey id [{}]", journeyId);
        return journeyDetailRepository.findFirstByJourneyIdOrderByIdDesc(journeyId).orElseThrow(() -> new BusinessServiceException(ENTITY_NOT_FOUND, "Journey detail does not exists"));
    }

    @Override
    public List<JourneyDetail> getAllJourneyDetailsByStartAndEndDate(final Integer journeyId, final Date startDate, final Date endDate) {
        log.debug("Get full journey by journey id [{}] and between startDate [{}] and endDate [{}]", journeyId, startDate, endDate);
        List<JourneyDetail> journeyDetailEntities = journeyDetailRepository
                .findAllByJourneyIdAndCreatedDateGreaterThanEqualAndLastUpdatedDateLessThanEqual(journeyId, startDate, endDate);
        if (CollectionUtils.isEmpty(journeyDetailEntities)) {
            log.error("journeys not found by journey id [{}] and between startDate [{}] and endDate [{}]", journeyId, startDate, endDate);
            throw new BusinessServiceException(ENTITY_NOT_FOUND, "Journey details does not exists");
        }
        log.info("Journey details [{}] found by journey  id [{}] and between startDate [{}] and endDate [{}]", journeyDetailEntities.size(), journeyId, startDate, endDate);
        return journeyDetailEntities;
    }
}
