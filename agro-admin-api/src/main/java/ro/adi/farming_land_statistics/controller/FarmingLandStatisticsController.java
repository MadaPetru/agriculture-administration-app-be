package ro.adi.farming_land_statistics.controller;

import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.adi.farming_land_statistics.dto.response.FarmingLandsProfitabilityPerOperationResponseDto;
import ro.adi.farming_land_statistics.dto.response.FarmingLandsProfitabilityPerYearResponseDto;

import java.util.List;

@RequestMapping(path = "/v1/farming-lands/statistics")
public interface FarmingLandStatisticsController {

    @GetMapping("/farming-lands/ha/administrated")
    Float getAreaInHaOfFieldsAdministrated();

    @GetMapping("/profitability/from/year/{startYear}/to/year/{endYear}")
    List<FarmingLandsProfitabilityPerYearResponseDto> revenueAndCostsPerYear(@PathVariable @NotNull Integer startYear, @PathVariable @NotNull Integer endYear);

    @GetMapping("/operations/types/profitability/from/year/{startYear}/to/year/{endYear}")
    List<FarmingLandsProfitabilityPerOperationResponseDto> getProfitabilityPerOperations(@PathVariable @NotNull Integer startYear, @PathVariable @NotNull Integer endYear);

    @GetMapping("/farming-land/{farmingLandId}/profitability/from/year/{startYear}/to/year/{endYear}")
    List<FarmingLandsProfitabilityPerYearResponseDto> revenueAndCostsPerYearForFarmingLand(@PathVariable @NotNull Integer startYear, @PathVariable @NotNull Integer endYear, @PathVariable @NotNull Integer farmingLandId);

    @GetMapping("/farming-land/{farmingLandId}/operations/types/profitability/from/year/{startYear}/to/year/{endYear}")
    List<FarmingLandsProfitabilityPerOperationResponseDto> getProfitabilityPerOperationsForFarmingLand(@PathVariable @NotNull Integer startYear, @PathVariable @NotNull Integer endYear, @PathVariable @NotNull Integer farmingLandId);
}
