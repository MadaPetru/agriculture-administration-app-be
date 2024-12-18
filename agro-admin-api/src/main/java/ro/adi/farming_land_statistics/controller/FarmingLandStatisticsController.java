package ro.adi.farming_land_statistics.controller;

import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.adi.common.dto.request.UserRequestDto;
import ro.adi.farming_land_statistics.dto.response.FarmingLandsProfitabilityPerOperationResponseDto;
import ro.adi.farming_land_statistics.dto.response.FarmingLandsProfitabilityPerYearResponseDto;

import java.util.List;

@RequestMapping(path = "/v1/farming-lands/statistics")
public interface FarmingLandStatisticsController {

    @PostMapping("/profitability/from/year/{startYear}/to/year/{endYear}")
    List<FarmingLandsProfitabilityPerYearResponseDto> revenueAndCostsPerYear(@RequestBody UserRequestDto requestDto, @PathVariable @NotNull Integer startYear, @PathVariable @NotNull Integer endYear);

    @PostMapping("/operations/types/profitability/from/year/{startYear}/to/year/{endYear}")
    List<FarmingLandsProfitabilityPerOperationResponseDto> getProfitabilityPerOperations(@RequestBody UserRequestDto requestDto, @PathVariable @NotNull Integer startYear, @PathVariable @NotNull Integer endYear);

    @PostMapping("/farming-land/{farmingLandId}/profitability/from/year/{startYear}/to/year/{endYear}")
    List<FarmingLandsProfitabilityPerYearResponseDto> revenueAndCostsPerYearForFarmingLand(@RequestBody UserRequestDto requestDto, @PathVariable @NotNull Integer startYear, @PathVariable @NotNull Integer endYear, @PathVariable @NotNull Integer farmingLandId);

    @PostMapping("/farming-land/{farmingLandId}/operations/types/profitability/from/year/{startYear}/to/year/{endYear}")
    List<FarmingLandsProfitabilityPerOperationResponseDto> getProfitabilityPerOperationsForFarmingLand(@RequestBody UserRequestDto requestDto, @PathVariable @NotNull Integer startYear, @PathVariable @NotNull Integer endYear, @PathVariable @NotNull Integer farmingLandId);
}
