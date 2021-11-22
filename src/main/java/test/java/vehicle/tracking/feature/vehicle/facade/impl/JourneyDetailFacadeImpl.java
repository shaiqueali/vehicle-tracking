package test.java.vehicle.tracking.feature.vehicle.facade.impl;

import test.java.vehicle.tracking.feature.vehicle.controller.request.JourneyDetailRequest;
import test.java.vehicle.tracking.feature.vehicle.controller.request.SearchJourneyDetailRequest;
import test.java.vehicle.tracking.feature.vehicle.controller.response.JourneyDetailGetResponse;
import test.java.vehicle.tracking.feature.vehicle.facade.JourneyDetailFacade;
import test.java.vehicle.tracking.feature.vehicle.repository.entity.Journey;
import test.java.vehicle.tracking.feature.vehicle.repository.entity.JourneyDetail;
import test.java.vehicle.tracking.feature.vehicle.service.JourneyDetailService;
import test.java.vehicle.tracking.feature.vehicle.service.JourneyService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

import static test.java.vehicle.tracking.constant.JourneyStatus.INPROGRESS;
import static test.java.vehicle.tracking.constant.JourneyStatus.SCHEDULED;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class JourneyDetailFacadeImpl implements JourneyDetailFacade {

    ModelMapper modelMapper;

    JourneyService journeyService;

    JourneyDetailService journeyDetailService;

    @Override
    public JourneyDetailGetResponse getById(@NotNull final Integer id) {
        final JourneyDetail journeyDetail = journeyDetailService.getById(id);
        log.info("Found Journey detail [{}]", id);
        return modelMapper.map(journeyDetail, JourneyDetailGetResponse.class);
    }

    @Override
    public Integer create(JourneyDetailRequest journeyDetailRequest) {
        log.debug("Journey detail create request [{}]", journeyDetailRequest);
        final Journey journey = journeyService.getById(journeyDetailRequest.getJourneyId());
        log.debug("Found Journey [{}]", journey.getId());
        // Update Journey status from SCHEDULED to INPROGRESS if first entry for Journey detail
        if (StringUtils.equals(journey.getStatus(), SCHEDULED.name())) {
            journeyService.updateStatus(journey.getId(), INPROGRESS.name());
        }
        JourneyDetail journeyDetail = journeyDetailService.save(buildJourneyDetailEntity(journeyDetailRequest, journey));
        log.info("Journey detail created with id [{}]", journeyDetail.getId());
        return journeyDetail.getId();
    }

    @Override
    public Integer update(@NotNull final Integer id, final JourneyDetailRequest journeyDetailRequest) {
        log.debug("Journey detail update request [{}] with id [{}]", journeyDetailRequest, id);
        final JourneyDetail journeyDetail = journeyDetailService.getById(id);
        final Journey journey = journeyService.getById(journeyDetailRequest.getJourneyId());
        log.debug("Found Journey [{}]", journey.getId());
        JourneyDetail savedJourneyDetail = journeyDetailService.save(buildJourneyDetailEntity(journeyDetailRequest, journeyDetail, journey));
        log.info("Journey detail updated with id [{}]", savedJourneyDetail.getId());
        return savedJourneyDetail.getId();
    }

    @Override
    public JourneyDetailGetResponse getCurrentPositionOfVehicleJourney(@NotNull final Integer journeyId) {
        final JourneyDetail journeyDetail = journeyDetailService.getCurrentPositionOfVehicleJourney(journeyId);
        log.info("Journey detail created with id [{}]", journeyDetail.getId());
        return buildJourneyDetailGetResponse(journeyDetail);
    }

    @Override
    public List<JourneyDetailGetResponse> searchJourneyDetailsByStartAndEndDate(@NotNull final Integer journeyId, final SearchJourneyDetailRequest request) {
        final List<JourneyDetail> journeyDetailEntities = journeyDetailService.getAllJourneyDetailsByStartAndEndDate(journeyId, request.getStartDate(), request.getEndDate());
        if (!journeyDetailEntities.isEmpty())
            log.info("Journeys detail [{}] found by journey id [{}]", journeyDetailEntities.size(), journeyId);
        return journeyDetailEntities.stream().map(this::buildJourneyDetailGetResponse).collect(Collectors.toList());
    }

    private JourneyDetailGetResponse buildJourneyDetailGetResponse(final JourneyDetail entity) {
        JourneyDetailGetResponse response = new JourneyDetailGetResponse();
        response.setId(entity.getId());
        response.setJourneyId(entity.getJourneyId());
        response.setLongitude(entity.getLongitude());
        response.setLatitude(entity.getLatitude());
        response.setCreatedDate(entity.getCreatedDate());
        response.setLastUpdatedDate(entity.getLastUpdatedDate());
        return response;
    }

    private JourneyDetail buildJourneyDetailEntity(final JourneyDetailRequest request, final Journey journey) {
        JourneyDetail journeyDetail = new JourneyDetail();
        journeyDetail.setJourneyId(journey.getId());
        journeyDetail.setLongitude(request.getLongitude());
        journeyDetail.setLatitude(request.getLatitude());
        journeyDetail.setIsDeleted(Boolean.FALSE);
        return journeyDetail;
    }

    private JourneyDetail buildJourneyDetailEntity(final JourneyDetailRequest request, final JourneyDetail journeyDetail, final Journey journey) {
        journeyDetail.setJourneyId(journey.getId());
        journeyDetail.setLongitude(request.getLongitude());
        journeyDetail.setLatitude(request.getLatitude());
        return journeyDetail;
    }
}
