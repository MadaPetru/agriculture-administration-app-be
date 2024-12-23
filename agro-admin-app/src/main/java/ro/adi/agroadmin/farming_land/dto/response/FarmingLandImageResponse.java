package ro.adi.agroadmin.farming_land.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class FarmingLandImageResponse {
    private Integer id;
    private String fileName;
    private LocalDateTime at;
}
