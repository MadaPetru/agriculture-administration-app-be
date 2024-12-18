package ro.adi.farming_land_statistics.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FarmingLandsProfitabilityPerYearResponseDto {

    private Float cost;
    private Float revenue;
    private Integer year;
}
