package ro.adi.farming_land_operation_history.dto.request;

import jakarta.validation.Valid;
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
    @Valid
    @NotNull
    private FarmingLandOperationHistoryPageableRequestDto pageable;
    @Valid
    @NotNull
    private FarmingLandOperationHistorySearchBy searchBy;
}
