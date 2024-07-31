package ro.adi.agroadmin.farming_land.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.RestController;
import ro.adi.agroadmin.farming_land.service.interceptor.FarmingLandServiceInterceptor;
import ro.adi.farming_land.controller.FarmingLandController;
import ro.adi.farming_land.dto.request.FarmingLandSaveRequestDto;
import ro.adi.farming_land.dto.request.FarmingLandSearchRequestDto;
import ro.adi.farming_land.dto.request.FarmingLandUpdateRequestDto;
import ro.adi.farming_land.dto.response.FarmingLandResponseDto;

@RestController
@RequiredArgsConstructor
public class FarmingLandControllerImpl implements FarmingLandController {

    private final FarmingLandServiceInterceptor farmingLandServiceInterceptor;

    @Override
    public PageImpl<FarmingLandResponseDto> search(FarmingLandSearchRequestDto requestDto) {
        return farmingLandServiceInterceptor.search(requestDto);
    }

    @Override
    public void saveFarmingLand(FarmingLandSaveRequestDto requestDto) {
        farmingLandServiceInterceptor.saveFarmingLand(requestDto);
    }

    @Override
    public void updateFarmingLand(FarmingLandUpdateRequestDto requestDto) {
        farmingLandServiceInterceptor.updateFarmingLand(requestDto);
    }

    @Override
    public void deleteFarmingLandById(Integer id) {
        farmingLandServiceInterceptor.deleteFarmingLandById(id);
    }

    @Override
    public FarmingLandResponseDto findFarmingLandByTitle(String title) {
        return farmingLandServiceInterceptor.findFarmingLandByTitle(title);
    }
}
