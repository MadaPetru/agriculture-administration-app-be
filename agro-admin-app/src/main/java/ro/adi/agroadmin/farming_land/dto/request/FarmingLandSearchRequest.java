package ro.adi.agroadmin.farming_land.dto.request;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Pageable;
import ro.adi.farming_land.dto.request.FarmingLandFieldsFilter;
import ro.adi.farming_land.dto.request.FarmingLandSearchBy;

import java.util.Set;

@Data
@Builder
public class FarmingLandSearchRequest {
    private Pageable pageable;
    private FarmingLandSearchBy searchBy;
    private Set<FarmingLandFieldsFilter> fieldsFilter;
}
