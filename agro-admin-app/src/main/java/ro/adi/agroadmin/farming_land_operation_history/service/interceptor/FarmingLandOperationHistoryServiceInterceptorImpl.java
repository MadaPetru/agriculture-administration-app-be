package ro.adi.agroadmin.farming_land_operation_history.service.interceptor;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import ro.adi.agroadmin.farming_land_operation_history.service.FarmingLandOperationHistoryService;
import ro.adi.agroadmin.farming_land_operation_history.converter.FarmingLandOperationHistoryMapper;
import ro.adi.farming_land_operation_history.dto.request.FarmingLandOperationHistorySaveRequestDto;
import ro.adi.farming_land_operation_history.dto.request.FarmingLandOperationHistorySearchRequestDto;
import ro.adi.farming_land_operation_history.dto.request.FarmingLandOperationHistoryUpdateRequestDto;
import ro.adi.farming_land_operation_history.dto.response.FarmingLandOperationHistoryResponseDto;

@Service
@RequiredArgsConstructor
public class FarmingLandOperationHistoryServiceInterceptorImpl implements FarmingLandOperationHistoryServiceInterceptor {

    private final FarmingLandOperationHistoryMapper farmingLandOperationHistoryMapper;
    private final FarmingLandOperationHistoryService farmingLandOperationHistoryService;

    @Override
    public void saveFarmingLandOperationHistory(FarmingLandOperationHistorySaveRequestDto requestDto) {
        var request = farmingLandOperationHistoryMapper.toFarmingLandOperationHistorySaveRequest(requestDto);
        farmingLandOperationHistoryService.saveFarmingLandOperationHistory(request);
    }

    @Override
    public PageImpl<FarmingLandOperationHistoryResponseDto> search(FarmingLandOperationHistorySearchRequestDto requestDto) {
        var request = farmingLandOperationHistoryMapper.toFarmingLandOperationHistorySearchRequest(requestDto);
        var responsePage = farmingLandOperationHistoryService.search(request);
        return farmingLandOperationHistoryMapper.toPageImplFarmingLandOperationHistoryResponseDto(responsePage);
    }

    @Override
    public void updateFarmingLandOperationHistory(FarmingLandOperationHistoryUpdateRequestDto requestDto) {
        var request = farmingLandOperationHistoryMapper.toFarmingLandOperationHistoryUpdateRequest(requestDto);
        farmingLandOperationHistoryService.updateFarmingLandOperationHistory(request);
    }

    @Override
    public void deleteFarmingLandOperationHistoryById(Integer id) {
        farmingLandOperationHistoryService.deleteFarmingLandOperationHistoryById(id);
    }

    @Override
    public FarmingLandOperationHistoryResponseDto findFarmingLandOperationHistoryById(Integer id) {
        return null;
    }
}
