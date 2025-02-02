package ro.adi.agroadmin.farming_land_statistics.dto.request;

import lombok.Builder;
import lombok.Data;
import ro.adi.agroadmin.common.entity.OperationType;

@Data
@Builder
public class FarmingLandsProfitabilityPerOperationAndYearUpdateRequest {

    private Float cost;
    private Integer year;
    private Float revenue;
    private Integer createdBy;
    private OperationType operation;
}
