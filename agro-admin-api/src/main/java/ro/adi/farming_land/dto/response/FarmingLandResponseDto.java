package ro.adi.farming_land.dto.response;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import ro.adi.farming_land.dto.common.AreaUnitTypeDto;
import ro.adi.farming_land.dto.common.DistanceUnitTypeDto;

import java.time.LocalDateTime;

@Data
@Builder
public class FarmingLandResponseDto {
    private Integer id;
    private String title;
    private Float area;
    private Float roughlyDistanceFromFarm;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Integer version;
    private AreaUnitTypeDto areaUnitType;
    private DistanceUnitTypeDto roughlyDistanceFromFarmUnitType;
}
