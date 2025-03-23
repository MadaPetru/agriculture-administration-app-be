package ro.adi.agroadmin.farming_land.service.interceptor;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.adi.agroadmin.common.entity.OperationType;
import ro.adi.agroadmin.farming_land.converter.FarmingLandMapper;
import ro.adi.agroadmin.farming_land.dto.response.FarmingLandImageResponse;
import ro.adi.agroadmin.farming_land.service.FarmingLandService;
import ro.adi.agroadmin.farming_land_operation_history.dto.response.FarmingLandOperationHistoryResponse;
import ro.adi.agroadmin.farming_land_operation_history.service.FarmingLandOperationHistoryService;
import ro.adi.agroadmin.farming_land_statistics.dto.request.FarmingLandsProfitabilityPerOperationAndYearUpdateRequest;
import ro.adi.agroadmin.farming_land_statistics.dto.request.FarmingLandsProfitabilityPerYearUpdateRequest;
import ro.adi.agroadmin.farming_land_statistics.service.FarmingLandStatisticsService;
import ro.adi.agroadmin.farming_land_statistics.updates.statistics_per_operation_and_year.UpdateFarmingLandStatisticsPerYearAndOperationEmitter;
import ro.adi.agroadmin.file.FileService;
import ro.adi.agroadmin.user.utils.UserUtils;
import ro.adi.farming_land.dto.request.*;
import ro.adi.farming_land.dto.response.FarmingLandImageBlobResponseDto;
import ro.adi.farming_land.dto.response.FarmingLandResponseDto;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static ro.adi.agroadmin.farming_land_statistics.updates.statistics_per_operation_and_year.UpdateFarmingLandStatisticsPerYearAndOperationScenario.DELETE_FIELD;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FarmingLandServiceInterceptorImpl implements FarmingLandServiceInterceptor {

    private final FarmingLandMapper farmingLandMapper;
    private final FileService fileService;
    private final FarmingLandService farmingLandService;
    private final FarmingLandStatisticsService farmingLandStatisticsService;
    private final FarmingLandOperationHistoryService farmingLandOperationHistoryService;
    private final UpdateFarmingLandStatisticsPerYearAndOperationEmitter updateFarmingLandStatisticsPerYearAndOperationEmitter;

    @Override
    public PageImpl<FarmingLandResponseDto> search(FarmingLandSearchRequestDto requestDto) {
        var request = farmingLandMapper.toFarmingLandSearchRequest(requestDto);
        var response = farmingLandService.search(request);
        return farmingLandMapper.toPageImplFarmingLandResponseDto(response);
    }

    @Override
    @Transactional
    public void saveFarmingLand(FarmingLandSaveRequestDto requestDto) {
        var request = farmingLandMapper.toFarmingLandSaveRequest(requestDto);
        farmingLandService.saveFarmingLand(request);
    }

    @Override
    @Transactional
    public void updateFarmingLand(FarmingLandUpdateRequestDto requestDto) {
        var request = farmingLandMapper.toFarmingLandUpdateRequest(requestDto);
        farmingLandService.updateFarmingLand(request);
    }

    @Override
    @Transactional
    public void deleteFarmingLandById(Integer id) {
        var operations = farmingLandOperationHistoryService.findOperationHistoriesByFarmingLandId(id);
        farmingLandOperationHistoryService.deleteFarmingLandOperationHistoriesByFarmingLandId(id);
        farmingLandService.deleteFarmingLandById(id);
        var issuer = UserUtils.getIdOfCurrentUser();
        updateFarmingLandStatisticsPerYear(operations, issuer);
        updateFarmingLandStatisticsPerOperationAndYear(operations, issuer);
    }

    @Override
    public FarmingLandResponseDto findFarmingLandByTitle(String title) {
        var response = farmingLandService.findFarmingLandByTitle(title);
        return farmingLandMapper.toFarmingLandResponseDto(response);
    }

    @Override
    @Transactional
    public void uploadFile(UploadFieldImageRequestDto requestDto, Integer farmingLandId) {
        var request = farmingLandMapper.toUploadFieldImageRequest(requestDto);
        var contentMappedById = farmingLandService.uploadFile(request, farmingLandId);
        contentMappedById.forEach(fileService::uploadBlob);
    }

    @Override
    public Page<FarmingLandImageBlobResponseDto> listFiles(ListFieldImageRequestDto requestDto, Integer farmingLandId) {
        var request = farmingLandMapper.toListFieldImageRequest(requestDto);
        var pageResponses = farmingLandService.listFiles(request, farmingLandId);
        var imageResponses = pageResponses.getContent();
        var imagesByBlob = groupImagesToSearchAfterByBlob(imageResponses);
        var files = fileService.listFiles(imagesByBlob);
        return farmingLandMapper.toPageFarmingLandImageBlobResponseDto(files, pageResponses.getPageable(), pageResponses.getTotalElements());
    }

    @Override
    @Transactional
    public void deleteFile(Integer id) {
        farmingLandService.deleteFile(id);
        fileService.deleteFile(id);
    }

    private Map<Integer, FarmingLandImageResponse> groupImagesToSearchAfterByBlob(List<FarmingLandImageResponse> imageToSearchAfterThatAreNotInCache) {
        return imageToSearchAfterThatAreNotInCache.stream()
                .collect(Collectors.toMap(
                        FarmingLandImageResponse::getId,
                        response -> response
                ));
    }

    private void updateFarmingLandStatisticsPerOperationAndYear(List<FarmingLandOperationHistoryResponse> operations, Integer issuer) {
        var updateRequestsForStatisticsPerOperationAndYearForFarmingLands = getUpdateRequestsForFarmingLandStatisticsPerOperationAndYear(issuer, operations);
        updateRequestsForStatisticsPerOperationAndYearForFarmingLands.forEach(updateRequestPerYearAndOperation -> {
            updateFarmingLandStatisticsPerYearAndOperationEmitter.emitUpdate(DELETE_FIELD, updateRequestPerYearAndOperation);
        });
    }

    private void updateFarmingLandStatisticsPerYear(List<FarmingLandOperationHistoryResponse> operations, Integer issuer) {
        var updateRequestsForStatisticsPerYearForFarmingLands = getUpdateRequestsForFarmingLandStatisticsPerYear(issuer, operations);
        updateRequestsForStatisticsPerYearForFarmingLands.forEach(farmingLandStatisticsService::update);
    }

    private Collection<FarmingLandsProfitabilityPerYearUpdateRequest> getUpdateRequestsForFarmingLandStatisticsPerYear(Integer issuer, List<FarmingLandOperationHistoryResponse> operations) {
        var updateRequestsPerYear = new HashMap<Integer, FarmingLandsProfitabilityPerYearUpdateRequest>();
        operations.forEach(operation -> {
            var year = operation.getAppliedAt().getYear();
            if (updateRequestsPerYear.get(year) == null) {
                var updateRequest = FarmingLandsProfitabilityPerYearUpdateRequest.builder()
                        .year(year)
                        .cost(-operation.getEstimatedCost())
                        .revenue(-operation.getEstimatedRevenue())
                        .createdBy(issuer)
                        .build();
                updateRequestsPerYear.put(year, updateRequest);
            } else {
                var updateRequest = updateRequestsPerYear.get(year);
                updateRequest.setCost(updateRequest.getCost() - operation.getEstimatedCost());
                updateRequest.setRevenue(updateRequest.getRevenue() - operation.getEstimatedRevenue());
            }
        });
        return updateRequestsPerYear.values();
    }

    private Collection<FarmingLandsProfitabilityPerOperationAndYearUpdateRequest> getUpdateRequestsForFarmingLandStatisticsPerOperationAndYear(Integer issuer, List<FarmingLandOperationHistoryResponse> operations) {
        var updateRequestsPerYear = new HashMap<Pair<Integer, OperationType>, FarmingLandsProfitabilityPerOperationAndYearUpdateRequest>();
        operations.forEach(operation -> {
            var year = operation.getAppliedAt().getYear();
            var yearAndOperationPair = Pair.of(year, operation.getOperation());
            if (updateRequestsPerYear.get(yearAndOperationPair) == null) {
                var updateRequest = FarmingLandsProfitabilityPerOperationAndYearUpdateRequest.builder()
                        .year(year)
                        .cost(-operation.getEstimatedCost())
                        .revenue(-operation.getEstimatedRevenue())
                        .createdBy(issuer)
                        .operation(operation.getOperation())
                        .build();
                updateRequestsPerYear.put(yearAndOperationPair, updateRequest);
            } else {
                var updateRequest = updateRequestsPerYear.get(yearAndOperationPair);
                updateRequest.setCost(updateRequest.getCost() - operation.getEstimatedCost());
                updateRequest.setRevenue(updateRequest.getRevenue() - operation.getEstimatedRevenue());
            }
        });
        return updateRequestsPerYear.values();
    }
}
