package ro.adi.farming_land.dto.request;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ro.adi.common.dto.request.PageRequestDto;

@Data
@Builder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ListFieldImagePageableRequestDto extends PageRequestDto {
}
