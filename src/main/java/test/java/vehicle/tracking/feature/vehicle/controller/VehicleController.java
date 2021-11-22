package test.java.vehicle.tracking.feature.vehicle.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
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
import test.java.vehicle.tracking.feature.vehicle.controller.request.VehicleRequest;
import test.java.vehicle.tracking.feature.vehicle.controller.response.VehicleGetResponse;
import test.java.vehicle.tracking.feature.vehicle.controller.response.VehicleResponse;
import test.java.vehicle.tracking.feature.vehicle.facade.VehicleFacade;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/vehicles")
@Api(tags = "Vehicle", value = "/")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class VehicleController {

    VehicleFacade vehicleFacade;

    @ApiOperation(value = "Get Vehicle", notes = "This api is used for get vehicle by id")
    @GetMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehicleGetResponse> getVehicleById(@PathVariable("id") final Integer id) {
        return new ResponseEntity<>(vehicleFacade.getById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Create Vehicle", notes = "This api is used for create vehicle")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehicleResponse> createVehicle(@Valid @RequestBody final VehicleRequest request) {
        return new ResponseEntity<>(vehicleFacade.create(request), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update Vehicle", notes = "This api is used for update vehicle")
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehicleResponse> updateVehicle(@PathVariable("id") final Integer id,
                                                         @Valid @RequestBody final VehicleRequest request) {
        return new ResponseEntity<>(vehicleFacade.update(id, request), HttpStatus.OK);
    }

}
