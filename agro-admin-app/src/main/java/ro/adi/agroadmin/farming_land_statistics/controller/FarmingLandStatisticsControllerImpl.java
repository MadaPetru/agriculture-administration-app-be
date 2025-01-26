package ro.adi.agroadmin.farming_land_statistics.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ro.adi.agroadmin.farming_land_statistics.service.interceptor.FarmingLandStatisticsServiceInterceptor;
import ro.adi.farming_land_statistics.controller.FarmingLandStatisticsController;
import ro.adi.farming_land_statistics.dto.response.FarmingLandsProfitabilityPerOperationResponseDto;
import ro.adi.farming_land_statistics.dto.response.FarmingLandsProfitabilityPerYearResponseDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FarmingLandStatisticsControllerImpl implements FarmingLandStatisticsController {

    private final FarmingLandStatisticsServiceInterceptor farmingLandStatisticsServiceInterceptor;

    @Override
    public Float getAreaInHaOfFieldsAdministrated() {
        return farmingLandStatisticsServiceInterceptor.getAreaInHaOfFieldsAdministrated();
    }

    @Override
    public List<FarmingLandsProfitabilityPerYearResponseDto> revenueAndCostsPerYear(Integer startYear, Integer endYear) {
        return farmingLandStatisticsServiceInterceptor.revenueAndCostsPerYear(startYear, endYear);
    }

    @Override
    public List<FarmingLandsProfitabilityPerOperationResponseDto> getProfitabilityPerOperations(Integer startYear, Integer endYear) {
        return farmingLandStatisticsServiceInterceptor.getProfitabilityPerOperations(startYear, endYear);
    }

    @Override
    public List<FarmingLandsProfitabilityPerYearResponseDto> revenueAndCostsPerYearForFarmingLand(Integer startYear, Integer endYear, Integer farmingLandId) {
        return farmingLandStatisticsServiceInterceptor.revenueAndCostsPerYearForFarmingLand(startYear, endYear, farmingLandId);
    }

    @Override
    public List<FarmingLandsProfitabilityPerOperationResponseDto> getProfitabilityPerOperationsForFarmingLand(Integer startYear, Integer endYear, Integer farmingLandId) {
        return farmingLandStatisticsServiceInterceptor.getProfitabilityPerOperationsForFarmingLand(startYear, endYear, farmingLandId);
    }
}
