package ro.adi.agroadmin.user.dto.response;

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
public class UserLoginResponse {
    private String email;
    private Set<UserRole> roles = new HashSet<>();
}
