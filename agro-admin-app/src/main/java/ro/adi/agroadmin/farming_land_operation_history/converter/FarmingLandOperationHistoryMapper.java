package ro.adi.agroadmin.farming_land_operation_history.converter;

import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import ro.adi.agroadmin.farming_land_operation_history.dto.request.FarmingLandOperationHistorySaveRequest;
import ro.adi.agroadmin.farming_land_operation_history.dto.request.FarmingLandOperationHistorySearchRequest;
import ro.adi.agroadmin.farming_land_operation_history.dto.request.FarmingLandOperationHistoryUpdateRequest;
import ro.adi.agroadmin.farming_land_operation_history.dto.response.FarmingLandOperationHistoryResponse;
import ro.adi.agroadmin.farming_land_operation_history.jpa.entity.FarmingLandOperationHistoryEntity;
import ro.adi.farming_land_operation_history.dto.request.FarmingLandOperationHistorySaveRequestDto;
import ro.adi.farming_land_operation_history.dto.request.FarmingLandOperationHistorySearchRequestDto;
import ro.adi.farming_land_operation_history.dto.request.FarmingLandOperationHistoryUpdateRequestDto;
import ro.adi.farming_land_operation_history.dto.response.FarmingLandOperationHistoryResponseDto;

@Mapper(componentModel = "spring")
public interface FarmingLandOperationHistoryMapper {

    FarmingLandOperationHistorySaveRequest toFarmingLandOperationHistorySaveRequest(FarmingLandOperationHistorySaveRequestDto requestDto);

    FarmingLandOperationHistoryUpdateRequest toFarmingLandOperationHistoryUpdateRequest(FarmingLandOperationHistoryUpdateRequestDto requestDto);

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

    FarmingLandOperationHistoryResponse toFarmingLandOperationHistoryResponse(FarmingLandOperationHistoryEntity entity);

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
