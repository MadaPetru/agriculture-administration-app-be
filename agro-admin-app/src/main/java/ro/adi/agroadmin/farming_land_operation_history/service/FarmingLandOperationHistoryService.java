package ro.adi.agroadmin.farming_land_operation_history.service;

import org.springframework.data.domain.PageImpl;
import ro.adi.agroadmin.farming_land_operation_history.dto.request.FarmingLandOperationHistorySaveRequest;
import ro.adi.agroadmin.farming_land_operation_history.dto.request.FarmingLandOperationHistorySearchRequest;
import ro.adi.agroadmin.farming_land_operation_history.dto.request.FarmingLandOperationHistoryUpdateRequest;
import ro.adi.agroadmin.farming_land_operation_history.dto.response.FarmingLandOperationHistoryResponse;
import ro.adi.agroadmin.farming_land_statistics.dto.response.FarmingLandsProfitabilityPerOperationResponse;
import ro.adi.agroadmin.farming_land_statistics.dto.response.FarmingLandsProfitabilityPerYearResponse;

import java.util.List;

public interface FarmingLandOperationHistoryService {
    void saveFarmingLandOperationHistory(FarmingLandOperationHistorySaveRequest request);

    void updateFarmingLandOperationHistory(FarmingLandOperationHistoryUpdateRequest request);

    PageImpl<FarmingLandOperationHistoryResponse> search(FarmingLandOperationHistorySearchRequest request);

    void deleteFarmingLandOperationHistoryById(Integer id);

    FarmingLandOperationHistoryResponse findFarmingLandOperationHistoryById(Integer id);

    void deleteFarmingLandOperationHistoriesByFarmingLandId(Integer id);

    List<FarmingLandOperationHistoryResponse> findOperationHistoriesByFarmingLandId(Integer farmingLandId);

    List<FarmingLandsProfitabilityPerYearResponse> revenueAndCostsPerYearForFarmingLand(Integer startYear, Integer endYear, Integer farmingLandId);

    List<FarmingLandsProfitabilityPerOperationResponse> getProfitabilityPerOperationsForFarmingLand(Integer startYear, Integer endYear, Integer farmingLandId);

}
