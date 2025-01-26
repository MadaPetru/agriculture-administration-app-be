package ro.adi.farming_land_operation_history.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.adi.farming_land_operation_history.dto.common.*;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FarmingLandOperationHistorySaveRequestDto {
    @NotNull
    private OperationTypeDto operation;
    @NotNull
    private Float estimatedCost;
    @NotNull
    private Float estimatedHarvest;
    @NotNull
    private Float estimatedRevenue;
    @NotNull
    private Date appliedAt;
    @NotNull
    private Integer farmingLandId;
    @NotNull
    private PlantTypeDto plantType;
    @NotNull
    private EstimatedCostCurrencyTypeDto estimatedCostCurrencyType;
    @NotNull
    private EstimatedHarvestMeasureTypeDto estimatedHarvestMeasureType;
    @NotNull
    private EstimatedRevenueCurrencyTypeDto estimatedRevenueCurrencyType;
}
