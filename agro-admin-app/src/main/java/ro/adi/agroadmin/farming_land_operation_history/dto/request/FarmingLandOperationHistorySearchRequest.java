package ro.adi.agroadmin.farming_land_operation_history.dto.request;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Pageable;
import ro.adi.farming_land_operation_history.dto.request.FarmingLandOperationHistorySearchBy;

@Data
@Builder
public class FarmingLandOperationHistorySearchRequest {
    private Pageable pageable;
    private FarmingLandOperationHistorySearchBy searchBy;
}
