package ro.adi.agroadmin.farming_land_statistics.service.interceptor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.adi.agroadmin.common.converter.UserMapper;
import ro.adi.agroadmin.farming_land_operation_history.service.FarmingLandOperationHistoryService;
import ro.adi.agroadmin.farming_land_statistics.converter.FarmingLandStatisticsMapper;
import ro.adi.agroadmin.farming_land_statistics.converter.FarmingLandStatisticsPerYearAndOperationMapper;
import ro.adi.agroadmin.farming_land_statistics.service.FarmingLandStatisticsService;
import ro.adi.common.dto.request.UserRequestDto;
import ro.adi.farming_land_statistics.dto.response.FarmingLandsProfitabilityPerOperationResponseDto;
import ro.adi.farming_land_statistics.dto.response.FarmingLandsProfitabilityPerYearResponseDto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FarmingLandStatisticsServiceInterceptorImpl implements FarmingLandStatisticsServiceInterceptor {

    private final UserMapper userMapper;
    private final FarmingLandStatisticsMapper farmingLandStatisticsMapper;
    private final FarmingLandStatisticsPerYearAndOperationMapper farmingLandStatisticsPerYearAndOperationMapper;
    private final FarmingLandStatisticsService farmingLandStatisticsService;
    private final FarmingLandOperationHistoryService farmingLandOperationHistoryService;

    @Override
    public List<FarmingLandsProfitabilityPerOperationResponseDto> getProfitabilityPerOperations(UserRequestDto requestDto, Integer startYear, Integer endYear) {
        var user = userMapper.toUserRequest(requestDto);
        var responses = farmingLandStatisticsService.getProfitabilityPerOperations(user, startYear, endYear);
        return farmingLandStatisticsPerYearAndOperationMapper.toListFarmingLandsProfitabilityPerOperationResponseDto(responses);
    }

    @Override
    public List<FarmingLandsProfitabilityPerYearResponseDto> revenueAndCostsPerYear(UserRequestDto requestDto, Integer startYear, Integer endYear) {
        var request = userMapper.toUserRequest(requestDto);
        var responses = farmingLandStatisticsService.revenueAndCostsPerYear(request, startYear, endYear);
        return farmingLandStatisticsMapper.toListFarmingLandsProfitabilityPerYearResponseDto(responses);
    }

    @Override
    public List<FarmingLandsProfitabilityPerYearResponseDto> revenueAndCostsPerYearForFarmingLand(UserRequestDto requestDto, Integer startYear, Integer endYear, Integer farmingLandId) {
        var user = userMapper.toUserRequest(requestDto);
        var responses = farmingLandOperationHistoryService.revenueAndCostsPerYearForFarmingLand(user, startYear, endYear, farmingLandId);
        return farmingLandStatisticsMapper.toListFarmingLandsProfitabilityPerYearResponseDto(responses);
    }

    @Override
    public List<FarmingLandsProfitabilityPerOperationResponseDto> getProfitabilityPerOperationsForFarmingLand(UserRequestDto requestDto, Integer startYear, Integer endYear, Integer farmingLandId) {
        var user = userMapper.toUserRequest(requestDto);
        var responses = farmingLandOperationHistoryService.getProfitabilityPerOperationsForFarmingLand(user, startYear, endYear, farmingLandId);
        return farmingLandStatisticsPerYearAndOperationMapper.toListFarmingLandsProfitabilityPerOperationResponseDto(responses);
    }
}
