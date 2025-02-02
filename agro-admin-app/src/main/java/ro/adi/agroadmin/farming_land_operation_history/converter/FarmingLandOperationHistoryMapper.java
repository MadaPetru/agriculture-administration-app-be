package ro.adi.agroadmin.farming_land_operation_history.converter;

import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import ro.adi.agroadmin.common.entity.CurrencyType;
import ro.adi.agroadmin.common.entity.OperationType;
import ro.adi.agroadmin.common.entity.PlantType;
import ro.adi.agroadmin.common.entity.WeightMeasureType;
import ro.adi.agroadmin.farming_land_operation_history.dto.request.FarmingLandOperationHistorySaveRequest;
import ro.adi.agroadmin.farming_land_operation_history.dto.request.FarmingLandOperationHistorySearchRequest;
import ro.adi.agroadmin.farming_land_operation_history.dto.request.FarmingLandOperationHistoryUpdateRequest;
import ro.adi.agroadmin.farming_land_operation_history.dto.response.FarmingLandOperationHistoryResponse;
import ro.adi.agroadmin.farming_land_operation_history.jpa.entity.FarmingLandOperationHistoryEntity;
import ro.adi.agroadmin.farming_land_statistics.dto.request.FarmingLandsProfitabilityPerYearUpdateRequest;
import ro.adi.agroadmin.user.utils.UserUtils;
import ro.adi.farming_land_operation_history.dto.request.FarmingLandOperationHistorySaveRequestDto;
import ro.adi.farming_land_operation_history.dto.request.FarmingLandOperationHistorySearchRequestDto;
import ro.adi.farming_land_operation_history.dto.request.FarmingLandOperationHistoryUpdateRequestDto;
import ro.adi.farming_land_operation_history.dto.response.FarmingLandOperationHistoryResponseDto;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Mapper(componentModel = "spring")
public interface FarmingLandOperationHistoryMapper {

    default FarmingLandOperationHistorySaveRequest toFarmingLandOperationHistorySaveRequest(FarmingLandOperationHistorySaveRequestDto requestDto) {
        FarmingLandOperationHistorySaveRequest.FarmingLandOperationHistorySaveRequestBuilder farmingLandOperationHistorySaveRequest = FarmingLandOperationHistorySaveRequest.builder();
        farmingLandOperationHistorySaveRequest.operation(OperationType.valueOf(requestDto.getOperation().name()));
        farmingLandOperationHistorySaveRequest.estimatedCost(requestDto.getEstimatedCost());
        farmingLandOperationHistorySaveRequest.estimatedRevenue(requestDto.getEstimatedRevenue());
        farmingLandOperationHistorySaveRequest.estimatedHarvest(requestDto.getEstimatedHarvest());
        if (requestDto.getAppliedAt() != null) {
            farmingLandOperationHistorySaveRequest.appliedAt(LocalDateTime.ofInstant(requestDto.getAppliedAt().toInstant(), ZoneId.of("UTC")));
        }
        farmingLandOperationHistorySaveRequest.farmingLandId(requestDto.getFarmingLandId());
        farmingLandOperationHistorySaveRequest.estimatedHarvestMeasureType(WeightMeasureType.valueOf(requestDto.getEstimatedHarvestMeasureType().name()));
        farmingLandOperationHistorySaveRequest.estimatedCostCurrencyType(CurrencyType.valueOf(requestDto.getEstimatedCostCurrencyType().name()));
        farmingLandOperationHistorySaveRequest.plantType(PlantType.valueOf(requestDto.getPlantType().name()));
        farmingLandOperationHistorySaveRequest.estimatedRevenueCurrencyType(CurrencyType.valueOf(requestDto.getEstimatedRevenueCurrencyType().name()));
        farmingLandOperationHistorySaveRequest.createdBy(UserUtils.getIdOfCurrentUser());
        return farmingLandOperationHistorySaveRequest.build();
    }

    default FarmingLandOperationHistoryUpdateRequest toFarmingLandOperationHistoryUpdateRequest(FarmingLandOperationHistoryUpdateRequestDto requestDto) {
        FarmingLandOperationHistoryUpdateRequest.FarmingLandOperationHistoryUpdateRequestBuilder farmingLandOperationHistoryUpdateRequest = FarmingLandOperationHistoryUpdateRequest.builder();

        farmingLandOperationHistoryUpdateRequest.id(requestDto.getId());
        farmingLandOperationHistoryUpdateRequest.version(requestDto.getVersion());
        farmingLandOperationHistoryUpdateRequest.appliedAt(requestDto.getAppliedAt());
        farmingLandOperationHistoryUpdateRequest.operation(OperationType.valueOf(requestDto.getOperation().name()));
        farmingLandOperationHistoryUpdateRequest.estimatedCost(requestDto.getEstimatedCost());
        farmingLandOperationHistoryUpdateRequest.farmingLandId(requestDto.getFarmingLandId());
        farmingLandOperationHistoryUpdateRequest.estimatedHarvest(requestDto.getEstimatedHarvest());
        farmingLandOperationHistoryUpdateRequest.estimatedRevenue(requestDto.getEstimatedRevenue());
        farmingLandOperationHistoryUpdateRequest.plantType(PlantType.valueOf(requestDto.getPlantType().name()));
        farmingLandOperationHistoryUpdateRequest.estimatedCostCurrencyType(CurrencyType.valueOf(requestDto.getEstimatedCostCurrencyType().name()));
        farmingLandOperationHistoryUpdateRequest.estimatedHarvestMeasureType(WeightMeasureType.valueOf(requestDto.getEstimatedHarvestMeasureType().name()));
        farmingLandOperationHistoryUpdateRequest.estimatedRevenueCurrencyType(CurrencyType.valueOf(requestDto.getEstimatedRevenueCurrencyType().name()));
        farmingLandOperationHistoryUpdateRequest.createdBy(UserUtils.getIdOfCurrentUser());
        return farmingLandOperationHistoryUpdateRequest.build();
    }

    FarmingLandOperationHistoryEntity toFarmingLandOperationHistoryEntity(FarmingLandOperationHistoryUpdateRequest request);

    FarmingLandOperationHistoryEntity toFarmingLandOperationHistoryEntity(FarmingLandOperationHistorySaveRequest request);

    default FarmingLandOperationHistorySearchRequest toFarmingLandOperationHistorySearchRequest(FarmingLandOperationHistorySearchRequestDto requestDto) {
        var pageRequestDto = requestDto.getPageable();
        var pageRequest = PageRequest.of(pageRequestDto.getPage(), pageRequestDto.getSize());
        return FarmingLandOperationHistorySearchRequest.builder()
                .pageable(pageRequest)
                .searchBy(requestDto.getSearchBy())
                .build();
    }

    default FarmingLandsProfitabilityPerYearUpdateRequest toFarmingLandsProfitabilityPerYearUpdateRequest(FarmingLandOperationHistorySaveRequest request) {

        return FarmingLandsProfitabilityPerYearUpdateRequest.builder()
                .year(request.getAppliedAt().getYear())
                .cost(request.getEstimatedCost())
                .revenue(request.getEstimatedRevenue())
                .createdBy(request.getCreatedBy())
                .build();
    }

    default FarmingLandsProfitabilityPerYearUpdateRequest toFarmingLandsProfitabilityPerYearUpdateRequest(FarmingLandOperationHistoryUpdateRequest request, FarmingLandOperationHistoryResponse response) {

        return FarmingLandsProfitabilityPerYearUpdateRequest.builder()
                .year(request.getAppliedAt().toInstant().atZone(ZoneId.of("Europe/Bucharest")).getYear())
                .cost(request.getEstimatedCost() - response.getEstimatedCost())
                .revenue(request.getEstimatedRevenue() - response.getEstimatedRevenue())
                .createdBy(request.getCreatedBy())
                .build();
    }

    default FarmingLandsProfitabilityPerYearUpdateRequest toFarmingLandsProfitabilityPerYearUpdateRequest(FarmingLandOperationHistoryResponse response, Integer issuer) {

        return FarmingLandsProfitabilityPerYearUpdateRequest.builder()
                .year(response.getAppliedAt().getYear())
                .cost(-response.getEstimatedCost())
                .revenue(-response.getEstimatedRevenue())
                .createdBy(issuer)
                .build();
    }

    FarmingLandOperationHistoryResponse toFarmingLandOperationHistoryResponse(FarmingLandOperationHistoryEntity entity);

    List<FarmingLandOperationHistoryResponse> toFarmingLandOperationHistoryResponses(List<FarmingLandOperationHistoryEntity> entities);

    FarmingLandOperationHistoryResponseDto toFarmingLandOperationHistoryResponseDto(FarmingLandOperationHistoryResponse entity);

    default PageImpl<FarmingLandOperationHistoryResponseDto> toPageImplFarmingLandOperationHistoryResponseDto(PageImpl<FarmingLandOperationHistoryResponse> responsePage) {
        var responseDtoList = responsePage.stream().map(this::toFarmingLandOperationHistoryResponseDto).toList();
        return new PageImpl<>(responseDtoList, responsePage.getPageable(), responsePage.getTotalElements());
    }

    default PageImpl<FarmingLandOperationHistoryResponse> toPageImplFarmingLandOperationHistoryResponse(Page<FarmingLandOperationHistoryEntity> entities) {
        var responseList = entities.stream().map(this::toFarmingLandOperationHistoryResponse).toList();
        return new PageImpl<>(responseList, entities.getPageable(), entities.getTotalElements());
    }
}
