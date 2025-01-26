package ro.adi.agroadmin.farming_land_operation_history.service.interceptor;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.adi.agroadmin.farming_land_operation_history.converter.FarmingLandOperationHistoryMapper;
import ro.adi.agroadmin.farming_land_operation_history.dto.request.FarmingLandOperationHistorySaveRequest;
import ro.adi.agroadmin.farming_land_operation_history.dto.request.FarmingLandOperationHistoryUpdateRequest;
import ro.adi.agroadmin.farming_land_operation_history.dto.response.FarmingLandOperationHistoryResponse;
import ro.adi.agroadmin.farming_land_operation_history.service.FarmingLandOperationHistoryService;
import ro.adi.agroadmin.farming_land_statistics.converter.FarmingLandStatisticsPerYearAndOperationMapper;
import ro.adi.agroadmin.farming_land_statistics.service.FarmingLandStatisticsService;
import ro.adi.agroadmin.farming_land_statistics.updates.statistics_per_operation_and_year.UpdateFarmingLandStatisticsPerYearAndOperationEmitter;
import ro.adi.farming_land_operation_history.dto.request.FarmingLandOperationHistorySaveRequestDto;
import ro.adi.farming_land_operation_history.dto.request.FarmingLandOperationHistorySearchRequestDto;
import ro.adi.farming_land_operation_history.dto.request.FarmingLandOperationHistoryUpdateRequestDto;
import ro.adi.farming_land_operation_history.dto.response.FarmingLandOperationHistoryResponseDto;

import static ro.adi.agroadmin.farming_land_statistics.updates.statistics_per_operation_and_year.UpdateFarmingLandStatisticsPerYearAndOperationScenario.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FarmingLandOperationHistoryServiceInterceptorImpl implements FarmingLandOperationHistoryServiceInterceptor {

    private final FarmingLandStatisticsService farmingLandStatisticsService;
    private final FarmingLandOperationHistoryMapper farmingLandOperationHistoryMapper;
    private final FarmingLandOperationHistoryService farmingLandOperationHistoryService;
    private final FarmingLandStatisticsPerYearAndOperationMapper farmingLandStatisticsPerYearAndOperationMapper;
    private final UpdateFarmingLandStatisticsPerYearAndOperationEmitter updateFarmingLandStatisticsPerYearAndOperationEmitter;

    @Override
    @Transactional
    public void saveFarmingLandOperationHistory(FarmingLandOperationHistorySaveRequestDto requestDto) {
        var request = farmingLandOperationHistoryMapper.toFarmingLandOperationHistorySaveRequest(requestDto);
        farmingLandOperationHistoryService.saveFarmingLandOperationHistory(request);
        updateFarmingLandsProfitabilityStatisticsPerYear(request);
        updateFarmingLandsProfitabilityStatisticsPerYearAndPerOperation(request);
    }

    @Override
    public PageImpl<FarmingLandOperationHistoryResponseDto> search(FarmingLandOperationHistorySearchRequestDto requestDto) {
        var request = farmingLandOperationHistoryMapper.toFarmingLandOperationHistorySearchRequest(requestDto);
        var responsePage = farmingLandOperationHistoryService.search(request);
        return farmingLandOperationHistoryMapper.toPageImplFarmingLandOperationHistoryResponseDto(responsePage);
    }

    @Override
    @Transactional
    public void updateFarmingLandOperationHistory(FarmingLandOperationHistoryUpdateRequestDto requestDto) {
        var request = farmingLandOperationHistoryMapper.toFarmingLandOperationHistoryUpdateRequest(requestDto);
        updateFarmingLandsProfitabilityStatisticsPerYear(request);
        updateFarmingLandsProfitabilityStatisticsPerOperationAndPerYear(request);
        farmingLandOperationHistoryService.updateFarmingLandOperationHistory(request);
    }

    @Override
    @Transactional
    public void deleteFarmingLandOperationHistoryById(Integer id) {
        var issuer = SecurityContextHolder.getContext().getAuthentication().getName();
        var operationHistoryResponse = getFarmingLandOperationHistoryResponse(id);
        farmingLandOperationHistoryService.deleteFarmingLandOperationHistoryById(id);
        updateFarmingLandsProfitabilityStatisticsPerYear(issuer, operationHistoryResponse);
        updateFarmingLandsProfitabilityStatisticsPerOperationAndPerYear(issuer, operationHistoryResponse);
    }

    @Override
    public FarmingLandOperationHistoryResponseDto findFarmingLandOperationHistoryById(Integer id) {
        return null;
    }

    private void updateFarmingLandsProfitabilityStatisticsPerYear(FarmingLandOperationHistorySaveRequest request) {
        var saveRequestFarmingLandStatistic = farmingLandOperationHistoryMapper.toFarmingLandsProfitabilityPerYearUpdateRequest(request);
        farmingLandStatisticsService.update(saveRequestFarmingLandStatistic);
    }

    private void updateFarmingLandsProfitabilityStatisticsPerYearAndPerOperation(FarmingLandOperationHistorySaveRequest request) {
        var saveRequestFarmingLandStatistic = farmingLandStatisticsPerYearAndOperationMapper.toFarmingLandsProfitabilityPerYearAndOperationUpdateRequest(request);
        updateFarmingLandStatisticsPerYearAndOperationEmitter.emitUpdate(SAVE_FIELD_OPERATION, saveRequestFarmingLandStatistic);
    }

    private void updateFarmingLandsProfitabilityStatisticsPerYear(FarmingLandOperationHistoryUpdateRequest request) {
        var farmingLandOperationHistory = farmingLandOperationHistoryService.findFarmingLandOperationHistoryById(request.getId());
        var farmingLandsProfitabilityPerYearSaveRequest = farmingLandOperationHistoryMapper.toFarmingLandsProfitabilityPerYearUpdateRequest(request, farmingLandOperationHistory);
        farmingLandStatisticsService.update(farmingLandsProfitabilityPerYearSaveRequest);
    }

    private void updateFarmingLandsProfitabilityStatisticsPerOperationAndPerYear(FarmingLandOperationHistoryUpdateRequest request) {
        var farmingLandOperationHistory = farmingLandOperationHistoryService.findFarmingLandOperationHistoryById(request.getId());
        if (request.getOperation() == farmingLandOperationHistory.getOperation()) {
            var farmingLandsProfitabilityPerYearSaveRequest = farmingLandStatisticsPerYearAndOperationMapper.toFarmingLandsProfitabilityPerYearAndOperationUpdateRequest(request, farmingLandOperationHistory);
            updateFarmingLandStatisticsPerYearAndOperationEmitter.emitUpdate(UPDATE_FIELD_OPERATION, farmingLandsProfitabilityPerYearSaveRequest);
            return;
        }
        var updateRequestForNewOperation = farmingLandStatisticsPerYearAndOperationMapper.toFarmingLandsProfitabilityPerYearAndOperationUpdateRequestForUpdatingOperation(request);
        var updateRequestForOldOperation = farmingLandStatisticsPerYearAndOperationMapper.toFarmingLandsProfitabilityPerYearAndOperationUpdateRequestForChangingOldOperation(farmingLandOperationHistory);
        updateFarmingLandStatisticsPerYearAndOperationEmitter.emitUpdate(REPLACE_OPERATION_TYPE_WHEN_UPDATE_FIELD_OPERATION, updateRequestForOldOperation);
        updateFarmingLandStatisticsPerYearAndOperationEmitter.emitUpdate(UPDATE_FIELD_OPERATION, updateRequestForNewOperation);
    }

    private void updateFarmingLandsProfitabilityStatisticsPerYear(String issuer, FarmingLandOperationHistoryResponse operationHistoryResponse) {
        var farmingLandsProfitabilityPerYearSaveRequest = farmingLandOperationHistoryMapper.toFarmingLandsProfitabilityPerYearUpdateRequest(operationHistoryResponse, issuer);
        farmingLandStatisticsService.update(farmingLandsProfitabilityPerYearSaveRequest);
    }

    private void updateFarmingLandsProfitabilityStatisticsPerOperationAndPerYear(String issuer, FarmingLandOperationHistoryResponse operationHistoryResponse) {
        var farmingLandsProfitabilityPerYearSaveRequest = farmingLandStatisticsPerYearAndOperationMapper.toFarmingLandsProfitabilityPerYearAndOperationUpdateRequest(operationHistoryResponse, issuer);
        updateFarmingLandStatisticsPerYearAndOperationEmitter.emitUpdate(DELETE_FIELD_OPERATION, farmingLandsProfitabilityPerYearSaveRequest);
    }

    private FarmingLandOperationHistoryResponse getFarmingLandOperationHistoryResponse(Integer id) {
        return farmingLandOperationHistoryService.findFarmingLandOperationHistoryById(id);
    }
}
