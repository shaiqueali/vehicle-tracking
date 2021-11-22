package test.java.vehicle.tracking.feature.vehicle.controller;

import test.java.vehicle.tracking.feature.vehicle.controller.request.JourneyDetailRequest;
import test.java.vehicle.tracking.feature.vehicle.controller.request.SearchJourneyDetailRequest;
import test.java.vehicle.tracking.feature.vehicle.controller.response.JourneyDetailGetResponse;
import test.java.vehicle.tracking.feature.vehicle.facade.JourneyDetailFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/journey/details")
@Api(tags = "JourneyDetail", value = "/")
public class JourneyDetailController {

    JourneyDetailFacade journeyDetailFacade;

    @ApiOperation(value = "Get Journey", notes = "This api is used for get journey detail by id")
    @GetMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JourneyDetailGetResponse> getJourneyDetailById(@PathVariable("id") final Integer id) {
        return new ResponseEntity<>(journeyDetailFacade.getById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Create Journey Detail", notes = "This api is used for create journey detail")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> createJourneyDetail(@Valid @RequestBody final JourneyDetailRequest request) {
        return new ResponseEntity<>(journeyDetailFacade.create(request), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update Journey Detail", notes = "This api is used for update journey detail")
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> updateJourneyDetail(@PathVariable("id") final Integer id,
                                                       @Valid @RequestBody final JourneyDetailRequest request) {
        return new ResponseEntity<>(journeyDetailFacade.update(id, request), HttpStatus.OK);
    }

    @ApiOperation(value = "Get Current Position Of Vehicle", notes = "This api is used for get current position of vehicle")
    @GetMapping(value = "/{journeyId}/current", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JourneyDetailGetResponse> getCurrentPositionOfVehicleJourney(@PathVariable("journeyId") final Integer journeyId) {
        return new ResponseEntity<>(journeyDetailFacade.getCurrentPositionOfVehicleJourney(journeyId), HttpStatus.OK);
    }

    @ApiOperation(value = "Search journey details", notes = "This api is used for search journey detail")
    @PostMapping(value = "/{journeyId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<JourneyDetailGetResponse>> searchJourneyDetails(@PathVariable("journeyId") final Integer journeyId,
                                                                               @RequestBody SearchJourneyDetailRequest request) {
        return new ResponseEntity<>(journeyDetailFacade.searchJourneyDetailsByStartAndEndDate(journeyId, request), HttpStatus.OK);
    }
}
