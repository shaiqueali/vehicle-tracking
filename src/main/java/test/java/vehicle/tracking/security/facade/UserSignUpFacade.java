package test.java.vehicle.tracking.security.facade;

import test.java.vehicle.tracking.security.controller.request.UserSignUpRequest;
import test.java.vehicle.tracking.security.controller.response.UserSignUpResponse;

public interface UserSignUpFacade {

    UserSignUpResponse signUpUser(final UserSignUpRequest request);
}
