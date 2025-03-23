package ro.adi.agroadmin.farming_land.dto.request;

import lombok.Builder;
import lombok.Data;
import ro.adi.agroadmin.common.dto.ImageRequest;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class UploadFieldImageRequest {

    private Date at;
    private List<ImageRequest> images;
}
