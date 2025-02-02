package ro.adi.agroadmin.user.service;

import org.springframework.security.core.userdetails.UserDetails;
import ro.adi.agroadmin.common.entity.UserRole;
import ro.adi.agroadmin.user.dto.request.JwtGenerationDetailsDto;

import java.util.Set;

public interface JwtService {

    String generateToken(JwtGenerationDetailsDto userDetails);

    long getExpirationTimestampAsMilliseconds();

    boolean isTokenValid(String token, UserDetails userDetails);

    String extractUsername(String token);

    Set<UserRole> extractRoles(String token);
}
