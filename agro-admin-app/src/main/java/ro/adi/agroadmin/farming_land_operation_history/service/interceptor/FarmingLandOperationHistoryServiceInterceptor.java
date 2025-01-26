package ro.adi.agroadmin.farming_land_operation_history.service.interceptor;

import org.springframework.data.domain.PageImpl;
import ro.adi.farming_land_operation_history.dto.request.FarmingLandOperationHistorySaveRequestDto;
import ro.adi.farming_land_operation_history.dto.request.FarmingLandOperationHistorySearchRequestDto;
import ro.adi.farming_land_operation_history.dto.request.FarmingLandOperationHistoryUpdateRequestDto;
import ro.adi.farming_land_operation_history.dto.response.FarmingLandOperationHistoryResponseDto;

public interface FarmingLandOperationHistoryServiceInterceptor {
    void saveFarmingLandOperationHistory(FarmingLandOperationHistorySaveRequestDto requestDto);
    PageImpl<FarmingLandOperationHistoryResponseDto> search(FarmingLandOperationHistorySearchRequestDto requestDto);
    void updateFarmingLandOperationHistory(FarmingLandOperationHistoryUpdateRequestDto requestDto);
    void deleteFarmingLandOperationHistoryById(Integer id);
    FarmingLandOperationHistoryResponseDto findFarmingLandOperationHistoryById(Integer id);
}
