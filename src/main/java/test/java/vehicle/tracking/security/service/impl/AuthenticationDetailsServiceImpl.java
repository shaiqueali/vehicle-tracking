package test.java.vehicle.tracking.security.service.impl;

import test.java.vehicle.tracking.feature.user.repository.entity.QUser;
import test.java.vehicle.tracking.feature.user.repository.entity.User;
import test.java.vehicle.tracking.feature.user.service.UserQueryService;
import test.java.vehicle.tracking.security.service.AuthenticationDetailsService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationDetailsServiceImpl implements AuthenticationDetailsService {

    UserQueryService userQueryService;

    @Override
    public UserDetails loadUserByUsername(final String username) {
        log.trace("Start loading user by username [{}]", username);
        final User user = userQueryService.findOne(QUser.user.username.eq(username)).getOrElseThrow(ex -> ex);
        log.trace("Finish loading user by userId [{}]", user.getUserId());
        return user;
    }
}
