package ro.adi.farming_land_operation_history.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import ro.adi.farming_land_operation_history.dto.common.*;

import java.util.Date;

@Data
@Builder
public class FarmingLandOperationHistoryUpdateRequestDto {
    @NotNull
    private Integer id;
    @NotNull
    private Integer version;
    @NotNull
    private Date appliedAt;
    @NotNull
    private Float estimatedCost;
    @NotNull
    private Integer farmingLandId;
    @NotNull
    private OperationTypeDto operation;
    @NotNull
    private Float estimatedHarvest;
    @NotNull
    private Float estimatedRevenue;
    @NotNull
    private PlantTypeDto plantType;
    @NotNull
    private EstimatedCostCurrencyTypeDto estimatedCostCurrencyType;
    @NotNull
    private EstimatedHarvestMeasureTypeDto estimatedHarvestMeasureType;
    @NotNull
    private EstimatedRevenueCurrencyTypeDto estimatedRevenueCurrencyType;
}
