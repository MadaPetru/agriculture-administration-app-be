package ro.adi.user.dto.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class SessionDetailsResponseDto {

    private String token;
    private long expiresIn;
    private Set<UserRoleDto> roles = new HashSet<>();
}
