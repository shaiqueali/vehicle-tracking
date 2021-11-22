package test.java.vehicle.tracking.test.java.vehicle.tracking.feature.vehicle;

import test.java.vehicle.tracking.feature.vehicle.controller.request.VehicleRequest;
import test.java.vehicle.tracking.feature.vehicle.controller.response.VehicleGetResponse;
import test.java.vehicle.tracking.feature.vehicle.controller.response.VehicleResponse;
import test.java.vehicle.tracking.feature.vehicle.repository.entity.Vehicle;

import java.time.Instant;
import java.util.Date;

public class AbstractVehicle {

    public static final String GET_VEHICLE_BY_ID = "/api/v1/vehicles/{id}";
    public static final String CREATE_VEHICLE = "/api/v1/vehicles";
    public static final String UPDATE_VEHICLE = "/api/v1/vehicles/{id}";

    public static final Integer ID = 1;
    public static final String USER_ID = "1";
    public static final String COLOR = "WHITE";
    public static final String MAKE = "Audi";
    public static final String MODEL = "Car";
    public static final Integer YEAR = 2021;
    public static final String REG_NUMBER = "ABC-123";


    // Error messages
    public static final String VEHICLE_NOT_FOUND =  "Vehicle does not exists";

    public VehicleRequest buildVehicleRequest() {
        VehicleRequest request = new VehicleRequest();
        request.setRegistrationNumber(REG_NUMBER);
        request.setYear(YEAR);
        request.setModel(MODEL);
        request.setMake(MAKE);
        request.setColor(COLOR);
        return request;
    }

    public VehicleResponse buildVehicleResponse() {
        return new VehicleResponse(ID);
    }
    public VehicleGetResponse buildVehicleGetResponse() {
        VehicleGetResponse response = new VehicleGetResponse();
        Date date = Date.from(Instant.now());
        response.setId(ID);
        response.setUserId(USER_ID);
        response.setRegistrationNumber(REG_NUMBER);
        response.setCreatedDate(date);
        response.setLastUpdatedDate(date);
        return response;
    }

    public Vehicle buildVehicle() {
        Vehicle vehicle = new Vehicle();
        Date date = Date.from(Instant.now());
        vehicle.setId(ID);
        vehicle.setUserId(USER_ID);
        vehicle.setRegistrationNumber(REG_NUMBER);
        vehicle.setMake(MAKE);
        vehicle.setModel(MODEL);
        vehicle.setColor(COLOR);
        vehicle.setYear(YEAR);
        vehicle.setIsDeleted(Boolean.FALSE);
        vehicle.setCreatedDate(date);
        vehicle.setLastUpdatedDate(date);
        return vehicle;
    }
}
