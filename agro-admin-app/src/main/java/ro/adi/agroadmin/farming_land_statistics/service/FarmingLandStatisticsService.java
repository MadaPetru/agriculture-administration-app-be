package ro.adi.agroadmin.farming_land_statistics.service;

import ro.adi.agroadmin.farming_land_statistics.dto.request.FarmingLandsProfitabilityPerOperationAndYearUpdateRequest;
import ro.adi.agroadmin.farming_land_statistics.dto.request.FarmingLandsProfitabilityPerYearUpdateRequest;
import ro.adi.agroadmin.farming_land_statistics.dto.request.UserRequest;
import ro.adi.agroadmin.farming_land_statistics.dto.response.FarmingLandsProfitabilityPerOperationResponse;
import ro.adi.agroadmin.farming_land_statistics.dto.response.FarmingLandsProfitabilityPerYearResponse;

import java.util.List;

public interface FarmingLandStatisticsService {

    void update(FarmingLandsProfitabilityPerYearUpdateRequest request);

    void update(FarmingLandsProfitabilityPerOperationAndYearUpdateRequest request);

    List<FarmingLandsProfitabilityPerOperationResponse> getProfitabilityPerOperations(UserRequest request, Integer startYear, Integer endYear);

    List<FarmingLandsProfitabilityPerYearResponse> revenueAndCostsPerYear(UserRequest request, Integer startYear, Integer endYear);

}
