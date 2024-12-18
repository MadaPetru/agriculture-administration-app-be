package ro.adi.agroadmin.farming_land_statistics.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FarmingLandsProfitabilityPerYearUpdateRequest {

    private Float cost;
    private Float revenue;
    private String createdBy;
    private Integer year;
}
