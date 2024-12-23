package ro.adi.farming_land.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FarmingLandImageBlobResponseDto {
    private String fileName;
    private String content;
}
