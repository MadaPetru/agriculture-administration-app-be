package ro.adi.agroadmin.farming_land.dto.response;

import lombok.Builder;
import lombok.Data;
import ro.adi.agroadmin.common.entity.AreaUnitType;
import ro.adi.agroadmin.common.entity.DistanceUnitType;

import java.time.LocalDateTime;

@Data
@Builder
public class FarmingLandResponse {
    private Integer id;
    private String title;
    private Float area;
    private Float roughlyDistanceFromFarm;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Integer version;
    private AreaUnitType areaUnitType;
    private DistanceUnitType roughlyDistanceFromFarmUnitType;
}
