package ro.adi.agroadmin.farming_land_statistics.converter;

import org.mapstruct.Mapper;
import ro.adi.agroadmin.farming_land_statistics.dto.request.FarmingLandsProfitabilityPerYearUpdateRequest;
import ro.adi.agroadmin.farming_land_statistics.dto.response.FarmingLandsProfitabilityPerYearResponse;
import ro.adi.agroadmin.farming_land_statistics.jpa.entity.FarmingLandsStatisticsPerYearEntity;
import ro.adi.agroadmin.farming_land_statistics.jpa.entity.ProfitabilityPerYearView;
import ro.adi.farming_land_statistics.dto.response.FarmingLandsProfitabilityPerYearResponseDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FarmingLandStatisticsMapper {

    List<FarmingLandsProfitabilityPerYearResponseDto> toListFarmingLandsProfitabilityPerYearResponseDto(List<FarmingLandsProfitabilityPerYearResponse> response);

    List<FarmingLandsProfitabilityPerYearResponse> toListFarmingLandsProfitabilityPerYearResponseFromView(List<ProfitabilityPerYearView> entities);

    List<FarmingLandsProfitabilityPerYearResponse> toListFarmingLandsProfitabilityPerYearResponse(List<FarmingLandsStatisticsPerYearEntity> entities);

    FarmingLandsStatisticsPerYearEntity toFarmingLandStatisticsPerYearEntity(FarmingLandsProfitabilityPerYearUpdateRequest request);
}
