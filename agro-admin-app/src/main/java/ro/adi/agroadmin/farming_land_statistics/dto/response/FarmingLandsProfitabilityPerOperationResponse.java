package ro.adi.agroadmin.farming_land_statistics.dto.response;

import lombok.Builder;
import lombok.Data;
import ro.adi.agroadmin.common.entity.OperationType;

@Data
@Builder
public class FarmingLandsProfitabilityPerOperationResponse {

    private Float cost;
    private Float revenue;
    private OperationType operation;
}
