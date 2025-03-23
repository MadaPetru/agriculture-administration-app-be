package ro.adi.farming_land.dto.common;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ImageRequestDto {

    private String content;
    private String fileName;
}
