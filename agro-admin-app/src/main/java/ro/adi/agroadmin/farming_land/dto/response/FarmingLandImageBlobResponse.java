package ro.adi.agroadmin.farming_land.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FarmingLandImageBlobResponse {
    private LocalDateTime at;
    private Integer id;
    private String fileName;
    private String content;
}
