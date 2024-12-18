package ro.adi.farming_land_statistics.dto.response;

import lombok.Builder;
import lombok.Data;
import ro.adi.farming_land_operation_history.dto.common.OperationTypeDto;

@Data
@Builder
public class FarmingLandsProfitabilityPerOperationResponseDto {

    private Float cost;
    private Float revenue;
    private OperationTypeDto operation;
}
