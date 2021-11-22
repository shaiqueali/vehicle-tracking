package test.java.vehicle.tracking.test.java.vehicle.tracking.feature.vehicle.facade;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import test.java.vehicle.tracking.exception.BusinessServiceException;
import test.java.vehicle.tracking.feature.user.repository.entity.User;
import test.java.vehicle.tracking.feature.vehicle.controller.request.VehicleRequest;
import test.java.vehicle.tracking.feature.vehicle.controller.response.VehicleGetResponse;
import test.java.vehicle.tracking.feature.vehicle.controller.response.VehicleResponse;
import test.java.vehicle.tracking.feature.vehicle.facade.impl.VehicleFacadeImpl;
import test.java.vehicle.tracking.feature.vehicle.repository.entity.Vehicle;
import test.java.vehicle.tracking.feature.vehicle.service.VehicleService;
import test.java.vehicle.tracking.test.java.vehicle.tracking.feature.vehicle.AbstractVehicle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static test.java.vehicle.tracking.exception.constant.ErrorCodeEnum.*;

@RunWith(MockitoJUnitRunner.class)
public class VehicleFacadeTest extends AbstractVehicle {

    @InjectMocks
    private VehicleFacadeImpl vehicleFacade;

    @Mock
    private VehicleService vehicleService;

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void testGetById() {
        Vehicle vehicle = buildVehicle();
        when(vehicleService.getById(any(Integer.class))).thenReturn(vehicle);
        VehicleGetResponse response = vehicleFacade.getById(ID);
        assertNotNull(response);
        assertEquals(ID, response.getId());
        assertThat(response)
                .isEqualToComparingOnlyGivenFields(vehicle,
                        "id", "userId", "registrationNumber", "createdDate", "lastUpdatedDate");
        verify(vehicleService).getById(anyInt());
    }

    @Test
    public void testGetByIdWhenVehicleDoesNotExists() {
        exceptionRule.expect(BusinessServiceException.class);
        exceptionRule.expectMessage(VEHICLE_NOT_FOUND);
        when(vehicleService.getById(any(Integer.class))).thenThrow(new BusinessServiceException(ENTITY_NOT_FOUND, VEHICLE_NOT_FOUND));
        vehicleFacade.getById(ID);
        verify(vehicleService).getById(anyInt());
    }

    @Test
    public void testCreate() {
        VehicleRequest vehicleRequest = buildVehicleRequest();
        when(vehicleService.save(any(Vehicle.class))).thenReturn(buildVehicle());
        VehicleResponse response = vehicleFacade.create(vehicleRequest);
        assertNotNull(response);
        assertEquals(ID, response.getId());
        verify(vehicleService).save(any(Vehicle.class));
    }

    @Test
    public void testUpdate() {
        VehicleRequest vehicleRequest = buildVehicleRequest();
        Vehicle vehicle = buildVehicle();

        // get user from context
        User user = new User();
        user.setUserId(USER_ID);
        Authentication authentication = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        when(SecurityContextHolder.getContext().getAuthentication().getPrincipal()).thenReturn(user);

        when(vehicleService.getByIdAndUserId(anyInt(), anyString())).thenReturn(vehicle);
        when(vehicleService.update(any(Vehicle.class))).thenReturn(vehicle);
        VehicleResponse response = vehicleFacade.update(ID, vehicleRequest);
        assertNotNull(response);
        assertEquals(ID, response.getId());
        verify(vehicleService).update(any(Vehicle.class));
    }

    @Test
    public void testUpdateWhenVehicleDoesNotExists() {
        exceptionRule.expect(BusinessServiceException.class);
        exceptionRule.expectMessage(VEHICLE_NOT_FOUND);

        // get user from context
        User user = new User();
        user.setUserId(USER_ID);
        Authentication authentication = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        when(SecurityContextHolder.getContext().getAuthentication().getPrincipal()).thenReturn(user);

        VehicleRequest vehicleRequest = buildVehicleRequest();
        when(vehicleService.getByIdAndUserId(anyInt(), anyString())).thenThrow(new BusinessServiceException(ENTITY_NOT_FOUND, VEHICLE_NOT_FOUND));
        vehicleFacade.update(ID, vehicleRequest);
        verify(vehicleService).getByIdAndUserId(anyInt(), anyString());
        verify(vehicleService).update(any(Vehicle.class));
    }
}
