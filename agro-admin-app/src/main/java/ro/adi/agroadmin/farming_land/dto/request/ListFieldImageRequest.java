package ro.adi.agroadmin.farming_land.dto.request;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

@Data
@Builder
public class ListFieldImageRequest {

    private Pageable pageable;
    private LocalDateTime endDate;
    private LocalDateTime startDate;
}
