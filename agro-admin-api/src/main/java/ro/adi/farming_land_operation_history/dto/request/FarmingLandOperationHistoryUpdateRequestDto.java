package ro.adi.farming_land_operation_history.dto.request;

import lombok.Builder;
import lombok.Data;
import ro.adi.farming_land_operation_history.dto.common.EstimatedCostCurrencyTypeDto;
import ro.adi.farming_land_operation_history.dto.common.EstimatedHarvestMeasureTypeDto;
import ro.adi.farming_land_operation_history.dto.common.EstimatedRevenueCurrencyTypeDto;
import ro.adi.farming_land_operation_history.dto.common.PlantTypeDto;

import java.util.Date;

@Data
@Builder
public class FarmingLandOperationHistoryUpdateRequestDto {
    private Integer id;
    private Integer version;
    private Date appliedAt;
    private String createdBy;
    private String operation;
    private Float estimatedCost;
    private Integer farmingLandId;
    private Float estimatedHarvest;
    private Float estimatedRevenue;
    private PlantTypeDto plantType;
    private EstimatedCostCurrencyTypeDto estimatedCostCurrencyType;
    private EstimatedHarvestMeasureTypeDto estimatedHarvestMeasureType;
    private EstimatedRevenueCurrencyTypeDto estimatedRevenueCurrencyType;
}
