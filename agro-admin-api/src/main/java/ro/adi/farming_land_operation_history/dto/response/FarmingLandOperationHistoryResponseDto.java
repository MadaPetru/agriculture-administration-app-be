package ro.adi.farming_land_operation_history.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import ro.adi.farming_land_operation_history.dto.common.*;

import java.time.LocalDateTime;

@Data
@Builder
public class FarmingLandOperationHistoryResponseDto {
    private Integer id;
    private Integer version;
    private OperationTypeDto operation;
    private Float estimatedCost;
    private Integer farmingLandId;
    private Float estimatedHarvest;
    private Float estimatedRevenue;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime appliedAt;
    private EstimatedHarvestMeasureTypeDto estimatedHarvestMeasureType;
    private EstimatedCostCurrencyTypeDto estimatedCostCurrencyType;
    private EstimatedRevenueCurrencyTypeDto estimatedRevenueCurrencyType;
    private PlantTypeDto plantType;
}
