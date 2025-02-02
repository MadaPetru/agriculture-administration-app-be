package ro.adi.agroadmin.user.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ro.adi.agroadmin.common.entity.UserRole;
import ro.adi.agroadmin.config.JwtProperties;
import ro.adi.agroadmin.user.dto.request.JwtGenerationDetailsDto;

import java.security.Key;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {

    private final JwtProperties jwtProperties;

    @Override
    public String generateToken(JwtGenerationDetailsDto userDetails) {
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("roles", userDetails.getRoles());
        return generateToken(claims, userDetails);
    }

    @Override
    public long getExpirationTimestampAsMilliseconds() {
        return jwtProperties.getExpirationTime();
    }

    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    @Override
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    @Override
    public Set<UserRole> extractRoles(String token) {
        return extractClaim(token, claims -> {
            List<String> rolesAsString = claims.get("roles", List.class);
            return rolesAsString.stream().map(UserRole::valueOf).collect(Collectors.toSet());
        });
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private String generateToken(Map<String, Object> extraClaims, JwtGenerationDetailsDto jwtGenerationDetailsDto) {
        return buildToken(extraClaims, jwtGenerationDetailsDto, jwtProperties.getExpirationTime());
    }

    private String buildToken(
            Map<String, Object> extraClaims,
            JwtGenerationDetailsDto jwtGenerationDetailsDto,
            long expiration
    ) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(jwtGenerationDetailsDto.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtProperties.getSecretKey());
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
