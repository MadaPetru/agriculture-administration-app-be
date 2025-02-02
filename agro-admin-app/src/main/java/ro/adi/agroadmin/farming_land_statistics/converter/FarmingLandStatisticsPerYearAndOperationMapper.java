package ro.adi.agroadmin.farming_land_statistics.converter;

import org.mapstruct.Mapper;
import ro.adi.agroadmin.farming_land_operation_history.dto.request.FarmingLandOperationHistorySaveRequest;
import ro.adi.agroadmin.farming_land_operation_history.dto.request.FarmingLandOperationHistoryUpdateRequest;
import ro.adi.agroadmin.farming_land_operation_history.dto.response.FarmingLandOperationHistoryResponse;
import ro.adi.agroadmin.farming_land_statistics.dto.request.FarmingLandsProfitabilityPerOperationAndYearUpdateRequest;
import ro.adi.agroadmin.farming_land_statistics.dto.response.FarmingLandsProfitabilityPerOperationResponse;
import ro.adi.agroadmin.farming_land_statistics.jpa.entity.FarmingLandsStatisticsPerOperationAndYearEntity;
import ro.adi.agroadmin.farming_land_statistics.jpa.entity.ProfitabilityPerOperationView;
import ro.adi.farming_land_statistics.dto.response.FarmingLandsProfitabilityPerOperationResponseDto;

import java.time.ZoneId;
import java.util.List;

@Mapper(componentModel = "spring")
public interface FarmingLandStatisticsPerYearAndOperationMapper {

    List<FarmingLandsProfitabilityPerOperationResponse> toListFarmingLandsProfitabilityPerOperationResponse(List<ProfitabilityPerOperationView> response);

    List<FarmingLandsProfitabilityPerOperationResponseDto> toListFarmingLandsProfitabilityPerOperationResponseDto(List<FarmingLandsProfitabilityPerOperationResponse> response);

    default FarmingLandsStatisticsPerOperationAndYearEntity toFarmingLandsStatisticsPerOperationAndYearEntityForSave(FarmingLandsProfitabilityPerOperationAndYearUpdateRequest request) {

        var entity = new FarmingLandsStatisticsPerOperationAndYearEntity();
        entity.setVersion(0);
        entity.setCost(request.getCost());
        entity.setYear(request.getYear());
        entity.setRevenue(request.getRevenue());
        entity.setCreatedBy(request.getCreatedBy());
        entity.setOperation(request.getOperation());
        return entity;
    }


    default FarmingLandsProfitabilityPerOperationAndYearUpdateRequest toFarmingLandsProfitabilityPerYearAndOperationUpdateRequest(FarmingLandOperationHistorySaveRequest request) {

        return FarmingLandsProfitabilityPerOperationAndYearUpdateRequest.builder()
                .cost(request.getEstimatedCost())
                .createdBy(request.getCreatedBy())
                .operation(request.getOperation())
                .revenue(request.getEstimatedRevenue())
                .year(request.getAppliedAt().getYear())
                .build();
    }

    default FarmingLandsProfitabilityPerOperationAndYearUpdateRequest toFarmingLandsProfitabilityPerYearAndOperationUpdateRequest(FarmingLandOperationHistoryUpdateRequest request, FarmingLandOperationHistoryResponse response) {

        return FarmingLandsProfitabilityPerOperationAndYearUpdateRequest.builder()
                .year(request.getAppliedAt().toInstant().atZone(ZoneId.of("Europe/Bucharest")).getYear())
                .cost(request.getEstimatedCost() - response.getEstimatedCost())
                .revenue(request.getEstimatedRevenue() - response.getEstimatedRevenue())
                .createdBy(request.getCreatedBy())
                .operation(request.getOperation())
                .build();
    }

    default FarmingLandsProfitabilityPerOperationAndYearUpdateRequest toFarmingLandsProfitabilityPerYearAndOperationUpdateRequestForChangingOldOperation(FarmingLandOperationHistoryResponse response) {

        return FarmingLandsProfitabilityPerOperationAndYearUpdateRequest.builder()
                .year(response.getAppliedAt().getYear())
                .cost(-response.getEstimatedCost())
                .revenue(- response.getEstimatedRevenue())
                .createdBy(response.getCreatedBy())
                .operation(response.getOperation())
                .build();
    }

    default FarmingLandsProfitabilityPerOperationAndYearUpdateRequest toFarmingLandsProfitabilityPerYearAndOperationUpdateRequestForUpdatingOperation(FarmingLandOperationHistoryUpdateRequest request) {

        return FarmingLandsProfitabilityPerOperationAndYearUpdateRequest.builder()
                .year(request.getAppliedAt().toInstant().atZone(ZoneId.of("Europe/Bucharest")).getYear())
                .cost(request.getEstimatedCost())
                .revenue(request.getEstimatedRevenue())
                .createdBy(request.getCreatedBy())
                .operation(request.getOperation())
                .build();
    }

    default FarmingLandsProfitabilityPerOperationAndYearUpdateRequest toFarmingLandsProfitabilityPerYearAndOperationUpdateRequest(FarmingLandOperationHistoryResponse response, Integer issuer) {

        return FarmingLandsProfitabilityPerOperationAndYearUpdateRequest.builder()
                .year(response.getAppliedAt().getYear())
                .cost(-response.getEstimatedCost())
                .revenue(-response.getEstimatedRevenue())
                .createdBy(issuer)
                .operation(response.getOperation())
                .build();
    }
}
