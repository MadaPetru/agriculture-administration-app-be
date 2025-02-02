package ro.adi.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.adi.user.dto.common.UserRoleDto;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequestDto {

    private Integer id;
    private Integer version;
    private String email;
    private Set<UserRoleDto> roles = new HashSet<>();
}
