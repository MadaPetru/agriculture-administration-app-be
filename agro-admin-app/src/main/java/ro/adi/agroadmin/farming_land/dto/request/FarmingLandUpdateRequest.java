package ro.adi.agroadmin.farming_land.dto.request;

import lombok.Builder;
import lombok.Data;
import ro.adi.agroadmin.common.entity.AreaUnitType;
import ro.adi.agroadmin.common.entity.DistanceUnitType;

@Data
@Builder
public class FarmingLandUpdateRequest {
    private Integer id;
    private Integer version;
    private Float area;
    private String title;
    private String createdBy;
    private AreaUnitType areaUnitType;
    private Float roughlyDistanceFromFarm;
    private DistanceUnitType roughlyDistanceFromFarmUnitType;
}
