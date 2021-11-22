package test.java.vehicle.tracking.feature.vehicle.facade;

import test.java.vehicle.tracking.feature.vehicle.controller.request.VehicleRequest;
import test.java.vehicle.tracking.feature.vehicle.controller.response.VehicleGetResponse;
import test.java.vehicle.tracking.feature.vehicle.controller.response.VehicleResponse;

public interface VehicleFacade {

    VehicleGetResponse getById(final Integer id);

    VehicleResponse create(final VehicleRequest vehicleRequest);

    VehicleResponse update(final Integer id, final VehicleRequest vehicleRequest);

}
