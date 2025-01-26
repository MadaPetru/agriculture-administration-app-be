package ro.adi.agroadmin.farming_land_statistics.service;

import ro.adi.agroadmin.farming_land_statistics.dto.request.FarmingLandsProfitabilityPerYearUpdateRequest;
import ro.adi.agroadmin.farming_land_statistics.dto.response.FarmingLandsProfitabilityPerOperationResponse;
import ro.adi.agroadmin.farming_land_statistics.dto.response.FarmingLandsProfitabilityPerYearResponse;

import java.util.List;

public interface FarmingLandStatisticsService {

    void update(FarmingLandsProfitabilityPerYearUpdateRequest request);

    List<FarmingLandsProfitabilityPerOperationResponse> getProfitabilityPerOperations(Integer startYear, Integer endYear);

    List<FarmingLandsProfitabilityPerYearResponse> revenueAndCostsPerYear(Integer startYear, Integer endYear);

}
