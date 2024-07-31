package ro.adi.farming_land.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class FarmingLandSearchRequestDto {

    @NotNull
    private FarmingLandPageableRequestDto pageable;
    private FarmingLandSearchBy searchBy;
    private Set<FarmingLandFieldsFilter> fieldsFilter;
}
