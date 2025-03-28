package ro.adi.farming_land.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import ro.adi.farming_land.dto.common.AreaUnitTypeDto;
import ro.adi.farming_land.dto.common.DistanceUnitTypeDto;

@Data
@Builder
public class FarmingLandUpdateRequestDto {
    @NotNull
    private Integer id;
    @NotNull
    private Integer version;
    @NotNull
    private Float area;
    @NotBlank
    private String title;
    @NotNull
    private AreaUnitTypeDto areaUnitType;
    @NotNull
    private Float roughlyDistanceFromFarm;
    @NotNull
    private DistanceUnitTypeDto roughlyDistanceFromFarmUnitType;
}
