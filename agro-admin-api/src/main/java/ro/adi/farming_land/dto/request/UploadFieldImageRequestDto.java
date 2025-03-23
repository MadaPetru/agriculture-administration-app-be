package ro.adi.farming_land.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import ro.adi.farming_land.dto.common.ImageRequestDto;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class UploadFieldImageRequestDto {

    @NotNull
    private Date at;
    @NotNull
    @NotEmpty
    private List<ImageRequestDto> images;
}
