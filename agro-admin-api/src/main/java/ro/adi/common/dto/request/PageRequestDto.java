package ro.adi.common.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import org.springframework.data.domain.Sort;

@Data
public class PageRequestDto {
    @Min(0)
    private Integer page = 0;
    @Max(1000)
    private Integer size = 1000;
    private Sort.Direction direction;
}
