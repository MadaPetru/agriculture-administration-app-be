package ro.adi.farming_land.dto.request;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ListFieldImageRequestDto {

    private Date endDate;
    private Date startDate;
    private ListFieldImagePageableRequestDto pageable;
}
