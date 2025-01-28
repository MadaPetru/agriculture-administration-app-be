package ro.adi.farming_land.dto.request;

import lombok.Builder;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@Builder
public class ListFieldImageRequestDto {

    private Date endDate;
    private Date startDate;
    private ListFieldImagePageableRequestDto pageable;

    @Override
    public String toString() {
        var dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return "ListFieldImageRequestDto{" +
                "endDate=" + dateFormat.format(endDate) +
                ", startDate=" + dateFormat.format(startDate) +
                ", pageable=" + pageable.toString() +
                '}';
    }
}
