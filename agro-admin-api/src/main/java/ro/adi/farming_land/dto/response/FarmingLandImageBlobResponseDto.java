package ro.adi.farming_land.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FarmingLandImageBlobResponseDto implements Serializable {
    private Integer id;
    private String fileName;
    private LocalDateTime at;
    private String content;
}
