package test.java.vehicle.tracking.feature.vehicle.controller;

import test.java.vehicle.tracking.feature.vehicle.controller.request.JourneyRequest;
import test.java.vehicle.tracking.feature.vehicle.controller.request.JourneyStatusRequest;
import test.java.vehicle.tracking.feature.vehicle.controller.response.JourneyGetResponse;
import test.java.vehicle.tracking.feature.vehicle.facade.JourneyFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
@RequestMapping("/api/v1/journeys")
@Api(tags = "Journey", value = "/")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class JourneyController {

    JourneyFacade journeyFacade;

    @ApiOperation(value = "Get Journey", notes = "This api is used for get journey by id")
    @GetMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JourneyGetResponse> getJourneyById(@PathVariable("id") final Integer id) {
        return new ResponseEntity<>(journeyFacade.getById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Create Journey", notes = "This api is used for create journey")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> createJourney(@Valid @RequestBody final JourneyRequest request) {
        return new ResponseEntity<>(journeyFacade.create(request), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update Journey", notes = "This api is used for update journey")
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> updateJourney(@PathVariable("id") final Integer id,
                                                 @Valid @RequestBody final JourneyRequest request) {
        return new ResponseEntity<>(journeyFacade.update(id, request), HttpStatus.OK);
    }

    @ApiOperation(value = "Get All Journeys by vehicle id", notes = "This api is used for get all journeys by id")
    @GetMapping(value = "/{vehicleId}/vehicles", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<JourneyGetResponse>> getAllJourneysByVehicleId(@PathVariable("vehicleId") final Integer vehicleId) {
        return new ResponseEntity<>(journeyFacade.getAllByVehicleId(vehicleId), HttpStatus.OK);
    }

    @ApiOperation(value = "Update Journey status", notes = "This api is used for update journey status")
    @PatchMapping(value = "/{id}/status", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateJourneyStatus(@PathVariable("id") final Integer id,
                                                    @Valid @RequestBody final JourneyStatusRequest journeyStatusRequest) {
        journeyFacade.updateStatus(id, journeyStatusRequest.getStatus());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
