package ro.adi.agroadmin.farming_land.service.interceptor;

import org.springframework.data.domain.PageImpl;
import ro.adi.farming_land.dto.request.*;
import ro.adi.farming_land.dto.response.FarmingLandImageBlobResponseDto;
import ro.adi.farming_land.dto.response.FarmingLandResponseDto;

import java.util.List;

public interface FarmingLandServiceInterceptor {
    PageImpl<FarmingLandResponseDto> search(FarmingLandSearchRequestDto requestDto);

    void saveFarmingLand(FarmingLandSaveRequestDto requestDto);

    void updateFarmingLand(FarmingLandUpdateRequestDto requestDto);

    void deleteFarmingLandById(Integer id, String issuer);

    FarmingLandResponseDto findFarmingLandByTitle(String title);

    void uploadFile(UploadFieldImageRequestDto requestDto, Integer farmingLandId);

    List<FarmingLandImageBlobResponseDto> listFiles(ListFieldImageRequestDto requestDto, Integer farmingLandId);
}
