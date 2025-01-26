package ro.adi.agroadmin.farming_land.service.interceptor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import ro.adi.farming_land.dto.request.*;
import ro.adi.farming_land.dto.response.FarmingLandImageBlobResponseDto;
import ro.adi.farming_land.dto.response.FarmingLandResponseDto;

public interface FarmingLandServiceInterceptor {
    PageImpl<FarmingLandResponseDto> search(FarmingLandSearchRequestDto requestDto);

    void saveFarmingLand(FarmingLandSaveRequestDto requestDto);

    void updateFarmingLand(FarmingLandUpdateRequestDto requestDto);

    void deleteFarmingLandById(Integer id);

    FarmingLandResponseDto findFarmingLandByTitle(String title);

    void uploadFile(UploadFieldImageRequestDto requestDto, Integer farmingLandId);

    Page<FarmingLandImageBlobResponseDto> listFiles(ListFieldImageRequestDto requestDto, Integer farmingLandId);

    void deleteFile(Integer id);
}
