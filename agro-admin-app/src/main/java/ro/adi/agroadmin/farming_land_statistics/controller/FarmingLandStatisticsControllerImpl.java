package ro.adi.agroadmin.farming_land_statistics.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ro.adi.agroadmin.farming_land_statistics.service.interceptor.FarmingLandStatisticsServiceInterceptor;
import ro.adi.common.dto.request.UserRequestDto;
import ro.adi.farming_land_statistics.controller.FarmingLandStatisticsController;
import ro.adi.farming_land_statistics.dto.response.FarmingLandsProfitabilityPerOperationResponseDto;
import ro.adi.farming_land_statistics.dto.response.FarmingLandsProfitabilityPerYearResponseDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FarmingLandStatisticsControllerImpl implements FarmingLandStatisticsController {

    private final FarmingLandStatisticsServiceInterceptor farmingLandStatisticsServiceInterceptor;

    @Override
    public Float getAreaInHaOfFieldsAdministrated(String username) {
        return farmingLandStatisticsServiceInterceptor.getAreaInHaOfFieldsAdministrated(username);
    }

    @Override
    public List<FarmingLandsProfitabilityPerYearResponseDto> revenueAndCostsPerYear(UserRequestDto requestDto, Integer startYear, Integer endYear) {
        return farmingLandStatisticsServiceInterceptor.revenueAndCostsPerYear(requestDto, startYear, endYear);
    }

    @Override
    public List<FarmingLandsProfitabilityPerOperationResponseDto> getProfitabilityPerOperations(UserRequestDto requestDto, Integer startYear, Integer endYear) {
        return farmingLandStatisticsServiceInterceptor.getProfitabilityPerOperations(requestDto, startYear, endYear);
    }

    @Override
    public List<FarmingLandsProfitabilityPerYearResponseDto> revenueAndCostsPerYearForFarmingLand(UserRequestDto requestDto, Integer startYear, Integer endYear, Integer farmingLandId) {
        return farmingLandStatisticsServiceInterceptor.revenueAndCostsPerYearForFarmingLand(requestDto, startYear, endYear, farmingLandId);
    }

    @Override
    public List<FarmingLandsProfitabilityPerOperationResponseDto> getProfitabilityPerOperationsForFarmingLand(UserRequestDto requestDto, Integer startYear, Integer endYear, Integer farmingLandId) {
        return farmingLandStatisticsServiceInterceptor.getProfitabilityPerOperationsForFarmingLand(requestDto, startYear, endYear, farmingLandId);
    }
}
