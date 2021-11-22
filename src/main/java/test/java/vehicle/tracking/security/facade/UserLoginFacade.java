package test.java.vehicle.tracking.security.facade;


import test.java.vehicle.tracking.security.controller.request.UserLoginRequest;
import test.java.vehicle.tracking.security.controller.response.UserLoginResponse;

public interface UserLoginFacade {

    UserLoginResponse loginUser(final UserLoginRequest request);
}
