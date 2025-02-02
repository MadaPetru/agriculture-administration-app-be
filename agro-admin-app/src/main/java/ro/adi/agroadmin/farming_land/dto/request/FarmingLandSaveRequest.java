package ro.adi.agroadmin.farming_land.dto.request;

import lombok.Builder;
import lombok.Data;
import ro.adi.agroadmin.common.entity.AreaUnitType;
import ro.adi.agroadmin.common.entity.DistanceUnitType;

@Data
@Builder
public class FarmingLandSaveRequest {
    private String title;
    private Float area;
    private Float roughlyDistanceFromFarm;
    private Integer createdBy;
    private AreaUnitType areaUnitType;
    private DistanceUnitType roughlyDistanceFromFarmUnitType;
}