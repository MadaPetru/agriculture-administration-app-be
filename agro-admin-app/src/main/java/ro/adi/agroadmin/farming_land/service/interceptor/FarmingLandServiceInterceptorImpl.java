package ro.adi.agroadmin.farming_land.service.interceptor;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import ro.adi.agroadmin.farming_land.converter.FarmingLandMapper;
import ro.adi.agroadmin.farming_land.service.FarmingLandService;
import ro.adi.agroadmin.farming_land_operation_history.service.FarmingLandOperationHistoryService;
import ro.adi.farming_land.dto.request.FarmingLandSaveRequestDto;
import ro.adi.farming_land.dto.request.FarmingLandSearchRequestDto;
import ro.adi.farming_land.dto.request.FarmingLandUpdateRequestDto;
import ro.adi.farming_land.dto.response.FarmingLandResponseDto;

@Service
@RequiredArgsConstructor
public class FarmingLandServiceInterceptorImpl implements FarmingLandServiceInterceptor {

    private final FarmingLandMapper farmingLandMapper;
    private final FarmingLandService farmingLandService;
    private final FarmingLandOperationHistoryService farmingLandOperationHistoryService;

    @Override
    public PageImpl<FarmingLandResponseDto> search(FarmingLandSearchRequestDto requestDto) {
        var request = farmingLandMapper.toFarmingLandSearchRequest(requestDto);
        var response = farmingLandService.search(request);
        return farmingLandMapper.toPageImplFarmingLandResponseDto(response);
    }

    @Override
    public void saveFarmingLand(FarmingLandSaveRequestDto requestDto) {
        var request = farmingLandMapper.toFarmingLandSaveRequest(requestDto);
        farmingLandService.saveFarmingLand(request);
    }

    @Override
    public void updateFarmingLand(FarmingLandUpdateRequestDto requestDto) {
        var request = farmingLandMapper.toFarmingLandUpdateRequest(requestDto);
        farmingLandService.updateFarmingLand(request);
    }

    @Override
    public void deleteFarmingLandById(Integer id) {
        farmingLandOperationHistoryService.deleteFarmingLandOperationHistoriesByFarmingLandId(id);
        farmingLandService.deleteFarmingLandById(id);
    }

    @Override
    public FarmingLandResponseDto findFarmingLandByTitle(String title) {
        var response = farmingLandService.findFarmingLandByTitle(title);
        return farmingLandMapper.toFarmingLandResponseDto(response);
    }
}
