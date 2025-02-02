package ro.adi.agroadmin.user.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.adi.agroadmin.common.entity.UserRole;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtGenerationDetailsDto {

    private String username;
    private Set<UserRole> roles = new HashSet<>();
}
