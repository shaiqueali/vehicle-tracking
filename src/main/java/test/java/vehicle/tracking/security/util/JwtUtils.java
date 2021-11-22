package test.java.vehicle.tracking.security.util;

import com.google.common.collect.Maps;
import test.java.vehicle.tracking.feature.user.repository.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;

import static org.apache.commons.collections4.ListUtils.emptyIfNull;
import static org.apache.commons.lang3.StringUtils.EMPTY;


@Slf4j
@Component
public class JwtUtils {

    @Value("${app.jwtSecret:secret-key}")
    private String jwtSecret;

    @Value("${app.jwtExpirationS:3600}")
    private Integer jwtTokenValidityInSeconds;


    public String generateJwtToken(final Authentication authentication) {
        final User user = (User) authentication.getPrincipal();
        final Date tokenExpiration = Date.from(LocalDateTime.now().plusSeconds(jwtTokenValidityInSeconds).atZone(ZoneId.systemDefault()).toInstant());
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setClaims(buildClaims(user))
                .setSubject(user.getUsername())
                .setIssuedAt(new Date()).setExpiration(tokenExpiration)
                .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
    }

    private Map<String, Object> buildClaims(final User user) {
        final Map<String, Object> claims = Maps.newHashMap();
        claims.put("userId", user.getUserId());
        claims.put("username", user.getUsername());
        claims.put("userType", user.getUserType());
        claims.put("roles", emptyIfNull(user.getRoles()));
        return claims;
    }

    public String getUserNameFromJwtToken(final String token) {
        return parseClaimsJws(token).getBody().getOrDefault("username", EMPTY).toString();
    }

    public boolean validateJwtToken(final String authToken) {
        try {
            final Jws<Claims> claimsJws = parseClaimsJws(authToken);
            return !claimsJws.getBody().getExpiration().before(new Date());
        } catch (SignatureException e) {
            log.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }

    private Jws<Claims> parseClaimsJws(final String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
    }

}
