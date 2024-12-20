package ro.adi.agroadmin.farming_land_statistics.service.interceptor;

import ro.adi.common.dto.request.UserRequestDto;
import ro.adi.farming_land_statistics.dto.response.FarmingLandsProfitabilityPerOperationResponseDto;
import ro.adi.farming_land_statistics.dto.response.FarmingLandsProfitabilityPerYearResponseDto;

import java.util.List;

public interface FarmingLandStatisticsServiceInterceptor {

    Float getAreaInHaOfFieldsAdministrated(String username);

    List<FarmingLandsProfitabilityPerOperationResponseDto> getProfitabilityPerOperations(UserRequestDto requestDto, Integer startYear, Integer endYear);

    List<FarmingLandsProfitabilityPerYearResponseDto> revenueAndCostsPerYear(UserRequestDto requestDto, Integer startYear, Integer endYear);

    List<FarmingLandsProfitabilityPerYearResponseDto> revenueAndCostsPerYearForFarmingLand(UserRequestDto requestDto, Integer startYear, Integer endYear, Integer farmingLandId);

    List<FarmingLandsProfitabilityPerOperationResponseDto> getProfitabilityPerOperationsForFarmingLand(UserRequestDto requestDto, Integer startYear, Integer endYear, Integer farmingLandId);

}