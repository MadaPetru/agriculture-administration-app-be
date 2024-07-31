package ro.adi.farming_land.dto.request;

import lombok.*;
import ro.adi.common.dto.request.PageRequestDto;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FarmingLandPageableRequestDto extends PageRequestDto {
    private FarmingLandSortBy sortBy;
}
