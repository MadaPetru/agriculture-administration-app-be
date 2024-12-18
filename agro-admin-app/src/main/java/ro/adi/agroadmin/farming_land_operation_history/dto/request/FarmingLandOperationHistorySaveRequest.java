package ro.adi.agroadmin.farming_land_operation_history.dto.request;

import lombok.Builder;
import lombok.Data;
import ro.adi.agroadmin.common.entity.CurrencyType;
import ro.adi.agroadmin.common.entity.OperationType;
import ro.adi.agroadmin.common.entity.PlantType;
import ro.adi.agroadmin.common.entity.WeightMeasureType;

import java.time.LocalDateTime;

@Data
@Builder
public class FarmingLandOperationHistorySaveRequest {
    private OperationType operation;
    private Float estimatedCost;
    private Float estimatedRevenue;
    private Float estimatedHarvest;
    private LocalDateTime appliedAt;
    private String createdBy;
    private Integer farmingLandId;
    private WeightMeasureType estimatedHarvestMeasureType;
    private CurrencyType estimatedCostCurrencyType;
    private PlantType plantType;
    private CurrencyType estimatedRevenueCurrencyType;
}
