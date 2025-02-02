package ro.adi.agroadmin.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.adi.agroadmin.common.entity.UserRole;
import ro.adi.user.dto.common.UserRoleDto;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private Integer id;
    private String email;
    private Integer version;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Set<UserRole> roles = new HashSet<>();
}
