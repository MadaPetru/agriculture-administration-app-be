package ro.adi.agroadmin.farming_land_statistics.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRequest {

    private String createdBy;
}
