package ro.adi.farming_land_operation_history.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FarmingLandOperationHistorySearchRequestDto {
    @NotNull
    private FarmingLandOperationHistoryPageableRequestDto pageable;
    @NotNull
    private FarmingLandOperationHistorySearchBy searchBy;
}
