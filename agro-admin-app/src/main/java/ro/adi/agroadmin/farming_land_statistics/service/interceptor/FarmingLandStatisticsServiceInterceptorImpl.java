package ro.adi.agroadmin.farming_land_statistics.service.interceptor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.adi.agroadmin.farming_land.service.FarmingLandService;
import ro.adi.agroadmin.farming_land_operation_history.service.FarmingLandOperationHistoryService;
import ro.adi.agroadmin.farming_land_statistics.converter.FarmingLandStatisticsMapper;
import ro.adi.agroadmin.farming_land_statistics.converter.FarmingLandStatisticsPerYearAndOperationMapper;
import ro.adi.agroadmin.farming_land_statistics.service.FarmingLandStatisticsService;
import ro.adi.farming_land_statistics.dto.response.FarmingLandsProfitabilityPerOperationResponseDto;
import ro.adi.farming_land_statistics.dto.response.FarmingLandsProfitabilityPerYearResponseDto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FarmingLandStatisticsServiceInterceptorImpl implements FarmingLandStatisticsServiceInterceptor {

    private final FarmingLandStatisticsMapper farmingLandStatisticsMapper;
    private final FarmingLandStatisticsPerYearAndOperationMapper farmingLandStatisticsPerYearAndOperationMapper;
    private final FarmingLandService farmingLandService;
    private final FarmingLandStatisticsService farmingLandStatisticsService;
    private final FarmingLandOperationHistoryService farmingLandOperationHistoryService;

    @Override
    public Float getAreaInHaOfFieldsAdministrated() {
        return farmingLandService.getAreaInHaOfFieldsAdministrated();
    }

    @Override
    public List<FarmingLandsProfitabilityPerOperationResponseDto> getProfitabilityPerOperations(Integer startYear, Integer endYear) {
        var responses = farmingLandStatisticsService.getProfitabilityPerOperations(startYear, endYear);
        return farmingLandStatisticsPerYearAndOperationMapper.toListFarmingLandsProfitabilityPerOperationResponseDto(responses);
    }

    @Override
    public List<FarmingLandsProfitabilityPerYearResponseDto> revenueAndCostsPerYear(Integer startYear, Integer endYear) {
        var responses = farmingLandStatisticsService.revenueAndCostsPerYear(startYear, endYear);
        return farmingLandStatisticsMapper.toListFarmingLandsProfitabilityPerYearResponseDto(responses);
    }

    @Override
    public List<FarmingLandsProfitabilityPerYearResponseDto> revenueAndCostsPerYearForFarmingLand(Integer startYear, Integer endYear, Integer farmingLandId) {
        var responses = farmingLandOperationHistoryService.revenueAndCostsPerYearForFarmingLand(startYear, endYear, farmingLandId);
        return farmingLandStatisticsMapper.toListFarmingLandsProfitabilityPerYearResponseDto(responses);
    }

    @Override
    public List<FarmingLandsProfitabilityPerOperationResponseDto> getProfitabilityPerOperationsForFarmingLand(Integer startYear, Integer endYear, Integer farmingLandId) {
        var responses = farmingLandOperationHistoryService.getProfitabilityPerOperationsForFarmingLand(startYear, endYear, farmingLandId);
        return farmingLandStatisticsPerYearAndOperationMapper.toListFarmingLandsProfitabilityPerOperationResponseDto(responses);
    }
}
