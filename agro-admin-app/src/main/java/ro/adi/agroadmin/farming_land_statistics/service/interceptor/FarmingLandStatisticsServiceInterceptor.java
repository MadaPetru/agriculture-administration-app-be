package ro.adi.agroadmin.farming_land_statistics.service.interceptor;

import ro.adi.farming_land_statistics.dto.response.FarmingLandsProfitabilityPerOperationResponseDto;
import ro.adi.farming_land_statistics.dto.response.FarmingLandsProfitabilityPerYearResponseDto;

import java.util.List;

public interface FarmingLandStatisticsServiceInterceptor {

    Float getAreaInHaOfFieldsAdministrated();

    List<FarmingLandsProfitabilityPerOperationResponseDto> getProfitabilityPerOperations(Integer startYear, Integer endYear);

    List<FarmingLandsProfitabilityPerYearResponseDto> revenueAndCostsPerYear(Integer startYear, Integer endYear);

    List<FarmingLandsProfitabilityPerYearResponseDto> revenueAndCostsPerYearForFarmingLand(Integer startYear, Integer endYear, Integer farmingLandId);

    List<FarmingLandsProfitabilityPerOperationResponseDto> getProfitabilityPerOperationsForFarmingLand(Integer startYear, Integer endYear, Integer farmingLandId);

}