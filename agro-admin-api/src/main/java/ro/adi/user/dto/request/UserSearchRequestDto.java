package ro.adi.user.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserSearchRequestDto {

    @NotNull
    private UserSearchPageableRequestDto pageable;
    private UserSearchBy searchBy;
}
