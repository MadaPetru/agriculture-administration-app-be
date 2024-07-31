package ro.adi.agroadmin.farming_land_operation_history.dto.response;

import lombok.Builder;
import lombok.Data;
import ro.adi.agroadmin.common.entity.CurrencyType;
import ro.adi.agroadmin.common.entity.PlantType;
import ro.adi.agroadmin.common.entity.WeightMeasureType;

import java.time.LocalDateTime;

@Data
@Builder
public class FarmingLandOperationHistoryResponse {
    private Integer id;
    private Integer version;
    private String operation;
    private Float estimatedCost;
    private Integer farmingLandId;
    private Float estimatedHarvest;
    private Float estimatedRevenue;
    private LocalDateTime appliedAt;
    private WeightMeasureType estimatedHarvestMeasureType;
    private CurrencyType estimatedCostCurrencyType;
    private PlantType plantType;
    private CurrencyType estimatedRevenueCurrencyType;
}