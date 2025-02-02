package ro.adi.agroadmin.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.adi.agroadmin.common.entity.UserRole;
import ro.adi.user.dto.common.UserRoleDto;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequest {

    private Integer id;
    private Integer version;
    private String email;
    private Set<UserRole> roles = new HashSet<>();
}
