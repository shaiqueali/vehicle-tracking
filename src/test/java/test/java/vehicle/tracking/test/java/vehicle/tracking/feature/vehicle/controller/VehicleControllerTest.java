package test.java.vehicle.tracking.test.java.vehicle.tracking.feature.vehicle.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import test.java.vehicle.tracking.feature.vehicle.controller.VehicleController;
import test.java.vehicle.tracking.feature.vehicle.controller.request.VehicleRequest;
import test.java.vehicle.tracking.feature.vehicle.controller.response.VehicleGetResponse;
import test.java.vehicle.tracking.feature.vehicle.controller.response.VehicleResponse;
import test.java.vehicle.tracking.feature.vehicle.facade.VehicleFacade;
import test.java.vehicle.tracking.security.config.WebSecurityConfig;
import test.java.vehicle.tracking.security.service.AuthenticationDetailsService;
import test.java.vehicle.tracking.test.java.vehicle.tracking.feature.vehicle.AbstractVehicle;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = VehicleController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class,
        excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
                classes = WebSecurityConfig.class))
public class VehicleControllerTest extends AbstractVehicle {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthenticationDetailsService authenticationDetailsService;

    @MockBean
    private VehicleFacade vehicleFacade;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetVehicleById() throws Exception {
        VehicleGetResponse response = buildVehicleGetResponse();
        when(vehicleFacade.getById(any(Integer.class))).thenReturn(response);
        mockMvc
                .perform(get(GET_VEHICLE_BY_ID, ID)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is(ID)))
                .andExpect(jsonPath("$.userId", Matchers.is(USER_ID)))
                .andExpect(jsonPath("$.registrationNumber", Matchers.is(REG_NUMBER)))
                .andExpect(jsonPath("$.createdDate", notNullValue()))
                .andExpect(jsonPath("$.lastUpdatedDate", notNullValue()));
        verify(vehicleFacade).getById(any(Integer.class));
    }

    @Test
    public void testCreateVehicle() throws Exception {
        VehicleRequest request = buildVehicleRequest();
        VehicleResponse response = buildVehicleResponse();
        when(vehicleFacade.create(any(VehicleRequest.class))).thenReturn(response);
        mockMvc
                .perform(post(CREATE_VEHICLE)
                        .content(objectMapper.writeValueAsString(request))
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", Matchers.is(ID)));
        verify(vehicleFacade).create(any(VehicleRequest.class));
    }

    @Test
    public void testUpdateVehicle() throws Exception {
        VehicleRequest request = buildVehicleRequest();
        VehicleResponse response = buildVehicleResponse();
        when(vehicleFacade.update(any(Integer.class), any(VehicleRequest.class))).thenReturn(response);
        mockMvc
                .perform(put(UPDATE_VEHICLE, ID)
                        .content(objectMapper.writeValueAsString(request))
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is(ID)));
        verify(vehicleFacade).update(any(Integer.class), any(VehicleRequest.class));
    }

}
