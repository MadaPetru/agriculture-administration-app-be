package ro.adi.agroadmin.farming_land.dto.request;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class UploadFieldImageRequest {

    private Date at;
    private String content;
    private String fileName;
}
