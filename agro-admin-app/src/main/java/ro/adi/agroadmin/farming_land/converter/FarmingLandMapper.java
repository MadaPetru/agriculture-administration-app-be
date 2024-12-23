package ro.adi.agroadmin.farming_land.converter;

import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import ro.adi.agroadmin.farming_land.dto.request.*;
import ro.adi.agroadmin.farming_land.dto.response.FarmingLandImageBlobResponse;
import ro.adi.agroadmin.farming_land.dto.response.FarmingLandImageResponse;
import ro.adi.agroadmin.farming_land.dto.response.FarmingLandResponse;
import ro.adi.agroadmin.farming_land.jpa.entity.FarmingLandEntity;
import ro.adi.agroadmin.farming_land.jpa.entity.FarmingLandImageEntity;
import ro.adi.farming_land.dto.request.*;
import ro.adi.farming_land.dto.response.FarmingLandImageBlobResponseDto;
import ro.adi.farming_land.dto.response.FarmingLandResponseDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FarmingLandMapper {
    FarmingLandSaveRequest toFarmingLandSaveRequest(FarmingLandSaveRequestDto requestDto);

    FarmingLandEntity toFarmingLandEntity(FarmingLandSaveRequest request);

    FarmingLandImageEntity toFarmingLandImageEntity(UploadFieldImageRequest request);

    UploadFieldImageRequest toUploadFieldImageRequest(UploadFieldImageRequestDto request);

    ListFieldImageRequest toListFieldImageRequest(ListFieldImageRequestDto request);

    FarmingLandUpdateRequest toFarmingLandUpdateRequest(FarmingLandUpdateRequestDto requestDto);

    FarmingLandEntity toFarmingLandEntity(FarmingLandUpdateRequest request);

    FarmingLandResponse toFarmingLandResponse(FarmingLandEntity entity);

    FarmingLandResponseDto toFarmingLandResponseDto(FarmingLandResponse response);

    List<FarmingLandImageResponse> toListFarmingLandImageResponse(List<FarmingLandImageEntity> entities);
    List<FarmingLandImageBlobResponseDto> toListFarmingLandImageBlobResponseDto(List<FarmingLandImageBlobResponse> responses);

    default FarmingLandSearchRequest toFarmingLandSearchRequest(FarmingLandSearchRequestDto requestDto) {
        var pageRequestDto = requestDto.getPageable();
        var pageRequest = PageRequest.of(pageRequestDto.getPage(), pageRequestDto.getSize());
        if (pageRequestDto.getSortBy() != null) {
            var sort = Sort.by(pageRequestDto.getDirection(), pageRequestDto.getSortBy().getValue());
            pageRequest = pageRequest.withSort(sort);
        }
        return FarmingLandSearchRequest.builder()
                .searchBy(requestDto.getSearchBy())
                .fieldsFilter(requestDto.getFieldsFilter())
                .pageable(pageRequest)
                .build();
    }

    default PageImpl<FarmingLandResponseDto> toPageImplFarmingLandResponseDto(PageImpl<FarmingLandResponse> responsePage) {
        var responseDtoList = responsePage.stream().map(this::toFarmingLandResponseDto).toList();
        return new PageImpl<>(responseDtoList, responsePage.getPageable(), responsePage.getTotalElements());
    }

    default PageImpl<FarmingLandResponse> toPageImplFarmingLandResponse(Page<FarmingLandEntity> entities) {
        var responseList = entities.stream().map(this::toFarmingLandResponse).toList();
        return new PageImpl<>(responseList, entities.getPageable(), entities.getTotalElements());
    }

}