package ro.adi.agroadmin.farming_land_operation_history.dto.request;

import lombok.Builder;
import lombok.Data;
import ro.adi.agroadmin.common.entity.CurrencyType;
import ro.adi.agroadmin.common.entity.PlantType;
import ro.adi.agroadmin.common.entity.WeightMeasureType;

import java.util.Date;

@Data
@Builder
public class FarmingLandOperationHistoryUpdateRequest {
    private Integer id;
    private Integer version;
    private Date appliedAt;
    private String createdBy;
    private String operation;
    private Float estimatedCost;
    private Integer farmingLandId;
    private Float estimatedHarvest;
    private Float estimatedRevenue;
    private PlantType plantType;
    private CurrencyType estimatedCostCurrencyType;
    private WeightMeasureType estimatedHarvestMeasureType;
    private CurrencyType estimatedRevenueCurrencyType;
}
