package ro.adi.agroadmin.farming_land_operation_history.service;

import org.springframework.data.domain.PageImpl;
import ro.adi.agroadmin.farming_land_operation_history.dto.response.FarmingLandOperationHistoryResponse;
import ro.adi.agroadmin.farming_land_operation_history.dto.request.FarmingLandOperationHistorySaveRequest;
import ro.adi.agroadmin.farming_land_operation_history.dto.request.FarmingLandOperationHistorySearchRequest;
import ro.adi.agroadmin.farming_land_operation_history.dto.request.FarmingLandOperationHistoryUpdateRequest;

public interface FarmingLandOperationHistoryService {
    void saveFarmingLandOperationHistory(FarmingLandOperationHistorySaveRequest request);

    void updateFarmingLandOperationHistory(FarmingLandOperationHistoryUpdateRequest request);

    PageImpl<FarmingLandOperationHistoryResponse> search(FarmingLandOperationHistorySearchRequest request);

    void deleteFarmingLandOperationHistoryById(Integer id);
    void deleteFarmingLandOperationHistoriesByFarmingLandId(Integer id);
}
