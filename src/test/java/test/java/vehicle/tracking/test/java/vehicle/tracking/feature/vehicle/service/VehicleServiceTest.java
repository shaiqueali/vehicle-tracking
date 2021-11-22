package test.java.vehicle.tracking.test.java.vehicle.tracking.feature.vehicle.service;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import test.java.vehicle.tracking.exception.BusinessServiceException;
import test.java.vehicle.tracking.feature.vehicle.repository.VehicleRepository;
import test.java.vehicle.tracking.feature.vehicle.repository.entity.Vehicle;
import test.java.vehicle.tracking.feature.vehicle.service.impl.VehicleServiceImpl;
import test.java.vehicle.tracking.test.java.vehicle.tracking.feature.vehicle.AbstractVehicle;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class VehicleServiceTest extends AbstractVehicle {

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @InjectMocks
    private VehicleServiceImpl vehicleService;

    @Mock
    private VehicleRepository vehicleRepository;

    @Test
    public void testGetById() {
        when(vehicleRepository.findById(any(Integer.class))).thenReturn(Optional.of(buildVehicle()));
        Vehicle vehicle = vehicleService.getById(ID);
        assertNotNull(vehicle);
        assertEquals(ID, vehicle.getId());
        assertThat(vehicle)
                .isEqualToComparingOnlyGivenFields(vehicle,
                        "id", "userId", "registrationNumber", "make", "model", "year",
                        "color", "isDeleted", "createdDate", "lastUpdatedDate");
        verify(vehicleRepository).findById(anyInt());
    }

    @Test
    public void testGetByIdWhenVehicleDoesNotExists() {
        exceptionRule.expect(BusinessServiceException.class);
        exceptionRule.expectMessage(VEHICLE_NOT_FOUND);
        when(vehicleRepository.findById(any(Integer.class))).thenReturn(Optional.empty());
        vehicleService.getById(ID);
        verify(vehicleRepository).findById(anyInt());
    }

    @Test
    public void testGetByIdAndUserId() {
        when(vehicleRepository.findByIdAndUserId(anyInt(), anyString())).thenReturn(Optional.of(buildVehicle()));
        Vehicle vehicle = vehicleService.getByIdAndUserId(ID, USER_ID);
        assertNotNull(vehicle);
        assertEquals(ID, vehicle.getId());
        assertThat(vehicle)
                .isEqualToComparingOnlyGivenFields(vehicle,
                        "id", "userId", "registrationNumber", "make", "model", "year",
                        "color", "isDeleted", "createdDate", "lastUpdatedDate");
        verify(vehicleRepository).findByIdAndUserId(anyInt(), anyString());
    }

    @Test
    public void testGetByIdAndUserIdWhenVehicleDoesNotExists() {
        exceptionRule.expect(BusinessServiceException.class);
        exceptionRule.expectMessage(VEHICLE_NOT_FOUND);
        when(vehicleRepository.findByIdAndUserId(anyInt(), anyString())).thenReturn(Optional.empty());
        vehicleService.getByIdAndUserId(ID, USER_ID);
        verify(vehicleRepository).findByIdAndUserId(anyInt(), anyString());
    }

    @Test
    public void testCreate() {
        Vehicle vehicle = buildVehicle();
        when(vehicleRepository.save(any(Vehicle.class))).thenReturn(vehicle);
        Vehicle savedVehicle = vehicleService.save(vehicle);
        assertNotNull(vehicle);
        assertThat(savedVehicle)
                .isEqualToComparingOnlyGivenFields(vehicle,
                        "id", "userId", "registrationNumber", "make", "model", "year",
                        "color", "isDeleted", "createdDate", "lastUpdatedDate");
        verify(vehicleRepository).save(any(Vehicle.class));
    }

    @Test
    public void testUpdate() {
        Vehicle vehicle = buildVehicle();
        when(vehicleRepository.save(any(Vehicle.class))).thenReturn(vehicle);
        Vehicle savedVehicle = vehicleService.update(vehicle);
        assertNotNull(vehicle);
        assertThat(savedVehicle)
                .isEqualToComparingOnlyGivenFields(vehicle,
                        "id", "userId", "registrationNumber", "make", "model", "year",
                        "color", "isDeleted", "createdDate", "lastUpdatedDate");
        verify(vehicleRepository).save(any(Vehicle.class));
    }
}
