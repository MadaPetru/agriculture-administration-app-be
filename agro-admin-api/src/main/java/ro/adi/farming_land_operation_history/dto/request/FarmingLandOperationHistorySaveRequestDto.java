package ro.adi.farming_land_operation_history.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.adi.farming_land_operation_history.dto.common.EstimatedCostCurrencyTypeDto;
import ro.adi.farming_land_operation_history.dto.common.EstimatedHarvestMeasureTypeDto;
import ro.adi.farming_land_operation_history.dto.common.EstimatedRevenueCurrencyTypeDto;
import ro.adi.farming_land_operation_history.dto.common.PlantTypeDto;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FarmingLandOperationHistorySaveRequestDto {
    private String operation;
    private Float estimatedCost;
    private Float estimatedHarvest;
    private Float estimatedRevenue;
    private Date appliedAt;
    private String createdBy;
    private Integer farmingLandId;
    private PlantTypeDto plantType;
    private EstimatedCostCurrencyTypeDto estimatedCostCurrencyType;
    private EstimatedHarvestMeasureTypeDto estimatedHarvestMeasureType;
    private EstimatedRevenueCurrencyTypeDto estimatedRevenueCurrencyType;
}
