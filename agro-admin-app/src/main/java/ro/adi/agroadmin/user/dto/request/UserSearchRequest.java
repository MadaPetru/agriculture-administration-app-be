package ro.adi.agroadmin.user.dto.request;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Pageable;
import ro.adi.user.dto.request.UserSearchBy;

@Data
@Builder
public class UserSearchRequest {

    private Pageable pageable;
    private UserSearchBy searchBy;
}
