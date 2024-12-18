package ro.adi.agroadmin.farming_land.service.interceptor;

import org.springframework.data.domain.PageImpl;
import ro.adi.farming_land.dto.request.FarmingLandSaveRequestDto;
import ro.adi.farming_land.dto.request.FarmingLandSearchRequestDto;
import ro.adi.farming_land.dto.request.FarmingLandUpdateRequestDto;
import ro.adi.farming_land.dto.response.FarmingLandResponseDto;

public interface FarmingLandServiceInterceptor {
    PageImpl<FarmingLandResponseDto> search(FarmingLandSearchRequestDto requestDto);
    void saveFarmingLand(FarmingLandSaveRequestDto requestDto);
    void updateFarmingLand(FarmingLandUpdateRequestDto requestDto);
    void deleteFarmingLandById(Integer id, String issuer);
    FarmingLandResponseDto findFarmingLandByTitle(String title);
}
