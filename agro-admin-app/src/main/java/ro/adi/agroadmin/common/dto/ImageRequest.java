package ro.adi.agroadmin.common.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ImageRequest {

    private String content;
    private String fileName;
}
