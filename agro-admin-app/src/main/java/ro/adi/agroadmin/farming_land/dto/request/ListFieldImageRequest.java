package ro.adi.agroadmin.farming_land.dto.request;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ListFieldImageRequest {

    private LocalDateTime endDate;
    private LocalDateTime startDate;
}
