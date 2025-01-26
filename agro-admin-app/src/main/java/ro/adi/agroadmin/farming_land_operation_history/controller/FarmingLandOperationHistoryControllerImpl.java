package ro.adi.agroadmin.farming_land_operation_history.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.RestController;
import ro.adi.agroadmin.farming_land_operation_history.service.interceptor.FarmingLandOperationHistoryServiceInterceptor;
import ro.adi.farming_land_operation_history.controller.FarmingLandOperationHistoryController;
import ro.adi.farming_land_operation_history.dto.request.FarmingLandOperationHistorySaveRequestDto;
import ro.adi.farming_land_operation_history.dto.request.FarmingLandOperationHistorySearchRequestDto;
import ro.adi.farming_land_operation_history.dto.request.FarmingLandOperationHistoryUpdateRequestDto;
import ro.adi.farming_land_operation_history.dto.response.FarmingLandOperationHistoryResponseDto;

@RestController
@RequiredArgsConstructor
public class FarmingLandOperationHistoryControllerImpl implements FarmingLandOperationHistoryController {

    private final FarmingLandOperationHistoryServiceInterceptor farmingLandOperationHistoryServiceInterceptor;


    @Override
    public PageImpl<FarmingLandOperationHistoryResponseDto> search(FarmingLandOperationHistorySearchRequestDto requestDto) {
        return farmingLandOperationHistoryServiceInterceptor.search(requestDto);
    }

    @Override
    public void saveFarmingLandOperationHistory(FarmingLandOperationHistorySaveRequestDto requestDto) {
        farmingLandOperationHistoryServiceInterceptor.saveFarmingLandOperationHistory(requestDto);
    }

    @Override
    public void updateFarmingLandOperationHistory(FarmingLandOperationHistoryUpdateRequestDto requestDto) {
        farmingLandOperationHistoryServiceInterceptor.updateFarmingLandOperationHistory(requestDto);
    }

    @Override
    public void deleteFarmingLandOperationHistoryById(Integer id) {
        farmingLandOperationHistoryServiceInterceptor.deleteFarmingLandOperationHistoryById(id);
    }

    @Override
    public FarmingLandOperationHistoryResponseDto findFarmingLandOperationHistoryById(Integer id) {
        return farmingLandOperationHistoryServiceInterceptor.findFarmingLandOperationHistoryById(id);
    }
}
